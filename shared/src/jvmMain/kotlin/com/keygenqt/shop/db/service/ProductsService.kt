/*
 * Copyright 2023 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keygenqt.shop.db.service

import com.keygenqt.shop.data.requests.OrderProduct
import com.keygenqt.shop.db.base.DatabaseMysql
import com.keygenqt.shop.db.entities.*
import com.keygenqt.shop.interfaces.IService
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import kotlin.collections.count as countCollections

class ProductsService(
    override val db: DatabaseMysql
) : IService<ProductsService> {

    /**
     * Find entity by id
     */
    fun findById(
        id: Int
    ) = ProductEntity.findById(id)

    /**
     * Get all entities for guest
     */
    fun findByIdPublished(id: Int) = ProductEntity
        .findById(id)
        ?.let { if (it.category.isPublished && it.isPublished) it else null }

    /**
     * Get all entities
     */
    fun getAll() = ProductEntity
        .all()
        .orderBy(Pair(Products.createAt, SortOrder.DESC))

    /**
     * Get purchased products
     */
    fun getPurchased(
        excludeID: Int,
    ) = Products
        .select { (Products.isPublished eq true) and (Products.id neq excludeID) }
        .orderBy(Random())
        .limit(4)
        .map { ProductEntity.wrapRow(it) }

    /**
     * Get all entities for guest
     */
    private fun getAllPublished(
        order: OrderProduct?,
        range: Pair<Double, Double>,
        categories: List<Int>,
        collections: List<Int>,
    ) = Products
        .select {
            (Products.isPublished eq true) and
                (Products.price greaterEq range.first) and
                (Products.price lessEq range.second) and
                (
                    if (categories.isNotEmpty()) {
                        Products.categoryID inList categories
                    } else Op.TRUE
                    )
        }
        .apply {
            if (order !== null) {
                when (order) {
                    OrderProduct.NEWEST -> orderBy(Pair(Products.createAt, SortOrder.DESC))
                    OrderProduct.LOW -> orderBy(Pair(Products.price, SortOrder.ASC))
                    OrderProduct.HEIGHT -> orderBy(Pair(Products.price, SortOrder.DESC))
                }
            }
        }
        .apply {
            if (collections.isNotEmpty()) {
                andHaving {
                    Op.build {
                        exists(
                            ProductCollections.select {
                                (ProductCollections.collection inList collections) and (ProductCollections.product eq Products.id)
                            }
                        )
                    }
                }
            }
        }

    /**
     * Get page entities for guest
     */
    fun getPagePublished(
        page: Int,
        order: OrderProduct,
        range: Pair<Double, Double>,
        categories: List<Int>,
        collections: List<Int>,
        pageSize: Int
    ) = getAllPublished(
        order = order,
        range = range,
        categories = categories,
        collections = collections,
    )
        .limit(pageSize, pageSize.toLong() * (page - 1))
        .map { ProductEntity.wrapRow(it) }

    /**
     * Get count entities for guest
     */
    fun getCountPublished(
        range: Pair<Double, Double>,
        categories: List<Int>,
        collections: List<Int>,
    ) = getAllPublished(
        order = null,
        range = range,
        categories = categories,
        collections = collections,
    ).let {
        if (collections.isNotEmpty()) {
            it.adjustSlice {
                slice(
                    Products.id,
                    Products.price,
                    Products.isPublished,
                    Products.createAt,
                    Products.categoryID
                )
            }.countCollections()
        } else {
            it.count().toInt()
        }
    }

    /**
     * Get max price
     */
    fun getPrice(
        categories: List<Int>,
        collections: List<Int>,
        order: SortOrder
    ) = Products
        .select {
            (Products.isPublished eq true) and
                (
                    if (categories.isNotEmpty()) {
                        Products.categoryID inList categories
                    } else Op.TRUE
                    )
        }
        .limit(1)
        .orderBy(Pair(Products.price, order))
        .apply {
            if (collections.isNotEmpty()) {
                andHaving {
                    Op.build {
                        exists(
                            ProductCollections.select {
                                (ProductCollections.collection inList collections) and
                                    (ProductCollections.product eq Products.id)
                            }
                        )
                    }
                }
            }
        }
        .map { ProductEntity.wrapRow(it) }
        .firstOrNull()?.price ?: 0.0

    /**
     * Get inList
     */
    fun getByIdPublished(
        ids: List<Int>
    ) = ProductEntity
        .find { (Products.id inList ids) and (Products.isPublished eq true) }
        // order by ids
        .associateBy { it.id.value }
        .let { entities -> ids.mapNotNull { entities[it] } }

    /**
     * Create entity
     */
    fun insert(
        categoryID: Int,
        image1: String,
        image2: String,
        image3: String,
        name: String,
        description: String,
        price: Double,
        isPublished: Boolean,
        collections: List<Int>,
        uploads: List<String>,
    ) = ProductEntity.new {
        this.categoryID = EntityID(categoryID, Categories)
        this.image1 = image1
        this.image2 = image2.ifBlank { null }
        this.image3 = image3.ifBlank { null }
        this.name = name
        this.description = description
        this.price = price
        this.isPublished = isPublished
        this.createAt = System.currentTimeMillis()
        this.updateAt = System.currentTimeMillis()
        this.collections = SizedCollection(
            collections.mapNotNull {
                CollectionEntity.findById(it)
            }
        )
        this.uploads = SizedCollection(
            uploads.mapNotNull {
                UploadEntity.find { (Uploads.fileName eq it.substringAfterLast("/")) }.firstOrNull()
            }
        )
    }

    /**
     * Update entity
     */
    fun ProductEntity.update(
        categoryID: Int,
        image1: String,
        image2: String,
        image3: String,
        name: String,
        description: String,
        price: Double,
        isPublished: Boolean,
        collections: List<Int>,
        uploads: List<String>,
    ) = let { entity ->
        entity.categoryID = EntityID(categoryID, Categories)
        entity.image1 = image1
        entity.image2 = image2.ifBlank { null }
        entity.image3 = image3.ifBlank { null }
        entity.name = name
        entity.description = description
        entity.price = price
        entity.isPublished = isPublished
        entity.updateAt = System.currentTimeMillis()
        entity.collections = SizedCollection(
            collections.mapNotNull {
                CollectionEntity.findById(it)
            }
        )
        entity.uploads = SizedCollection(
            uploads.mapNotNull {
                UploadEntity.find { (Uploads.fileName eq it.substringAfterLast("/")) }.firstOrNull()
            }
        )
        entity
    }

    /**
     * Update entity state
     */
    fun ProductEntity.updateState(
        isPublished: Boolean,
    ) = let { entity ->
        entity.isPublished = isPublished
        entity.updateAt = System.currentTimeMillis()
        entity
    }
}

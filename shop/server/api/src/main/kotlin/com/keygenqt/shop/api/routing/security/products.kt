/*
 * Copyright 2022 Vitaliy Zarubin
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
package com.keygenqt.shop.api.routing.security

import com.keygenqt.shop.api.base.Exceptions
import com.keygenqt.shop.api.extension.getNumberParam
import com.keygenqt.shop.db.entities.toModel
import com.keygenqt.shop.db.entities.toModelWithUploads
import com.keygenqt.shop.db.entities.toModels
import com.keygenqt.shop.db.entities.toModelsWithUploads
import com.keygenqt.shop.db.service.ProductsService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.products() {

    val productsService: ProductsService by inject()

    get("/products") {
        // act
        val entities = productsService.transaction {
            getAll().toModels()
        }
        // response
        call.respond(entities)
    }

    get("/products/uploads") {
        // act
        val entities = productsService.transaction {
            getAll().toModelsWithUploads()
        }
        // response
        call.respond(entities)
    }

    get("/products/{id}") {
        // get request
        val id = call.getNumberParam()
        // act
        val entity = productsService.transaction {
            getById(id)?.toModel() ?: throw Exceptions.NotFound()
        }
        // response
        call.respond(entity)
    }

    get("/products/uploads/{id}") {
        // get request
        val id = call.getNumberParam()
        // act
        val entity = productsService.transaction {
            getById(id)?.toModelWithUploads() ?: throw Exceptions.NotFound()
        }
        // response
        call.respond(entity)
    }
}

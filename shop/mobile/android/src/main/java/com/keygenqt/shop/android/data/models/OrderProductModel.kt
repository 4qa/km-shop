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
package com.keygenqt.shop.android.data.models

import androidx.compose.runtime.Immutable
import com.keygenqt.shop.data.responses.OrderProductResponse

/**
 * Order model without db
 */
@Immutable
data class OrderProductModel(
    val price: Double,
    val count: Int,
    val sum: Double,
    val product: ProductModel,
)

fun OrderProductResponse.mapToModel() =
    OrderProductModel(
        price = price,
        count = count,
        sum = sum,
        product = product.mapToModel(),
    )

fun List<OrderProductResponse>.mapToModels() =
    map { it.mapToModel() }

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
package com.keygenqt.shop.data.responses

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

/**
 * Dashboard count/percent
 */
@JsExport
@Serializable
data class DashboardCountResponse(
    val count: Int,
    val percent: Int,
)

/**
 * Dashboard Amount
 */
@JsExport
@Serializable
data class DashboardAmountResponse(
    val amount: Double,
)

/**
 * Test model query
 */
@JsExport
@Serializable
@Suppress("ArrayInDataClass")
data class DashboardChartResponse(
    val series1: Array<Int>,
    val series2: Array<Int>,
)

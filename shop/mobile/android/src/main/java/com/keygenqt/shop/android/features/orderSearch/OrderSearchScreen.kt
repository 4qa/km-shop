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
package com.keygenqt.shop.android.features.orderSearch

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.keygenqt.shop.android.features.orderSearch.elements.AppScaffoldOrderSearch
import com.keygenqt.shop.android.features.orderSearch.elements.OrderSearchBody
import com.keygenqt.shop.android.routes.RouteOrder

/**
 * Home page, main for app
 *
 * @param viewModel page view model
 */
@Composable
fun OrderSearchScreen(
    navController: NavHostController,
    viewModel: OrderSearchViewModel = hiltViewModel(),
) {
    AppScaffoldOrderSearch(navController = navController) {
        OrderSearchBody(
            onPressSearch = { number ->
                navController.navigate(
                    RouteOrder.link(number)
                )
            }
        )
    }
}
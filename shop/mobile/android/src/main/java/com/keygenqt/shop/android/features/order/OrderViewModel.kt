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
package com.keygenqt.shop.android.features.order

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.shop.android.data.models.OrderModel
import com.keygenqt.shop.android.data.models.mapToModel
import com.keygenqt.shop.android.routes.RouteOrder
import com.keygenqt.shop.services.ServiceRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val serviceRequest: ServiceRequest,
) : ViewModel() {

    /**
     * Search by key
     */
    private val orderKey: String = savedStateHandle[RouteOrder.orderKey.name]!!

    /**
     * Error response
     */
    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_error]
     */
    val error: StateFlow<String?> get() = _error.asStateFlow()

    /**
     * Loading query
     */
    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    /**
     * [StateFlow] for [_loading]
     */
    val loading: StateFlow<Boolean> get() = _loading.asStateFlow()

    /**
     * Loading query
     */
    private val _order: MutableStateFlow<OrderModel?> = MutableStateFlow(null)

    /**
     * [StateFlow] for [_loading]
     */
    val order: StateFlow<OrderModel?> get() = _order.asStateFlow()

    init {
        searchOrder()
    }

    /**
     * Query search order by number
     */
    private fun searchOrder() {
        viewModelScope.launch {
            _error.value = null
            _loading.value = true
            try {
                delay(500)
                serviceRequest.get.orderByNumber(orderKey).let { order ->
                    _order.value = order.mapToModel()
                    _loading.value = false
                }
            } catch (ex: Exception) {
                _error.value = ex.localizedMessage ?: ""
                _loading.value = false
            }
        }
    }
}

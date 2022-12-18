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
package com.keygenqt.shop.android.extensions

import androidx.room.withTransaction
import com.keygenqt.shop.android.interfaces.IAppDatabase

/**
 * Extensions for easy transaction
 */
@Suppress("UNCHECKED_CAST")
suspend fun <T> IAppDatabase.withTransaction(body: suspend T.() -> Unit) {
    db.withTransaction {
        body.invoke(this as T)
    }
}

/**
 * Extensions for easy transaction
 */
@Suppress("UNCHECKED_CAST")
suspend fun <T, R> IAppDatabase.withTransactionResponse(body: suspend T.() -> R): R {
    return db.withTransaction {
        return@withTransaction body.invoke(this as T)
    }
}

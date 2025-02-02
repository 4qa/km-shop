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
import androidx.room.PrimaryKey
import com.keygenqt.shop.data.responses.UploadResponse

/**
 * Category product model
 */
@Immutable
data class UploadModel(
    @PrimaryKey val id: Int,
    val fileName: String,
    val fileMime: String,
    val originalFileName: String,
    val createAt: String,
)

fun UploadResponse.mapToModel() =
    UploadModel(
        id = id,
        fileName = fileName,
        fileMime = fileMime,
        originalFileName = originalFileName,
        createAt = createAt,
    )

fun List<UploadResponse>.mapToModels() =
    map { it.mapToModel() }

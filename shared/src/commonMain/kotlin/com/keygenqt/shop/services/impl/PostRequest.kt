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
package com.keygenqt.shop.services.impl

import com.keygenqt.shop.data.requests.*
import com.keygenqt.shop.data.responses.*
import com.keygenqt.shop.utils.helpers.Helpers
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.datetime.toInstant

class PostRequest(private val client: HttpClient) {

    /**
     * Login method
     */
    @Throws(Exception::class)
    suspend fun login(request: LoginRequest): AdminResponse {
        return client.post("api/login") { setBody(request) }.body()
    }

    /**
     * Login method
     */
    @Throws(Exception::class)
    suspend fun loginCookie(request: LoginRequest): SessionCookieResponse {
        client.post("api/login") { setBody(request) }.headers["Set-Cookie"]?.let {

            var name = ""
            var value = ""
            var path = ""
            var maxAge = 0
            var expires = 0L

            it.split(";").forEachIndexed { index, item ->
                if (index == 0) {
                    name = item.split("=")[0].trim()
                    value = item.split("=")[1].trim()
                } else if (item.contains("Path")) {
                    path = item.split("=")[1].trim()
                } else if (item.contains("Max-Age")) {
                    maxAge = item.split("=")[1].trim().toInt()
                } else if (item.contains("Expires")) {
                    expires = Helpers.convertCookieTimeToISO8601(item.split("=")[1].trim())
                        .toInstant()
                        .toEpochMilliseconds()
                }
            }

            return SessionCookieResponse(
                name = name,
                value = value,
                path = path,
                maxAge = maxAge,
                expires = expires,
            )
        }

        throw RuntimeException("Error get cookie session")
    }

    /**
     * Create message method
     */
    @Throws(Exception::class)
    suspend fun message(
        request: MessageRequest
    ): MessageResponse {
        return client.post("api/messages") { setBody(request) }.body()
    }

    /**
     * Create category method
     */
    @Throws(Exception::class)
    suspend fun category(
        request: CategoryRequest
    ): CategoryResponse {
        return client.post("api/categories") { setBody(request) }.body()
    }

    /**
     * Create collection method
     */
    @Throws(Exception::class)
    suspend fun collection(
        request: CollectionRequest
    ): CollectionResponse {
        return client.post("api/collections") { setBody(request) }.body()
    }

    /**
     * Create product method
     */
    @Throws(Exception::class)
    suspend fun product(
        request: ProductRequest
    ): ProductResponse {
        return client.post("api/products") { setBody(request) }.body()
    }

    /**
     * Create admin method
     */
    @Throws(Exception::class)
    suspend fun admin(
        request: AdminCreateRequest
    ): AdminResponse {
        return client.post("api/admins") { setBody(request) }.body()
    }

    /**
     * Upload file
     */
    @Throws(Exception::class)
    suspend fun uploads(
        files: Array<FileRequest>
    ): List<UploadResponse> {
        return client.submitFormWithBinaryData(
            url = "api/uploads",
            formData = formData {
                files.forEach {
                    append(
                        "file", it.file,
                        Headers.build {
                            append(HttpHeaders.ContentType, it.contentType)
                            append(HttpHeaders.ContentDisposition, "filename=\"${it.name}\"")
                        }
                    )
                }
            }
        ).body()
    }

    /**
     * Create order method
     */
    @Throws(Exception::class)
    suspend fun orderCreate(
        request: OrderCreateRequest
    ): OrderResponse {
        return client.post("api/orders/create") { setBody(request) }.body()
    }
}

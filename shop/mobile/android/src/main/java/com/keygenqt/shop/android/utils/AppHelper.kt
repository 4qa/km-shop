package com.keygenqt.shop.android.utils

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object AppHelper {
    /**
     * Generate custom type
     */
    inline fun <reified T> getType(
        isNullableAllowed: Boolean = true
    ): NavType<T> {
        return object : NavType<T>(
            isNullableAllowed = isNullableAllowed
        ) {
            override fun put(bundle: Bundle, key: String, value: T) {
                bundle.putParcelable(key, value as Parcelable)
            }

            override fun get(bundle: Bundle, key: String): T? = when {
                Build.VERSION.SDK_INT >= 33 -> bundle.getParcelable(key, T::class.java)
                else -> @Suppress("DEPRECATION") bundle.getParcelable(key) as? T
            }

            override fun parseValue(value: String): T {
                return Json.decodeFromString(Uri.decode(value))
            }
        }
    }
}
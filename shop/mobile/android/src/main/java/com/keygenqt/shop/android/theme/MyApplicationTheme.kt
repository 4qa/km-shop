package com.keygenqt.shop.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            background = Color(0xFF292929),
            primary = Color(0xFF2b96f1),
            onPrimary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFFADDCFF),
            secondary = Color(0xFFA63FC2),
            secondaryVariant = Color(0xFFD69697),
            onSecondary = Color(0xFFFFFFFF),
            surface = Color(0xFF4E4E4E),
            onSurface = Color(0xFFBADEFD)
        )
    } else {
        lightColors(
            background = Color(0xFFFFFFFF),
            primary = Color(0xFF2b96f1),
            onPrimary = Color(0xFFFFFFFF),
            primaryVariant = Color(0xFFE3F2FD),
            secondary = Color(0xFFA63FC2),
            secondaryVariant = Color(0xFFF7F0EA),
            onSecondary = Color(0xFFFFFFFF),
            surface = Color(0xFFF6F7F9),
            onSurface = Color(0xFF2b96f1)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )

    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(16.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
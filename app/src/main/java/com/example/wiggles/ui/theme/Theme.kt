package com.example.wiggles.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wiggles.vm.ThemeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = textNight,
    secondary = miniCardNight,
    tertiary = miniCardRedNight,
    background = background,
    surface = cardNight,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

private val LightColorScheme = lightColorScheme(
    primary = text,
    secondary = miniCard,
    tertiary = miniCardRed,
    background = backgroundNight,
    surface = card,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun WigglesTheme(
    themeViewModel: ThemeViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeViewModel.theme.value.toString()) {
        "Dark" -> DarkColorScheme
        "Light" -> LightColorScheme
        else -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }


    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent)
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
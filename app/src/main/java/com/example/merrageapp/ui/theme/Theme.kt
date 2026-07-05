package com.example.merrageapp.ui.theme

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
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = SoulmatePrimary,
    onPrimary = Color.White,
    primaryContainer = SoulmatePrimaryTransparent20,
    onPrimaryContainer = SoulmatePrimary,
    secondary = SoulmateVerifiedBlue,
    onSecondary = Color.White,
    tertiary = SoulmateStarYellow,
    onTertiary = Color.Black,
    background = SoulmateBackgroundDark,
    onBackground = SoulmateTextDark,
    surface = SoulmateSurfaceDark,
    onSurface = SoulmateTextDark,
    surfaceVariant = SoulmateSlate800,
    onSurfaceVariant = SoulmateSlate400,
    outline = SoulmateSlate700
)

private val LightColorScheme = lightColorScheme(
    primary = SoulmatePrimary,
    onPrimary = Color.White,
    primaryContainer = SoulmatePrimaryTransparent5,
    onPrimaryContainer = SoulmatePrimary,
    secondary = SoulmateVerifiedBlue,
    onSecondary = Color.White,
    tertiary = SoulmateStarYellow,
    onTertiary = Color.Black,
    background = SoulmateBackgroundLight,
    onBackground = SoulmateTextLight,
    surface = SoulmateSurfaceLight,
    onSurface = SoulmateTextLight,
    surfaceVariant = SoulmateSlate200,
    onSurfaceVariant = SoulmateSlate600,
    outline = SoulmateSlate400
)

@Composable
fun MerrageAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // Setting to false by default to showcase the Soulmate design system
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

package onetree.ignacio.guerendiain.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import onetree.ignacio.guerendiain.core.theme.defs.DarkColorPalette
import onetree.ignacio.guerendiain.core.theme.defs.DefaultTypographyDefs
import onetree.ignacio.guerendiain.core.theme.defs.LightColorPalette

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorPalette = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        CurrentColorPalette provides colorPalette,
        CurrentTypographyDefs provides DefaultTypographyDefs
    ) {
        content()
    }
}

val CurrentColorPalette = staticCompositionLocalOf { LightColorPalette }
val CurrentTypographyDefs = staticCompositionLocalOf { DefaultTypographyDefs }

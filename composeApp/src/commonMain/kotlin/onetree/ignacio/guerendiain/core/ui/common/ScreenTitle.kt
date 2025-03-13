package onetree.ignacio.guerendiain.core.ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ScreenTitle(title: String, modifier: Modifier = Modifier){
    Text(
        text = title,
        color = CurrentColorPalette.current.defaultText,
        style = CurrentTypographyDefs.current.defaultScreenTitle,
        modifier = modifier
    )
}

@Composable
fun ScreenTitle(titleRes: StringResource, modifier: Modifier = Modifier){
    ScreenTitle(stringResource(titleRes), modifier)
}
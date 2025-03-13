package onetree.ignacio.guerendiain.core.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.ui.button.MainButton

@Composable
fun Tag(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    cb: () -> Unit = {}
){
    MainButton(
        modifier = modifier.height(36.dp),
        buttonColor = CurrentColorPalette.current.tagBg,
        fgColor = CurrentColorPalette.current.tagFg,
        borderColor = if (selected)
            CurrentColorPalette.current.tagSelectedBorder
        else
            CurrentColorPalette.current.tagUnselectedBorder,
        text = text,
        cb = cb
    )
}
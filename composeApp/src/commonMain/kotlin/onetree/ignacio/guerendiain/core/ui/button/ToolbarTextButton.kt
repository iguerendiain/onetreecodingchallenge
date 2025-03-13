package onetree.ignacio.guerendiain.core.ui.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ToolbarTextButton(
    text: String? = null,
    res: StringResource? = null,
    modifier: Modifier = Modifier
) {
    text?: res?.let { stringResource(it) }?.let {
        Text(
            text = it,
            style = CurrentTypographyDefs.current.toolbarButton.copy(
                color = CurrentColorPalette.current.toolbarTextButton
            ),
            modifier = Modifier
                .padding(6.dp)
                .then(modifier)
        )
    }
}
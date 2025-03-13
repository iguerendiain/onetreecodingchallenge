package onetree.ignacio.guerendiain.core.ui.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ToolbarIconButton(
    painter: Painter? = null,
    res: DrawableResource? = null,
    contentDescription: String? = null,
    contentDescriptionRes: StringResource? = null,
    modifier: Modifier = Modifier
) {
    val solvedContentDescription =
        contentDescription ?: contentDescriptionRes?.let { stringResource(it) }

    painter ?: res?.let { painterResource(it) }?.let { icon ->
        Image(
            painter = icon,
            colorFilter = ColorFilter.tint(CurrentColorPalette.current.defaultText),
            contentDescription = solvedContentDescription,
            modifier = Modifier
                .size(36.dp)
                .padding(6.dp)
                .then(modifier)
        )
    }
}
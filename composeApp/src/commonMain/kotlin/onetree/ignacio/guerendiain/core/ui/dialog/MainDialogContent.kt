package onetree.ignacio.guerendiain.core.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainDialogContent(
    imagePainter: Painter? = null,
    imageRes: DrawableResource? = null,
    imageTint: Color? = null,
    imageBgColor: Color? = null,
    title: String? = null,
    titleRes: StringResource? = null,
    description: String? = null,
    descriptionAnnotated: AnnotatedString? = null,
    descriptionRes: StringResource? = null,
    bottom: @Composable (() -> Unit)? = null
){
    val imagePainterSolved = imagePainter?:imageRes?.let { painterResource(it) }
    val titleSolved = title?:titleRes?.let { stringResource(it) }
    val descriptionSolved = description?:descriptionRes?.let { stringResource(it) }
    val image: ( @Composable () -> Unit )? = if (imagePainterSolved!=null) (@Composable {
        Image(
            painter = imagePainterSolved,
            contentDescription = null,
            colorFilter = imageTint?.let { c -> ColorFilter.tint(c) }
        )
    })
    else null

    Column(horizontalAlignment = Alignment.CenterHorizontally){
        if (image!=null)
            if (imageBgColor!=null) @Composable {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(color = imageBgColor)
                ) { image() }
            }else @Composable {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(90.dp)
                ) { image() }
            }

        Spacer(Modifier.size(12.dp))

        titleSolved?.let {
            Text(
                text = it,
                style = CurrentTypographyDefs.current.dialogTitle.copy(
                    color = CurrentColorPalette.current.defaultText
                )
            )
        }

        Spacer(Modifier.size(12.dp))

        val descriptionStyle = CurrentTypographyDefs.current.dialogDescription.copy(
            color = CurrentColorPalette.current.defaultText
        )

        descriptionSolved
            ?.let { Text(text = it, style = descriptionStyle) }
            ?:descriptionAnnotated?.let { Text(text = it, style = descriptionStyle) }

        Spacer(Modifier.size(24.dp))

        bottom?.invoke()
    }
}
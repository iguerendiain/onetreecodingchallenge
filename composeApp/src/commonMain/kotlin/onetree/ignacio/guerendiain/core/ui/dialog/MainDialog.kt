package onetree.ignacio.guerendiain.core.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.AnnotatedString
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun MainDialog(
    onDismissRequest: () -> Unit,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    usePlatformDefaultWidth: Boolean = true,
    imagePainter: Painter? = null,
    imageRes: DrawableResource? = null,
    imageTint: Color? = null,
    imageBgColor: Color? = null,
    title: String? = null,
    titleRes: StringResource? = null,
    description: String? = null,
    descriptionAnnotated: AnnotatedString? = null,
    descriptionRes: StringResource? = null,
    buttons: Array<@Composable () -> Unit>? = null
){
    DefaultDialog(
        content = { MainDialogContent(
            imagePainter = imagePainter,
            imageRes = imageRes,
            imageTint = imageTint,
            imageBgColor = imageBgColor,
            title = title,
            titleRes = titleRes,
            description = description,
            descriptionAnnotated = descriptionAnnotated,
            descriptionRes = descriptionRes,
            bottom = buttons?.let { { DialogButtonSet(it) } },
        ) },
        onDismissRequest = onDismissRequest,
        dismissOnBackPress = dismissOnBackPress,
        dismissOnClickOutside = dismissOnClickOutside,
        usePlatformDefaultWidth = usePlatformDefaultWidth
    )
}

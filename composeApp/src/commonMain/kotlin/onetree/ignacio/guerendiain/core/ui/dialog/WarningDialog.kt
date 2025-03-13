package onetree.ignacio.guerendiain.core.ui.dialog

import androidx.compose.runtime.Composable
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.ic_exclamation
import org.jetbrains.compose.resources.StringResource

@Composable
fun WarningDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    titleRes: StringResource? = null,
    description: String? = null,
    descriptionRes: StringResource? = null,
    retryButtonText: String? = null,
    retryButtonRes: StringResource? = null,
    retryButtonCb: (() -> Unit)? = null,
    cancelButtonText: String? = null,
    cancelButtonRes: StringResource? = null,
    cancelButtonCb: (() -> Unit)? = null,
){
    val buttons = mutableListOf<@Composable ()->Unit>().apply {
        if (retryButtonCb != null)add @Composable {
            DialogButton(
                buttonType = DialogButtonType.RETRY,
                text = retryButtonText,
                textResource = retryButtonRes,
                cb = retryButtonCb
            )
        }

        if (cancelButtonCb != null) add @Composable {
            DialogButton(
                buttonType = DialogButtonType.CANCEL,
                text = cancelButtonText,
                textResource = cancelButtonRes,
                cb = cancelButtonCb
            )
        }
    }.toTypedArray()

    MainDialog(
        onDismissRequest = onDismissRequest,
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true,
        imageRes = Res.drawable.ic_exclamation,
        imageTint = CurrentColorPalette.current.dialogImageTintWarning,
        imageBgColor = CurrentColorPalette.current.dialogImageBgWarning,
        title = title,
        titleRes = titleRes,
        description = description,
        descriptionRes = descriptionRes,
        buttons = buttons
    )
}

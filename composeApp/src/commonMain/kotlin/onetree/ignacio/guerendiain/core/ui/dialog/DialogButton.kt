package onetree.ignacio.guerendiain.core.ui.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.ui.button.MainButton
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class DialogButtonType { RETRY, CANCEL, OK }

@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    buttonType: DialogButtonType = DialogButtonType.OK,
    icon: Painter? = null,
    iconResource: DrawableResource? = null,
    text: String? = null,
    textResource: StringResource? = null,
    cb: () -> Unit = {}
){
    val buttonColor: Color
    val fgColor: Color

    when (buttonType){
        DialogButtonType.RETRY -> {
            buttonColor = CurrentColorPalette.current.dialogButtonRetryBg
            fgColor = CurrentColorPalette.current.dialogButtonRetryFg
        }
        DialogButtonType.CANCEL -> {
            buttonColor = CurrentColorPalette.current.dialogButtonCancelBg
            fgColor = CurrentColorPalette.current.dialogButtonCancelFg
        }
        DialogButtonType.OK -> {
            buttonColor = CurrentColorPalette.current.dialogButtonOkBg
            fgColor = CurrentColorPalette.current.dialogButtonOkFg
        }
    }

    MainButton(
        modifier = Modifier.fillMaxWidth().then(modifier),
        buttonColor = buttonColor,
        fgColor = fgColor,
        leftIcon = icon,
        leftIconResource = iconResource,
        text = text,
        textResource = textResource,
        cb = cb
    )
}
package onetree.ignacio.guerendiain.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs

@Composable
fun MainTextField(
    value: String,
    textAlign: TextAlign = TextAlign.Start,
    hint: String? = null,
    hintTextAlign: TextAlign = TextAlign.Start,
    startContent: @Composable (() -> Unit)? = null,
    endContent: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit = {}
) {
    MainTextFieldScafolding(
        modifier = modifier,
        startContent = startContent,
        endContent = endContent,
        textField = {
            Box {
                val textStyle = CurrentTypographyDefs
                    .current
                    .defaultInputValue
                    .copy(
                        color = CurrentColorPalette.current.defaultText,
                        textAlign = textAlign
                    )

                if (enabled) BasicTextField(
                    value = value,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    onValueChange = onValueChange,
                    visualTransformation = visualTransformation,
                    textStyle = textStyle,
                    modifier = Modifier.fillMaxSize()
                )else  Text(
                    text = value,
                    style = textStyle,
                    modifier = Modifier.fillMaxSize()
                )

                if (hint != null && value.isBlank()) Text(
                    text = hint,
                    style = CurrentTypographyDefs
                        .current
                        .defaultInputValue
                        .copy(
                            color = CurrentColorPalette.current.defaultText.copy(alpha = .5f),
                            textAlign = hintTextAlign
                        ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@Composable
fun MainTextFieldScafolding(
    modifier: Modifier = Modifier,
    startContent: @Composable (() -> Unit)? = null,
    textField: @Composable () -> Unit,
    endContent: @Composable (() -> Unit)? = null
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                shape = RoundedCornerShape(12.dp),
                color = CurrentColorPalette.current.mainTextFieldBg
            )
            .padding(horizontal = 8.dp)
    ) {
        startContent?.let {
            it()
            Spacer(Modifier.width(8.dp))
        }

        Box(
            Modifier
                .weight(1f)
                .padding(vertical = 11.dp)
        ){ textField() }

        endContent?.let {
            Spacer(Modifier.width(8.dp))
            it()
        }
    }
}
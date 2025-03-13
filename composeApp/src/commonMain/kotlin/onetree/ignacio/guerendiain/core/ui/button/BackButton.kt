package onetree.ignacio.guerendiain.core.ui.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.StringResource

@Composable
fun BackButton(
    contentDescription: String? = null,
    contentDescriptionRes: StringResource? = null,
    modifier: Modifier = Modifier
){
    ToolbarIconButton(
        res = Res.drawable.ic_back,
        contentDescription = contentDescription,
        contentDescriptionRes = contentDescriptionRes,
        modifier = modifier
    )
}
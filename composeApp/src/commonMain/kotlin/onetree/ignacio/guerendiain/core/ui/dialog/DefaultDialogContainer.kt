package onetree.ignacio.guerendiain.core.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette

@Composable
fun DefaultDialogContainer(content: @Composable ()->Unit){
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(CurrentColorPalette.current.dialogBg)
            .clip(RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        content()
    }
}
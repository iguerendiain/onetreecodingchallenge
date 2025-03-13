package onetree.ignacio.guerendiain.core.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Toolbar(
    startContent: @Composable BoxScope.() -> Unit,
    centerContent: @Composable BoxScope.() -> Unit,
    endContent: @Composable BoxScope.() -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().height(48.dp)
    ){
        Box(Modifier.align(Alignment.CenterStart)){ startContent() }
        Box(Modifier.align(Alignment.Center)) { centerContent() }
        Box(Modifier.align(Alignment.CenterEnd)) { endContent() }
    }
}
package onetree.ignacio.guerendiain

import androidx.compose.runtime.*
import onetree.ignacio.guerendiain.app.RootNavigation
import onetree.ignacio.guerendiain.core.theme.MainTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    MainTheme {
        KoinContext {
            RootNavigation()
        }
    }
}
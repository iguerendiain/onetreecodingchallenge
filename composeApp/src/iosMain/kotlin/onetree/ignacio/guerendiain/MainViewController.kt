package onetree.ignacio.guerendiain

import androidx.compose.ui.window.ComposeUIViewController
import onetree.ignacio.guerendiain.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure =  {
        initKoin()
    }
) {
    App()
}
package onetree.ignacio.guerendiain

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import onetree.ignacio.guerendiain.core.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "OneTree Coding Challenge",
        ) {
            App()
        }
    }
}
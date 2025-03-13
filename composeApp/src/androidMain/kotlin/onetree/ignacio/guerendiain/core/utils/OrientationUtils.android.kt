package onetree.ignacio.guerendiain.core.utils

import android.content.Context
import android.content.res.Configuration

actual class OrientationService(private val context: Context) {

    actual fun getOrientation(): ScreenOrientation {
        val config = context.resources.configuration
        return when (config.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> ScreenOrientation.LANDSCAPE
            Configuration.ORIENTATION_PORTRAIT -> ScreenOrientation.PORTRAIT
            else -> ScreenOrientation.PORTRAIT
        }
    }
}
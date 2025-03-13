package onetree.ignacio.guerendiain.core.utils

enum class ScreenOrientation { PORTRAIT, LANDSCAPE }

expect class OrientationService {
    fun getOrientation(): ScreenOrientation
}
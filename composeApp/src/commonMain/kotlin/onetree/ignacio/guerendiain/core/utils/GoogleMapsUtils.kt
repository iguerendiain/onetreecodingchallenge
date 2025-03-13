package onetree.ignacio.guerendiain.core.utils

object GoogleMapsUtils {

    fun buildURLWithPin(latitude: Double, longitude: Double): String {
        return "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
    }

}
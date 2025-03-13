package onetree.ignacio.guerendiain.core.utils

actual class LocationService {
    actual suspend fun getCurrentLocation(): Pair<Double, Double>? {
        return null
    }
    actual fun isServiceAvailable() = false
    actual fun hasUserGivenPermission() = true
    actual fun askUserForPermission(cb: (Boolean) -> Unit) {
    }
}
package onetree.ignacio.guerendiain.core.utils

expect class LocationService {
    suspend fun getCurrentLocation(): Pair<Double, Double>?
    fun isServiceAvailable(): Boolean
    fun hasUserGivenPermission(): Boolean
    fun askUserForPermission(cb: (Boolean)-> Unit)
}
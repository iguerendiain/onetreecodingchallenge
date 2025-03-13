package onetree.ignacio.guerendiain.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import onetree.ignacio.guerendiain.MainActivity

actual class LocationService(private val context: Context){

    companion object{
        @SuppressLint("StaticFieldLeak")
        var INSTANCE: LocationService? = null

        fun getInstance(context: Context): LocationService {
            return (INSTANCE?:LocationService(context)).apply {
                INSTANCE = this
            }
        }
    }

    var permissionLauncher: ActivityResultLauncher<Array<String>>? = null
    var grantedCallback: ((Boolean) -> Unit)? = null

    private val locationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @SuppressLint("MissingPermission")
    actual suspend fun getCurrentLocation(): Pair<Double, Double>? {
        val providers = locationManager.getProviders(true)
        var bestLocation: Location? = null
        for (p in providers){
            val location = locationManager.getLastKnownLocation(p) ?: continue

            if (bestLocation == null || location.accuracy < bestLocation.accuracy)
                bestLocation = location
        }

        return bestLocation?.let { Pair(it.latitude, it.longitude) }
    }

    actual fun isServiceAvailable(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        try {
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (e: Exception) {}

        return gpsEnabled
    }

    actual fun hasUserGivenPermission() = ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    actual fun askUserForPermission(cb: (Boolean) -> Unit) {
        grantedCallback = cb
        permissionLauncher?.launch(MainActivity.LOCATION_PERMISSIONS)
    }

    fun triggerGrantedCallback(granted: Boolean){
        grantedCallback?.invoke(granted)
        grantedCallback = null
    }
}
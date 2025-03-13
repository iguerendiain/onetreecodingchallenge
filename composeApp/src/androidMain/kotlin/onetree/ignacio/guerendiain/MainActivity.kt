package onetree.ignacio.guerendiain

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import onetree.ignacio.guerendiain.core.theme.MainTheme
import onetree.ignacio.guerendiain.core.utils.LocationService

class MainActivity : ComponentActivity() {
    companion object{
        val LOCATION_PERMISSIONS = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private val locationService by lazy {
        LocationService.getInstance(applicationContext)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
        ) { grantMap: Map<String, Boolean> ->
            var granted = true

            for (grant in grantMap.values){
                if (!grant){
                    granted = false
                    break
                }
            }

            locationService.triggerGrantedCallback(granted)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationService.permissionLauncher = requestPermissionLauncher

        setContent {
            MainTheme {
                val view = LocalView.current
                val window = (view.context as Activity).window
                val darkTheme = isSystemInDarkTheme()

                LaunchedEffect(darkTheme) {
                    WindowCompat
                        .getInsetsController(window, view)
                        .isAppearanceLightStatusBars = !darkTheme
                }

                enableEdgeToEdge()
                App()
            }
        }
    }

    override fun onDestroy() {
        locationService.permissionLauncher = null
        super.onDestroy()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
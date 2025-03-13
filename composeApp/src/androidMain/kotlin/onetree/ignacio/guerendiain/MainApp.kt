package onetree.ignacio.guerendiain

import android.app.Application
import onetree.ignacio.guerendiain.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApp)
        }
    }
}
package onetree.ignacio.guerendiain.core.di

import onetree.ignacio.guerendiain.core.storage.getDatabaseBuilder
import onetree.ignacio.guerendiain.core.utils.LocationService
import onetree.ignacio.guerendiain.core.utils.OrientationService
import org.koin.dsl.module

actual val platfotmDIModule = module {
    single { getDatabaseBuilder(context = get()) }
    single { LocationService.getInstance(context = get()) }
    single { OrientationService(context = get()) }
}
package onetree.ignacio.guerendiain.core.di

import onetree.ignacio.guerendiain.core.domain.repository.TaskRepository
import onetree.ignacio.guerendiain.core.domain.repositoryimpl.TaskRepositoryRoomDB
import onetree.ignacio.guerendiain.core.storage.buildRoomDatabase
import onetree.ignacio.guerendiain.feature.tasks.vm.TasksViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDIModule = module {
    single { buildRoomDatabase(builder = get()) }
    single { TaskRepositoryRoomDB(db = get()) }.bind<TaskRepository>()
    viewModelOf(::TasksViewModel)
}

expect val platfotmDIModule: Module

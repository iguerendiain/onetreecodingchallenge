package onetree.ignacio.guerendiain.core.storage

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import onetree.ignacio.guerendiain.core.storage.dao.TaskDao
import onetree.ignacio.guerendiain.core.storage.model.TaskDB

@Database(
    entities = [
        TaskDB::class
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MainDB : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MainDB> {
    override fun initialize(): MainDB
}

fun buildRoomDatabase(builder: RoomDatabase.Builder<MainDB>): MainDB {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
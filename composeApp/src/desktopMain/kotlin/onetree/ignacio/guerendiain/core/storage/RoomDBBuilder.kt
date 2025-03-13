package onetree.ignacio.guerendiain.core.storage

import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(): RoomDatabase.Builder<MainDB> {
    return Room.databaseBuilder<MainDB>(
        name = "main.db"
    )
}
package onetree.ignacio.guerendiain.core.storage

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MainDB> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("main.db")
    return Room.databaseBuilder<MainDB>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
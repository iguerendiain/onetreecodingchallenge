package onetree.ignacio.guerendiain.core.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskDB(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val done: Boolean,
    val creation_time: Long,
    val update_time: Long,
    val location_lat: Double?,
    val location_lng: Double?
)
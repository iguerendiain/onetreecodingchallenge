package onetree.ignacio.guerendiain.core.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import onetree.ignacio.guerendiain.core.storage.model.TaskDB

@Dao
interface TaskDao {
    @Query("select * from task order by done asc, update_time desc")
    suspend fun getAllTasks(): List<TaskDB>

    @Query("select * from task where done == false order by update_time asc")
    suspend fun getUndoneTasks(): List<TaskDB>

    @Query("select * from task where done == true order by update_time desc")
    suspend fun getDoneTasks(): List<TaskDB>

    @Query("select * from task where id = :id")
    suspend fun findTaskById(id: Long): TaskDB?

    @Query("delete from task where id = :id")
    suspend fun deleteTask(id: Long)

    @Insert
    suspend fun createTask(task: TaskDB)

    @Update
    suspend fun updateTask(task: TaskDB)

    @Query("delete from task")
    suspend fun deleteAllTasks()

    @Query("delete from task where done == true")
    suspend fun deleteAllDoneTasks()
}
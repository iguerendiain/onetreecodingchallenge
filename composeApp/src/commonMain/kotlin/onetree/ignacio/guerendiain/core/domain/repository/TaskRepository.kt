package onetree.ignacio.guerendiain.core.domain.repository

import onetree.ignacio.guerendiain.core.domain.model.Task

interface TaskRepository{
    suspend fun getAllTasks(): List<Task>
    suspend fun deleteAllTasks()
    suspend fun createTask(task: Task)
    suspend fun findTaskById(taskId: Long): Task?
    suspend fun deleteTask(taskId: Long)
    suspend fun updateTask(task: Task)
    suspend fun getDoneTasks(): List<Task>
    suspend fun getUndoneTasks(): List<Task>
}
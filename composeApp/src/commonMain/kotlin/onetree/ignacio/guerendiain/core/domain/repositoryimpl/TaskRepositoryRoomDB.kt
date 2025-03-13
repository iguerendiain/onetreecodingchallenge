package onetree.ignacio.guerendiain.core.domain.repositoryimpl

import onetree.ignacio.guerendiain.core.storage.DBModelMapper
import onetree.ignacio.guerendiain.core.domain.model.Task
import onetree.ignacio.guerendiain.core.domain.repository.TaskRepository
import onetree.ignacio.guerendiain.core.storage.MainDB

class TaskRepositoryRoomDB(
    private val db: MainDB
): TaskRepository {
    override suspend fun getAllTasks() = db
        .getTaskDao()
        .getAllTasks()
        .map { DBModelMapper.buildTaskFrom(it) }

    override suspend fun deleteAllTasks() {
        db.getTaskDao().deleteAllTasks()
    }

    override suspend fun createTask(task: Task){
        db
            .getTaskDao()
            .createTask(
                DBModelMapper.buildTaskDBFrom(task)
            )
    }

    override suspend fun findTaskById(taskId: Long) = db
        .getTaskDao()
        .findTaskById(taskId)
        ?.let { DBModelMapper.buildTaskFrom(it) }

    override suspend fun deleteTask(taskId: Long) = db
        .getTaskDao()
        .deleteTask(taskId)

    override suspend fun updateTask(task: Task){
        DBModelMapper
            .buildTaskDBFrom(task)
            .let { db.getTaskDao().updateTask(it) }
    }

    override suspend fun getDoneTasks() = db
        .getTaskDao()
        .getDoneTasks()
        .map { DBModelMapper.buildTaskFrom(it) }

    override suspend fun getUndoneTasks() = db
        .getTaskDao()
        .getUndoneTasks()
        .map { DBModelMapper.buildTaskFrom(it) }
}
package onetree.ignacio.guerendiain.core.domain.repositoryimpl

import kotlinx.datetime.Clock
import onetree.ignacio.guerendiain.core.domain.model.Task
import onetree.ignacio.guerendiain.core.domain.repository.TaskRepository

class TaskRepositoryMock(): TaskRepository {
    companion object {
        val MOCK_TASK_DB = listOf(
            "Take out the garbage",
            "Write unit tests",
            "Make sure the Android app works",
            "Zip all the code and send",
            "Eat something!!",
            "Try to sleep",
            "Finish Inidiana Jones And The Great Circle"
        ).mapIndexed{ index, name ->
            Task(
                id = index.toLong() + 1,
                name = name,
                description = "",
                done = index > 3,
                creationTime = Clock.System.now(),
                updateTime = Clock.System.now(),
                location = null
            )
        }
    }

    private val tasks = mutableListOf<Task>()

    fun resetMockData(){
        tasks.clear()
        tasks.addAll(MOCK_TASK_DB)
    }

    override suspend fun getAllTasks(): List<Task> {
        return tasks
            .sortedByDescending { it.updateTime }
            .sortedBy { it.done }
    }

    override suspend fun deleteAllTasks() {
        tasks.clear()
    }

    override suspend fun createTask(task: Task) {
        val lastId = tasks.last().id
        tasks.add(task.copy(id = lastId +1))
    }

    override suspend fun findTaskById(taskId: Long): Task? {
        return tasks.find { it.id == taskId }
    }

    override suspend fun deleteTask(taskId: Long) {
        tasks
            .find { it.id == taskId }
            ?.let { tasks.remove(it) }
    }

    override suspend fun updateTask(task: Task) {
        tasks
            .find { it.id == task.id}
            ?.let { tasks.indexOf(it) }
            ?.let { tasks[it] = task }
    }

    override suspend fun getDoneTasks(): List<Task> {
        return tasks
            .filter { it.done }
            .sortedByDescending { it.updateTime }
    }

    override suspend fun getUndoneTasks(): List<Task> {
        return tasks
            .filter { !it.done }
            .sortedBy { it.updateTime }
    }
}
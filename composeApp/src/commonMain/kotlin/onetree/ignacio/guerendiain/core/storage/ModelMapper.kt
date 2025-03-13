package onetree.ignacio.guerendiain.core.storage

import kotlinx.datetime.Instant
import onetree.ignacio.guerendiain.core.domain.model.Task
import onetree.ignacio.guerendiain.core.storage.model.TaskDB

object DBModelMapper{
    fun buildTaskFrom(taskDB: TaskDB) = Task(
        id = taskDB.id,
        name = taskDB.name,
        done = taskDB.done,
        description = taskDB.description,
        creationTime = Instant.fromEpochSeconds(taskDB.creation_time),
        updateTime = Instant.fromEpochSeconds(taskDB.update_time),
        location = if (taskDB.location_lat!=null && taskDB.location_lng!=null)
            Pair(taskDB.location_lat, taskDB.location_lng)
        else
            null,
    )

    fun buildTaskDBFrom(task: Task) = TaskDB(
        id = if (task.id > 0) task.id else 0,
        name = task.name,
        description = task.description,
        done = task.done,
        creation_time = task.creationTime.epochSeconds,
        update_time = task.updateTime.epochSeconds,
        location_lat = task.location?.first,
        location_lng = task.location?.second
    )
}
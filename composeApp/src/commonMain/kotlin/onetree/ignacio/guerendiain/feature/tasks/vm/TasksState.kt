package onetree.ignacio.guerendiain.feature.tasks.vm

import onetree.ignacio.guerendiain.core.domain.model.Task

enum class TaskFilter { DONE, UNDONE, ALL}
enum class LocationStatus {
    LOADING, EXISTING, NONE, NOT_AVAILABLE, NOT_PERMISSION
}
data class TasksState(
    val currentFilter: TaskFilter,
    val listedTasks: List<Task>,
    val selectedTask: Task?,
    val locationStatus: LocationStatus,
    val currentLocation: Pair<Double, Double>?,
    val clearedLocation: Boolean
){
    companion object {
        val DEFAULT = TasksState(
            currentFilter = TaskFilter.ALL,
            listedTasks = listOf(),
            selectedTask = null,
            locationStatus = LocationStatus.NOT_AVAILABLE,
            currentLocation = null,
            clearedLocation = false
        )
    }
}
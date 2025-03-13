package onetree.ignacio.guerendiain.feature.tasks.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import onetree.ignacio.guerendiain.core.domain.model.Task
import onetree.ignacio.guerendiain.core.domain.repository.TaskRepository
import onetree.ignacio.guerendiain.core.utils.LocationService

class TasksViewModel(
    private val taskRespository: TaskRepository,
    private val locationService: LocationService
): ViewModel() {
    private val _state = MutableStateFlow(TasksState.DEFAULT)
    val state = _state.asStateFlow()

    fun selectTask(taskId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val foundTask = taskRespository.findTaskById(taskId)
            _state.update { it.copy(selectedTask = foundTask)}
        }
    }

    fun filterTasks(filter: TaskFilter){
        _state.update { it.copy(currentFilter = filter)}
        loadTasks()
    }

    fun deleteTask(taskId: Long){
        viewModelScope.launch(Dispatchers.IO){
            taskRespository.deleteTask(taskId)
            loadTasks()
        }
    }

    fun loadTasks(){
        viewModelScope.launch(Dispatchers.IO){
            val tasks = when(state.value.currentFilter){
                TaskFilter.DONE -> taskRespository.getDoneTasks()
                TaskFilter.UNDONE -> taskRespository.getUndoneTasks()
                TaskFilter.ALL -> taskRespository.getAllTasks()
            }
            _state.update { it.copy( listedTasks = tasks) }
        }
    }

    fun createTask(
        name: String,
        description: String,
        isDone: Boolean,
        latitude: Double?,
        longitude: Double?
    ){
        val now = Clock.System.now()

        viewModelScope.launch(Dispatchers.IO) {
            val location = if (latitude!=null && longitude!=null)
                Pair(latitude, longitude)
            else null

            val task = Task(
                id = 0,
                name = name,
                done = isDone,
                description = description,
                creationTime = now,
                updateTime = now,
                location = location
            )

            taskRespository.createTask(task)
            loadTasks()
        }
    }

    fun editTask(
        taskId: Long,
        name: String,
        description: String,
        isDone: Boolean,
        latitude: Double?,
        longitude: Double?
    ){
        val location = if (latitude!=null && longitude!=null)
            Pair(latitude, longitude)
        else null

        viewModelScope.launch(Dispatchers.IO){
            taskRespository
                .findTaskById(taskId)
                ?.copy(
                    name = name,
                    description = description,
                    done = isDone,
                    updateTime = Clock.System.now(),
                    location = location
                )?.let {
                    taskRespository.updateTask(it)
                    loadTasks()
                }
        }
    }

    fun toggleDone(taskId: Long) {
        viewModelScope.launch(Dispatchers.IO){
            taskRespository
                .findTaskById(taskId)
                ?.let { it.copy(done = !it.done) }
                ?.let {
                    taskRespository.updateTask(it)
                    loadTasks()
                }
        }
    }

    fun clearLocation() {
        _state.update{
            it.copy(
                clearedLocation = true,
                currentLocation = null,
                locationStatus = LocationStatus.NONE
            )
        }
    }

    fun setCurrentLocation(location: Pair<Double, Double>){
        _state.update{
            it.copy(
                clearedLocation = false,
                currentLocation = location,
                locationStatus = LocationStatus.EXISTING
            )
        }
    }

    fun reloadLocation() {
        if (locationService.isServiceAvailable())
            if (locationService.hasUserGivenPermission()) {
                _state.update { it.copy(locationStatus = LocationStatus.LOADING) }
                viewModelScope.launch(Dispatchers.IO) {
                    val location = locationService.getCurrentLocation()
                    _state.update {
                        it.copy(
                            locationStatus = LocationStatus.EXISTING,
                            clearedLocation = false,
                            currentLocation = location ?: it.currentLocation
                        )
                    }
                }
            }else{
                locationService.askUserForPermission { granted ->
                    if (granted) reloadLocation()
                    else _state.update {
                        it.copy(locationStatus = LocationStatus.NOT_PERMISSION)
                    }
                }
            }
        else _state.update { it.copy(locationStatus = LocationStatus.NOT_AVAILABLE)}
    }
}
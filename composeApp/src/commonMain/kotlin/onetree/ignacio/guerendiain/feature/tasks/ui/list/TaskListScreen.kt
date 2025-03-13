package onetree.ignacio.guerendiain.feature.tasks.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import onetree.ignacio.guerendiain.app.RootNavDestination
import onetree.ignacio.guerendiain.core.utils.GoogleMapsUtils
import onetree.ignacio.guerendiain.core.utils.OrientationService
import onetree.ignacio.guerendiain.core.utils.ScreenOrientation
import onetree.ignacio.guerendiain.feature.tasks.vm.TaskFilter
import onetree.ignacio.guerendiain.feature.tasks.vm.TasksViewModel
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.tasklist_filter_all
import onetreecodingchallenge.composeapp.generated.resources.tasklist_filter_done
import onetreecodingchallenge.composeapp.generated.resources.tasklist_filter_pending
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun TaskListScreen(
    navHostController: NavHostController,
    tasksViewModel: TasksViewModel = koinInject(),
    screenOrientationService: OrientationService = koinInject(),
){
    LaunchedEffect(Unit){
        tasksViewModel.loadTasks()
    }

    val tasksState by tasksViewModel.state.collectAsStateWithLifecycle()

    val uriHandler = LocalUriHandler.current

    val filters = arrayOf(
        Res.string.tasklist_filter_all,
        Res.string.tasklist_filter_done,
        Res.string.tasklist_filter_pending,
    )
        .map { stringResource(it) }
        .toTypedArray()

    val selectedFilter = when (tasksState.currentFilter){
        TaskFilter.DONE -> 1
        TaskFilter.UNDONE -> 2
        TaskFilter.ALL -> 0
    }

    val emptyType = when (tasksState.currentFilter){
        TaskFilter.DONE -> stringResource(Res.string.tasklist_filter_done)
        TaskFilter.UNDONE -> stringResource(Res.string.tasklist_filter_pending)
        TaskFilter.ALL -> null
    }

    val portrait = screenOrientationService.getOrientation() == ScreenOrientation.PORTRAIT

    TaskListContent(
        listedTasks = tasksState.listedTasks,
        filters = filters,
        selectedFilter = selectedFilter,
        emptyType = emptyType,
        portrait = portrait,

        onAddNew = {
            navHostController.navigate(RootNavDestination.TASKDETAILS.name)
        },
        onEditTask = {
            navHostController.navigate("${RootNavDestination.TASKDETAILS.name}/$it")
        },
        onToggleDone = { tasksViewModel.toggleDone(it) },
        onFilterSelected = {
            when (it) {
                0 -> TaskFilter.ALL
                1 -> TaskFilter.DONE
                2 -> TaskFilter.UNDONE
                else -> null
            }?.let { filter -> tasksViewModel.filterTasks(filter) }
        },
        onLocation = {
            uriHandler.openUri(
                GoogleMapsUtils.buildURLWithPin(it.first, it.second)
            )
        }
    )
}
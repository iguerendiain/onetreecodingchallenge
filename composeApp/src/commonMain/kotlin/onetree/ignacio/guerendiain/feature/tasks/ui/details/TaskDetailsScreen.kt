package onetree.ignacio.guerendiain.feature.tasks.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import onetree.ignacio.guerendiain.core.domain.model.Task
import onetree.ignacio.guerendiain.core.ui.dialog.WarningDialog
import onetree.ignacio.guerendiain.core.utils.GoogleMapsUtils
import onetree.ignacio.guerendiain.core.utils.OrientationService
import onetree.ignacio.guerendiain.core.utils.ScreenOrientation
import onetree.ignacio.guerendiain.feature.tasks.vm.TasksViewModel
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.common_cancel
import onetreecodingchallenge.composeapp.generated.resources.common_delete
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_create_title
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_deleteconfirm_description
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_deleteconfirm_title
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_edit_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun TaskDetailsScreen(
    task: Task? = null,
    navHostController: NavHostController,
    tasksViewModel: TasksViewModel = koinInject(),
    orientationService: OrientationService = koinInject()
){
    LaunchedEffect(task){
        if (task?.location==null) tasksViewModel.reloadLocation()
        else tasksViewModel.setCurrentLocation(task.location)
    }

    val uriHandler = LocalUriHandler.current
    val tasksState by tasksViewModel.state.collectAsStateWithLifecycle()
    val selectedTask = tasksState.selectedTask

    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    val title = stringResource(
        if (task==null) Res.string.taskdetails_create_title
        else Res.string.taskdetails_edit_title
    )
    val showDeleteButton = task!=null
    val initialName = selectedTask?.name?:""
    val initialDescription = selectedTask?.description?:""
    val initialDone = selectedTask?.done?:false
    val locationStatus = tasksState.locationStatus
    val portrait = orientationService.getOrientation() == ScreenOrientation.PORTRAIT

    TaskDetailsContent(
        title = title,
        showDeleteButton = showDeleteButton,
        initialName = initialName,
        initialDescription = initialDescription,
        initialDone = initialDone,
        locationStatus = locationStatus,
        portrait = portrait,

        onBack = { navHostController.navigateUp() },
        onDelete = { showDeleteConfirmationDialog = true },
        onSave = { name, description, isDone ->
            val location = if (tasksState.clearedLocation) null
                else tasksState.currentLocation

            if (task == null) tasksViewModel.createTask(
                name = name,
                description = description,
                isDone = isDone,
                latitude = location?.first,
                longitude = location?.second
            )
            else tasksViewModel.editTask(
                taskId = task.id,
                name = name,
                description = description,
                isDone = isDone,
                latitude = location?.first,
                longitude = location?.second
            )
            navHostController.navigateUp()
        },
        onLocation = {
            tasksState.currentLocation?.let {
                uriHandler.openUri(
                    GoogleMapsUtils.buildURLWithPin(it.first, it.second)
                )
            }
        },
        onClearLocation = { tasksViewModel.clearLocation() },
        onReloadLocation = { tasksViewModel.reloadLocation() },
    )

    if (showDeleteConfirmationDialog) WarningDialog(
        onDismissRequest = { showDeleteConfirmationDialog = false },
        titleRes = Res.string.taskdetails_deleteconfirm_title,
        descriptionRes = Res.string.taskdetails_deleteconfirm_description,
        retryButtonRes = Res.string.common_cancel,
        retryButtonCb = { showDeleteConfirmationDialog = false },
        cancelButtonRes = Res.string.common_delete,
        cancelButtonCb = {
            task?.id?.let { tasksViewModel.deleteTask(it) }
            showDeleteConfirmationDialog = false
            navHostController.navigateUp()
        }
    )
}
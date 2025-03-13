package onetree.ignacio.guerendiain.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import onetree.ignacio.guerendiain.core.utils.NavigationUtils.DEFAULT_ANIMATION_DURATION
import onetree.ignacio.guerendiain.core.utils.NavigationUtils.enterFromRightExitToLeft
import onetree.ignacio.guerendiain.feature.tasks.ui.details.TaskDetailsScreen
import onetree.ignacio.guerendiain.feature.tasks.ui.list.TaskListScreen
import onetree.ignacio.guerendiain.feature.tasks.vm.TasksViewModel
import org.koin.compose.koinInject

object RootNavArguments{
    const val ARGS_TASKID = "taskId"
}

enum class RootNavDestination(
    val arguments: List<NamedNavArgument> = listOf()
){
    TASKLIST,
    TASKDETAILS(
        arguments = listOf(
            navArgument(RootNavArguments.ARGS_TASKID)
            {
                type = NavType.LongType
                nullable = false
                defaultValue = -1
            }
        )
    )
}

@Composable
fun RootNavigation(){
    val navController = rememberNavController()
    val tasksViewModel: TasksViewModel = koinInject<TasksViewModel>()

    NavHost(
        navController = navController,
        startDestination = RootNavDestination.TASKLIST.name
    ){
        enterFromRightExitToLeft(
            route = RootNavDestination.TASKLIST.name,
            enterTransition = { fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)) },
        ){
            TaskListScreen(
                navHostController = navController,
                tasksViewModel = tasksViewModel
            )
        }

        enterFromRightExitToLeft(
            route = RootNavDestination.TASKDETAILS.name
        ){
            TaskDetailsScreen(navHostController = navController)
        }

        enterFromRightExitToLeft(
            route = "${RootNavDestination.TASKDETAILS.name}/{${RootNavArguments.ARGS_TASKID}}",
            arguments = RootNavDestination.TASKDETAILS.arguments
        ){
            val taskId = it
                .arguments
                ?.getLong(RootNavArguments.ARGS_TASKID)
                ?:-1

            if (taskId > 0){
                val tasksViewModel = koinInject<TasksViewModel>()
                val tasksState by tasksViewModel.state.collectAsStateWithLifecycle()
                tasksViewModel.selectTask(taskId)

                tasksState.selectedTask?.let { task ->
                    TaskDetailsScreen(
                        tasksViewModel = tasksViewModel,
                        task = task,
                        navHostController = navController
                    )
                }
            }
        }
    }
}
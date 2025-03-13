package onetree.ignacio.guerendiain.feature.tasks.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.domain.model.Task
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.ui.button.ToolbarTextButton
import onetree.ignacio.guerendiain.core.ui.common.ScreenTitle
import onetree.ignacio.guerendiain.core.ui.common.TagStrip
import onetree.ignacio.guerendiain.core.ui.common.Toolbar
import onetree.ignacio.guerendiain.core.ui.util.NavbarSpacer
import onetree.ignacio.guerendiain.core.ui.util.StatusbarSpacer
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.tasklist_add
import onetreecodingchallenge.composeapp.generated.resources.tasklist_title

@Composable
fun TaskListContent(
    listedTasks: List<Task>,
    filters: Array<String>,
    selectedFilter: Int,
    emptyType: String?,
    portrait: Boolean,

    onAddNew: () -> Unit,
    onEditTask: (Long) -> Unit,
    onToggleDone: (Long) -> Unit,
    onFilterSelected: (Int) -> Unit,
    onLocation: (Pair<Double, Double>) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(CurrentColorPalette.current.defaultScreenBackground)
            .then(
                if (portrait) Modifier
                else Modifier.safeContentPadding()
            )
    ){
        StatusbarSpacer()
        Toolbar(
            startContent = {},
            centerContent = { ScreenTitle(Res.string.tasklist_title) },
            endContent = {
                ToolbarTextButton(
                    res = Res.string.tasklist_add,
                    modifier = Modifier.clickable { onAddNew() }
                )
            }
        )

        TagStrip(
            tags = filters,
            selectedTag = selectedFilter,
            onSelectedTag = onFilterSelected
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ){

            if (listedTasks.isEmpty()) EmptyTasklist(
                modifier = Modifier.fillMaxWidth(),
                onAddNew = onAddNew,
                emptyType = emptyType
            )else LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(listedTasks.size) {
                    val task = listedTasks[it]

                    TaskItem(
                        name = task.name,
                        location = task.location,
                        description = task.description,
                        done = task.done,
                        onLocation = {
                            task.location?.let { location -> onLocation(location) }
                        },
                        onSelected = { onEditTask(task.id) },
                        onToggleDone = { onToggleDone(task.id) },
                    )
                }

                item { NavbarSpacer() }
            }
        }
    }
}

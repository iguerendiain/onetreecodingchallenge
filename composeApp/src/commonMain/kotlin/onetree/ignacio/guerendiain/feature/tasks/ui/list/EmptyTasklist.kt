package onetree.ignacio.guerendiain.feature.tasks.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.tasklist_empty
import onetreecodingchallenge.composeapp.generated.resources.tasklist_empty_create
import onetreecodingchallenge.composeapp.generated.resources.tasklist_empty_filtered
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyTasklist(
    modifier: Modifier = Modifier,
    emptyType: String?,
    onAddNew: ()-> Unit
){
    val solvedText = if (emptyType == null) stringResource(Res.string.tasklist_empty)
    else stringResource(Res.string.tasklist_empty_filtered).replace("%s", emptyType)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ){
        Text(
            text = solvedText,
            style = CurrentTypographyDefs.current.defaultBigText.copy(
                color = CurrentColorPalette.current.defaultText
            )
        )
        Text(
            text = stringResource(Res.string.tasklist_empty_create),
            style = CurrentTypographyDefs.current.defaultBigText.copy(
                color = CurrentColorPalette.current.textLink
            ),
            modifier = Modifier.clickable { onAddNew() }
        )
    }
}
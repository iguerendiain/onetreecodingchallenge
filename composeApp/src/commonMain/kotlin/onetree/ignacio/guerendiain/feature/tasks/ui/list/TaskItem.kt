package onetree.ignacio.guerendiain.feature.tasks.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs
import onetree.ignacio.guerendiain.core.ui.button.MainButton
import onetree.ignacio.guerendiain.core.ui.button.MainButtonText
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.ic_check_circle
import onetreecodingchallenge.composeapp.generated.resources.ic_close_circle
import onetreecodingchallenge.composeapp.generated.resources.ic_location
import onetreecodingchallenge.composeapp.generated.resources.tasklist_button_setdone
import onetreecodingchallenge.composeapp.generated.resources.tasklist_button_setundone
import org.jetbrains.compose.resources.painterResource

@Composable
fun TaskItem(
    name: String,
    location: Pair<Double, Double>?,
    description: String?,
    done: Boolean,

    onSelected: () -> Unit,
    onToggleDone: () -> Unit,
    onLocation: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = CurrentColorPalette.current.defaultScreenBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                color = CurrentColorPalette.current.cardBorder,
                shape = RoundedCornerShape(8.dp),
                width = 1.dp
            )
            .clickable { onSelected() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Text(
                text = name,
                style = CurrentTypographyDefs.current.itemTitle.copy(
                    color = CurrentColorPalette.current.defaultText
                ),
                modifier = Modifier.weight(1f)
            )
            if (location!=null)
                Image(
                    painter = painterResource(Res.drawable.ic_location),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(CurrentColorPalette.current.importantText),
                    modifier = Modifier.clickable { onLocation() }
                )
        }
        Spacer(Modifier.size(12.dp))

        if (description!=null){
            Text(
                text = description,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                style = CurrentTypographyDefs.current.itemSubtitle.copy(
                    color = CurrentColorPalette.current.itemSubtitle
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.size(12.dp))
        }

        MainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonColor = if (done)
                CurrentColorPalette.current.importantButtonBg
            else
                CurrentColorPalette.current.mainButtonColorBg,
            leftContent = {
                if (done) Image(
                    painter = painterResource(Res.drawable.ic_close_circle),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(CurrentColorPalette.current.importantButtonFg)
                )else Image(
                    painter = painterResource(Res.drawable.ic_check_circle),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(CurrentColorPalette.current.mainButtonColorFg)
                )
            },
            centerContent = {
                MainButtonText(
                    textResource = if (done)
                        Res.string.tasklist_button_setundone
                    else
                        Res.string.tasklist_button_setdone,
                    color = if (done)
                        CurrentColorPalette.current.importantButtonFg
                    else
                        CurrentColorPalette.current.mainButtonColorFg,
                )
            },
            cb = onToggleDone,
        )
    }
}
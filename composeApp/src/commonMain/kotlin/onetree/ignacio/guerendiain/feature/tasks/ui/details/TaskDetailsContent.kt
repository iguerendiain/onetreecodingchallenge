package onetree.ignacio.guerendiain.feature.tasks.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import onetree.ignacio.guerendiain.core.theme.CurrentColorPalette
import onetree.ignacio.guerendiain.core.theme.CurrentTypographyDefs
import onetree.ignacio.guerendiain.core.ui.button.BackButton
import onetree.ignacio.guerendiain.core.ui.button.MainButton
import onetree.ignacio.guerendiain.core.ui.button.ToolbarTextButton
import onetree.ignacio.guerendiain.core.ui.common.MainTextField
import onetree.ignacio.guerendiain.core.ui.common.ScreenTitle
import onetree.ignacio.guerendiain.core.ui.common.Toolbar
import onetree.ignacio.guerendiain.core.ui.util.NavbarSpacer
import onetree.ignacio.guerendiain.core.ui.util.StatusbarSpacer
import onetree.ignacio.guerendiain.feature.tasks.vm.LocationStatus
import onetreecodingchallenge.composeapp.generated.resources.Res
import onetreecodingchallenge.composeapp.generated.resources.common_delete
import onetreecodingchallenge.composeapp.generated.resources.ic_close_circle
import onetreecodingchallenge.composeapp.generated.resources.ic_location
import onetreecodingchallenge.composeapp.generated.resources.ic_refresh_circle
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_button_save
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_input_description_label
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_input_name_label
import onetreecodingchallenge.composeapp.generated.resources.taskdetails_switch_done_label
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TaskDetailsContent(
    title: String,
    showDeleteButton: Boolean,
    initialName: String,
    initialDescription: String,
    initialDone: Boolean,
    locationStatus: LocationStatus,
    portrait: Boolean,

    onBack: () -> Unit,
    onDelete: () -> Unit,
    onLocation: () -> Unit,
    onClearLocation: () -> Unit,
    onReloadLocation: () -> Unit,
    onSave: (String, String, Boolean) -> Unit
){
    var nameInput by remember { mutableStateOf(initialName) }
    var descriptionInput by remember { mutableStateOf(initialDescription) }
    var isDone by remember { mutableStateOf(initialDone) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(CurrentColorPalette.current.defaultScreenBackground)
            .imePadding()
            .safeContentPadding()
    ){
        StatusbarSpacer()
        Toolbar(
            startContent = {
                BackButton(modifier = Modifier.clickable { onBack() })
            },
            centerContent = { ScreenTitle(title) },
            endContent = {
                if (showDeleteButton) ToolbarTextButton(
                    res = Res.string.common_delete,
                    modifier = Modifier.clickable { onDelete() }
                )
            }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ){
            Text(
                text = stringResource(Res.string.taskdetails_input_name_label),
                style = CurrentTypographyDefs.current.defaultMediumText.copy(
                    color = CurrentColorPalette.current.defaultText,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.fillMaxWidth()
            )

            MainTextField(
                value = nameInput,
                onValueChange = { nameInput = it}
            )

            Spacer(Modifier.size(12.dp))

            Text(
                text = stringResource(Res.string.taskdetails_input_description_label),
                style = CurrentTypographyDefs.current.defaultMediumText.copy(
                    color = CurrentColorPalette.current.defaultText,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (portrait) MainTextField(
                value = descriptionInput,
                onValueChange = { descriptionInput = it },
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) else MainTextField(
                value = descriptionInput,
                onValueChange = { descriptionInput = it },
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(60.dp).fillMaxWidth()
            ){
                Switch(
                    checked = isDone,
                    onCheckedChange = { isDone = it },
                    modifier = Modifier,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = CurrentColorPalette.current.mainButtonColorBg,
                        uncheckedThumbColor = CurrentColorPalette.current.disabledButtonBg,
                        uncheckedTrackColor = CurrentColorPalette.current.disabledButtonBg,
                    )
                )

                Text(
                    text = stringResource(Res.string.taskdetails_switch_done_label),
                    style = CurrentTypographyDefs.current.defaultMediumText.copy(
                        color = CurrentColorPalette.current.defaultText,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(Modifier.weight(1f))

                when (locationStatus){
                    LocationStatus.LOADING -> {
                        CircularProgressIndicator(
                            color = CurrentColorPalette.current.lightText,
                            modifier = Modifier.size(24.dp)
                        )

                        Image(
                            painter = painterResource(Res.drawable.ic_location),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(CurrentColorPalette.current.lightText)
                        )
                    }
                    LocationStatus.EXISTING -> {
                        Image(
                            contentDescription = null,
                            painter = painterResource(Res.drawable.ic_refresh_circle),
                            colorFilter = ColorFilter.tint(CurrentColorPalette.current.mainButtonColorBg),
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable { onReloadLocation() }
                        )

                        Image(
                            contentDescription = null,
                            painter = painterResource(Res.drawable.ic_close_circle),
                            colorFilter = ColorFilter.tint(CurrentColorPalette.current.importantButtonBg),
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable { onClearLocation() }
                        )

                        Image(
                            painter = painterResource(Res.drawable.ic_location),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(CurrentColorPalette.current.importantButtonBg),
                            modifier = Modifier.clickable { onLocation() }
                        )
                    }
                    LocationStatus.NOT_PERMISSION, LocationStatus.NONE -> {
                        Image(
                            contentDescription = null,
                            painter = painterResource(Res.drawable.ic_refresh_circle),
                            colorFilter = ColorFilter.tint(CurrentColorPalette.current.mainButtonColorBg),
                            modifier = Modifier
                                .fillMaxHeight()
                                .clickable { onReloadLocation() }
                        )

                        Image(
                            painter = painterResource(Res.drawable.ic_location),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(CurrentColorPalette.current.lightText)
                        )
                    }

                    LocationStatus.NOT_AVAILABLE -> {}
                }
            }

            Spacer(Modifier.size(12.dp))

            MainButton(
                text = stringResource(Res.string.taskdetails_button_save),
                buttonColor = CurrentColorPalette.current.mainButtonColorBg,
                fgColor = CurrentColorPalette.current.mainButtonColorFg,
                enabled = nameInput.isNotBlank(),
                cb = { onSave(nameInput, descriptionInput, isDone) }
            )

            Spacer(Modifier.size(12.dp))
        }

        NavbarSpacer()
    }

}
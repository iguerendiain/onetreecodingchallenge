package onetree.ignacio.guerendiain.core.ui.dialog

import androidx.compose.runtime.Composable

@Composable
fun DefaultDialog(
    content: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    usePlatformDefaultWidth: Boolean = true,
){
    BaseDialog(
        content = { DefaultDialogContainer(content) },
        onDismissRequest = onDismissRequest,
        dismissOnBackPress = dismissOnBackPress,
        dismissOnClickOutside = dismissOnClickOutside,
        usePlatformDefaultWidth = usePlatformDefaultWidth
    )
}
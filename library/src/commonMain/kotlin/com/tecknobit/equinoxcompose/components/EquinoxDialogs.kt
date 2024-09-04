package com.tecknobit.equinoxcompose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.tecknobit.equinoxcompose.helpers.EquinoxViewModel
import com.tecknobit.library.generated.resources.Res
import com.tecknobit.library.generated.resources.confirm
import com.tecknobit.library.generated.resources.dismiss
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Function to display a custom [AlertDialog]
 *
 * @param modifier: the modifier to apply to the [AlertDialog]
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
 * @param viewModel: the viewmodel, if available, used in the context where the [AlertDialog] has been invoked, passing
 * it allows to manage in automatically the refresher, so suspend it or restarting it
 * @param onDismissAction: the action to execute when the alert dialog has been dismissed
 * @param title: the title of the alert dialog
 * @param text: the text displayed in the alert dialog
 * @param dismissAction: the action to execute when the user dismissed the action
 * @param dismissText: the text of the dismiss [TextButton]
 * @param confirmAction: the action to execute when the used confirmed the action
 * @param confirmText: the text of the confirm [TextButton]
 */
@Composable
fun EquinoxAlertDialog(
    modifier: Modifier = Modifier,
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    viewModel: EquinoxViewModel? = null,
    onDismissAction: () -> Unit = {
        show.value = false
        viewModel?.restartRefresher()
    },
    title: StringResource,
    text: StringResource,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: StringResource = Res.string.dismiss,
    confirmAction: () -> Unit,
    confirmText: StringResource = Res.string.confirm
) {
    EquinoxAlertDialog(
        modifier = modifier,
        show = show,
        icon = icon,
        viewModel = viewModel,
        onDismissAction = onDismissAction,
        title = title,
        text = {
            Text(
                text = stringResource(text),
                textAlign = TextAlign.Justify
            )
        },
        dismissAction = dismissAction,
        dismissText = dismissText,
        confirmAction = confirmAction,
        confirmText = confirmText
    )
}

/**
 * Function to display a custom [AlertDialog]
 *
 * @param modifier: the modifier to apply to the [AlertDialog]
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
 * @param viewModel: the viewmodel, if available, used in the context where the [AlertDialog] has been invoked, passing
 * it allows to manage in automatically the refresher, so suspend it or restarting it
 * @param onDismissAction: the action to execute when the alert dialog has been dismissed
 * @param title: the title of the alert dialog
 * @param text: the text displayed in the alert dialog
 * @param dismissAction: the action to execute when the user dismissed the action
 * @param dismissText: the text of the dismiss [TextButton]
 * @param confirmAction: the action to execute when the used confirmed the action
 * @param confirmText: the text of the confirm [TextButton]
 */
@Composable
fun EquinoxAlertDialog(
    modifier: Modifier = Modifier,
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    viewModel: EquinoxViewModel? = null,
    onDismissAction: () -> Unit = {
        show.value = false
        viewModel?.restartRefresher()
    },
    title: String,
    text: String,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: String,
    confirmAction: () -> Unit,
    confirmText: String
) {
    EquinoxAlertDialog(
        modifier = modifier,
        show = show,
        icon = icon,
        viewModel = viewModel,
        onDismissAction = onDismissAction,
        title = title,
        text = {
            Text(
                text = text,
                textAlign = TextAlign.Justify
            )
        },
        dismissAction = dismissAction,
        dismissText = dismissText,
        confirmAction = confirmAction,
        confirmText = confirmText
    )
}

/**
 * Function to display a custom [AlertDialog]
 *
 * @param modifier: the modifier to apply to the [AlertDialog]
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
 * @param viewModel: the viewmodel, if available, used in the context where the [AlertDialog] has been invoked, passing
 * it allows to manage in automatically the refresher, so suspend it or restarting it
 * @param onDismissAction: the action to execute when the alert dialog has been dismissed
 * @param title: the title of the alert dialog
 * @param text: the text displayed in the alert dialog
 * @param dismissAction: the action to execute when the user dismissed the action
 * @param dismissText: the text of the dismiss [TextButton]
 * @param confirmAction: the action to execute when the used confirmed the action
 * @param confirmText: the text of the confirm [TextButton]
 */
@Composable
fun EquinoxAlertDialog(
    modifier: Modifier = Modifier,
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    viewModel: EquinoxViewModel? = null,
    onDismissAction: () -> Unit = {
        show.value = false
        viewModel?.restartRefresher()
    },
    title: StringResource,
    text: @Composable () -> Unit,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: StringResource = Res.string.dismiss,
    confirmAction: () -> Unit,
    confirmText: StringResource = Res.string.confirm
) {
    EquinoxAlertDialog(
        modifier = modifier,
        show = show,
        icon = icon,
        viewModel = viewModel,
        onDismissAction = onDismissAction,
        title = stringResource(title),
        text = text,
        dismissAction = dismissAction,
        dismissText = stringResource(dismissText),
        confirmAction = confirmAction,
        confirmText = stringResource(confirmText)
    )
}

/**
 * Function to display a custom [AlertDialog]
 *
 * @param modifier: the modifier to apply to the [AlertDialog]
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
 * @param viewModel: the viewmodel, if available, used in the context where the [AlertDialog] has been invoked, passing
 * it allows to manage in automatically the refresher, so suspend it or restarting it
 * @param onDismissAction: the action to execute when the alert dialog has been dismissed
 * @param title: the title of the alert dialog
 * @param text: the text displayed in the alert dialog
 * @param dismissAction: the action to execute when the user dismissed the action
 * @param dismissText: the text of the dismiss [TextButton]
 * @param confirmAction: the action to execute when the used confirmed the action
 * @param confirmText: the text of the confirm [TextButton]
 */
@Composable
fun EquinoxAlertDialog(
    modifier: Modifier = Modifier,
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    viewModel: EquinoxViewModel? = null,
    onDismissAction: () -> Unit = {
        show.value = false
        viewModel?.restartRefresher()
    },
    title: String,
    text: @Composable () -> Unit,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: String,
    confirmAction: () -> Unit,
    confirmText: String
) {
    if(show.value) {
        viewModel?.suspendRefresher()
        AlertDialog(
            modifier = modifier,
            icon = {
                if(icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            },
            onDismissRequest = onDismissAction,
            title = {
                Text(
                    text = title
                )
            },
            text = text,
            dismissButton = {
                TextButton(
                    onClick = dismissAction
                ) {
                    Text(
                        text = dismissText
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmAction.invoke()
                        viewModel?.restartRefresher()
                    }
                ) {
                    Text(
                        text = confirmText
                    )
                }
            }
        )
    }
}
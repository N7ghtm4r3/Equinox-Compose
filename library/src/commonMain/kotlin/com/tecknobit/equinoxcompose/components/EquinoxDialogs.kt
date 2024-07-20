package com.tecknobit.equinoxcompose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.tecknobit.library.generated.resources.Res
import com.tecknobit.library.generated.resources.confirm
import com.tecknobit.library.generated.resources.dismiss
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Function to display a custom [AlertDialog]
 *
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
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
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    onDismissAction: () -> Unit = { show.value = false },
    title: StringResource,
    text: StringResource,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: StringResource = Res.string.dismiss,
    confirmAction: () -> Unit,
    confirmText: StringResource = Res.string.confirm
) {
    EquinoxAlertDialog(
        show = show,
        icon = icon,
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
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
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
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    onDismissAction: () -> Unit = { show.value = false },
    title: String,
    text: String,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: String,
    confirmAction: () -> Unit,
    confirmText: String
) {
    EquinoxAlertDialog(
        show = show,
        icon = icon,
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
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
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
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    onDismissAction: () -> Unit = { show.value = false },
    title: StringResource,
    text: @Composable () -> Unit,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: StringResource = Res.string.dismiss,
    confirmAction: () -> Unit,
    confirmText: StringResource = Res.string.confirm
) {
    EquinoxAlertDialog(
        show = show,
        icon = icon,
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
 * @param show: whether show the alert dialog
 * @param icon: the icon of the alert dialog
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
    show: MutableState<Boolean>,
    icon: ImageVector? = null,
    onDismissAction: () -> Unit = { show.value = false },
    title: String,
    text: @Composable () -> Unit,
    dismissAction: () -> Unit = onDismissAction,
    dismissText: String,
    confirmAction: () -> Unit,
    confirmText: String
) {
    if(show.value) {
        AlertDialog(
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
                    onClick = confirmAction
                ) {
                    Text(
                        text = confirmText
                    )
                }
            }
        )
    }
}
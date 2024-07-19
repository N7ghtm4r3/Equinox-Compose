package com.tecknobit.equinoxcompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.tecknobit.library.generated.resources.Res
import com.tecknobit.library.generated.resources.an_error_occurred
import com.tecknobit.library.generated.resources.retry
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Function to display a layout when a list of values is empty
 *
 * @param icon: the icon to display
 * @param subText: the description of the layout
 */
@Composable
fun EmptyListUI(
    icon: ImageVector,
    subText: StringResource
) {
    EmptyListUI(
        icon = icon,
        subText = stringResource(subText)
    )
}

/**
 * Function to display a layout when a list of values is empty
 *
 * @param icon: the icon to display
 * @param subText: the description of the layout
 */
@Composable
fun EmptyListUI(
    icon: ImageVector,
    subText: String
) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(100.dp),
            imageVector = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = subText,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

/**
 * Function to display a layout when an error occurred
 *
 * @param retryAction: the retry action to execute
 */
@Composable
fun ErrorUI(
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.errorContainer,
    errorMessage: StringResource = Res.string.an_error_occurred,
    retryAction: @Composable (() -> Unit)? = null,
    retryText: StringResource = Res.string.retry
) {
    ErrorUI(
        errorIcon = errorIcon,
        errorColor = errorColor,
        errorMessage = stringResource(errorMessage),
        retryAction = retryAction,
        retryText = stringResource(retryText)
    )
}

/**
 * Function to display a layout when an error occurred
 *
 * @param retryAction: the retry action to execute
 */
@Composable
fun ErrorUI(
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.errorContainer,
    errorMessage: String = stringResource(Res.string.an_error_occurred),
    retryAction: @Composable (() -> Unit)? = null,
    retryText: String = stringResource(Res.string.retry)
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(100.dp),
            imageVector = errorIcon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = errorColor
            )
        )
        Text(
            text = errorMessage,
            color = errorColor
        )
        if(retryAction != null) {
            var retryActionStart by remember { mutableStateOf(false) }
            TextButton(
                onClick = { retryActionStart = true }
            ) {
                Text(
                    text = retryText
                )
            }
            if(retryActionStart)
                retryAction.invoke()
        }
    }
}
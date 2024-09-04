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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.tecknobit.equinoxcompose.resources.Res
import com.tecknobit.equinoxcompose.resources.an_error_occurred
import com.tecknobit.equinoxcompose.resources.retry
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Function to display a layout when a list of values is empty
 *
 * @param containerModifier: the modifier to apply to the container column
 * @param imageModifier: the modifier to apply to the image icon
 * @param textStyle: the style to apply to the text
 * @param icon: the icon to display
 * @param themeColor: the color to use into the composable
 * @param subText: the description of the layout
 */
@Composable
@NonRestartableComposable
fun EmptyListUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    icon: ImageVector,
    themeColor: Color = MaterialTheme.colorScheme.primary,
    subText: StringResource
) {
    EmptyListUI(
        textStyle = textStyle,
        containerModifier = containerModifier,
        imageModifier = imageModifier,
        icon = icon,
        themeColor = themeColor,
        subText = stringResource(subText)
    )
}

/**
 * Function to display a layout when a list of values is empty
 *
 * @param containerModifier: the modifier to apply to the container column
 * @param imageModifier: the modifier to apply to the image icon
 * @param textStyle: the style to apply to the text
 * @param icon: the icon to display
 * @param themeColor: the color to use into the composable
 * @param subText: the description of the layout
 */
@Composable
@NonRestartableComposable
fun EmptyListUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    icon: ImageVector,
    themeColor: Color = MaterialTheme.colorScheme.primary,
    subText: String
) {
    Column (
        modifier = containerModifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = imageModifier
                .size(100.dp),
            imageVector = icon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = themeColor
            )
        )
        Text(
            style = textStyle,
            text = subText,
            color = themeColor
        )
    }
}

/**
 * Function to display a layout when an error occurred
 *
 * @param containerModifier: the modifier to apply to the container column
 * @param imageModifier: the modifier to apply to the image icon
 * @param textStyle: the style to apply to the text
 * @param errorIcon: the error icon used, as default is used the **Icons.Default.Error**
 * @param errorColor: the error color used, as default is used the **MaterialTheme.colorScheme.errorContainer**
 * @param errorMessage: the error that occurred or to indicate a generic error
 * @param retryAction: the retry action to execute
 * @param retryText: the retry message
 */
@Composable
@NonRestartableComposable
fun ErrorUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.errorContainer,
    errorMessage: StringResource = Res.string.an_error_occurred,
    retryAction: @Composable (() -> Unit)? = null,
    retryText: StringResource = Res.string.retry
) {
    ErrorUI(
        containerModifier = containerModifier,
        imageModifier = imageModifier,
        textStyle = textStyle,
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
 * @param containerModifier: the modifier to apply to the container column
 * @param imageModifier: the modifier to apply to the image icon
 * @param textStyle: the style to apply to the text
 * @param errorIcon: the error icon used, as default is used the **Icons.Default.Error**
 * @param errorColor: the error color used, as default is used the **MaterialTheme.colorScheme.errorContainer**
 * @param errorMessage: the error that occurred or to indicate a generic error
 * @param retryAction: the retry action to execute
 * @param retryText: the retry message
 */
@Composable
@NonRestartableComposable
fun ErrorUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.errorContainer,
    errorMessage: String,
    retryAction: @Composable (() -> Unit)? = null,
    retryText: String? = null
) {
    Column (
        modifier = containerModifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = imageModifier
                .size(100.dp),
            imageVector = errorIcon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = errorColor
            )
        )
        Text(
            style = textStyle,
            text = errorMessage,
            color = errorColor
        )
        if(retryAction != null && retryText != null) {
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
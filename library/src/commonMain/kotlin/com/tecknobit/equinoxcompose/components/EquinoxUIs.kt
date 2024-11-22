package com.tecknobit.equinoxcompose.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
 * Method to display a layout when a list of values is empty
 *
 * @param containerModifier The modifier to apply to the container column
 * @param imageModifier The modifier to apply to the image icon
 * @param animations The set of the animations to use to animate the layout
 * @param textStyle The style to apply to the text
 * @param icon The icon to display
 * @param themeColor The color to use into the composable
 * @param subText The description of the layout
 */
@Composable
@NonRestartableComposable
fun EmptyListUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    animations: UIAnimations? = null,
    textStyle: TextStyle = TextStyle.Default,
    icon: ImageVector,
    themeColor: Color = MaterialTheme.colorScheme.primary,
    subText: StringResource
) {
    EmptyListUI(
        containerModifier = containerModifier,
        imageModifier = imageModifier,
        animations = animations,
        textStyle = textStyle,
        icon = icon,
        themeColor = themeColor,
        subText = stringResource(subText)
    )
}

/**
 * Method to display a layout when a list of values is empty
 *
 * @param containerModifier The modifier to apply to the container column
 * @param imageModifier The modifier to apply to the image icon
 * @param animations The set of the animations to use to animate the layout
 * @param textStyle The style to apply to the text
 * @param icon The icon to display
 * @param themeColor The color to use into the composable
 * @param subText The description of the layout
 */
@Composable
@NonRestartableComposable
fun EmptyListUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    animations: UIAnimations? = null,
    textStyle: TextStyle = TextStyle.Default,
    icon: ImageVector,
    themeColor: Color = MaterialTheme.colorScheme.primary,
    subText: String
) {
    if(animations != null) {
        AnimatedVisibility(
            visible = animations.visible,
            enter = animations.onEnter,
            exit = animations.onExit
        ) {
            EmptyListUIContent(
                containerModifier = containerModifier,
                imageModifier = imageModifier,
                textStyle = textStyle,
                icon = icon,
                themeColor = themeColor,
                subText = subText
            )
        }
    } else {
        EmptyListUIContent(
            containerModifier = containerModifier,
            imageModifier = imageModifier,
            textStyle = textStyle,
            icon = icon,
            themeColor = themeColor,
            subText = subText
        )
    }
}

/**
 * Method to display the content of the [EmptyListUI]
 *
 * @param containerModifier The modifier to apply to the container column
 * @param imageModifier The modifier to apply to the image icon
 * @param textStyle The style to apply to the text
 * @param icon The icon to display
 * @param themeColor The color to use into the composable
 * @param subText The description of the layout
 */
@Composable
@NonRestartableComposable
private fun EmptyListUIContent(
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
 * Method to display a layout when an error occurred
 *
 * @param containerModifier The modifier to apply to the container column
 * @param imageModifier The modifier to apply to the image icon
 * @param animations The set of the animations to use to animate the layout
 * @param textStyle The style to apply to the text
 * @param errorIcon The error icon used, as default is used the **Icons.Default.Error**
 * @param errorColor The error color used, as default is used the **MaterialTheme.colorScheme.errorContainer**
 * @param errorMessage The error that occurred or to indicate a generic error
 * @param retryAction The retry action to execute
 * @param retryText The retry message
 */
@Composable
@NonRestartableComposable
fun ErrorUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    animations: UIAnimations? = null,
    textStyle: TextStyle = TextStyle.Default,
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.error,
    errorMessage: StringResource = Res.string.an_error_occurred,
    retryAction: @Composable (() -> Unit)? = null,
    retryText: StringResource = Res.string.retry
) {
    ErrorUI(
        containerModifier = containerModifier,
        imageModifier = imageModifier,
        animations = animations,
        textStyle = textStyle,
        errorIcon = errorIcon,
        errorColor = errorColor,
        errorMessage = stringResource(errorMessage),
        retryAction = retryAction,
        retryText = stringResource(retryText)
    )
}

/**
 * Method to display the layout when an error occurred
 *
 * @param containerModifier The modifier to apply to the container column
 * @param imageModifier The modifier to apply to the image icon
 * @param animations The set of the animations to use to animate the layout
 * @param textStyle The style to apply to the text
 * @param errorIcon The error icon used, as default is used the **Icons.Default.Error**
 * @param errorColor The error color used, as default is used the **MaterialTheme.colorScheme.errorContainer**
 * @param errorMessage The error that occurred or to indicate a generic error
 * @param retryAction The retry action to execute
 * @param retryText The retry message
 */
@Composable
@NonRestartableComposable
fun ErrorUI(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    animations: UIAnimations? = null,
    textStyle: TextStyle = TextStyle.Default,
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.error,
    errorMessage: String,
    retryAction: @Composable (() -> Unit)? = null,
    retryText: String? = null
) {
    if(animations != null) {
        AnimatedVisibility(
            visible = animations.visible,
            enter = animations.onEnter,
            exit = animations.onExit
        ) {
            ErrorUIContent(
                containerModifier = containerModifier,
                imageModifier = imageModifier,
                textStyle = textStyle,
                errorIcon = errorIcon,
                errorColor = errorColor,
                errorMessage = errorMessage,
                retryAction = retryAction,
                retryText = retryText
            )
        }
    } else {
        ErrorUIContent(
            containerModifier = containerModifier,
            imageModifier = imageModifier,
            textStyle = textStyle,
            errorIcon = errorIcon,
            errorColor = errorColor,
            errorMessage = errorMessage,
            retryAction = retryAction,
            retryText = retryText
        )
    }
}

/**
 * Method to display the content of the [ErrorUI] layout
 *
 * @param containerModifier The modifier to apply to the container column
 * @param imageModifier The modifier to apply to the image icon
 * @param textStyle The style to apply to the text
 * @param errorIcon The error icon used, as default is used the **Icons.Default.Error**
 * @param errorColor The error color used, as default is used the **MaterialTheme.colorScheme.errorContainer**
 * @param errorMessage The error that occurred or to indicate a generic error
 * @param retryAction The retry action to execute
 * @param retryText The retry message
 */
@Composable
@NonRestartableComposable
private fun ErrorUIContent(
    containerModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    errorIcon: ImageVector = Icons.Default.Error,
    errorColor: Color = MaterialTheme.colorScheme.error,
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
            if(retryActionStart) {
                retryAction.invoke()
                retryActionStart = false
            }
        }
    }
}

/**
 * Data class to use to animate a UI layout
 *
 * @property visible Whether the layout is visible or not
 * @property onEnter The [EnterTransition] to use
 * @property onExit The [ExitTransition] to use
 */
data class UIAnimations(
    val visible: Boolean,
    val onEnter: EnterTransition,
    val onExit: ExitTransition
)
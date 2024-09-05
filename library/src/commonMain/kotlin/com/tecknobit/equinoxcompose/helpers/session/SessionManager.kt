package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.tecknobit.equinoxcompose.components.ErrorUI
import com.tecknobit.equinoxcompose.helpers.viewmodels.EquinoxViewModel
import com.tecknobit.equinoxcompose.resources.Res
import com.tecknobit.equinoxcompose.resources.no_internet
import com.tecknobit.equinoxcompose.resources.no_internet_connection
import com.tecknobit.equinoxcompose.resources.server_currently_offline
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

/**
 * *sessionSetup* -> the setup for the session
 */
private lateinit var sessionSetup: SessionSetup

/**
 * *isServerOffline* -> state to manage the server offline scenario
 */
private lateinit var isServerOffline: MutableState<Boolean>

/**
 * *noInternetConnection* -> state to manage the no internet connection scenario
 */
private lateinit var noInternetConnection: MutableState<Boolean>

/**
 * *hasBeenDisconnectedAction* -> when the account has been deleted and the session needs to
 * be detached from the device
 */
private lateinit var hasBeenDisconnected: MutableState<Boolean>

/**
 * Function to set up the [sessionSetup] instance
 *
 * @param serverOfflineMessage: the message to use when the server is offline
 * @param serverOfflineIcon: the icon to use when the server is offline
 * @param noInternetConnectionMessage: the message to use when the internet connection is not available
 * @param noInternetConnectionIcon: the icon to use when the internet connection is not available
 * @param hasBeenDisconnectedAction: the action to execute when the user has been disconnected
 */
@Composable
fun setUpSession(
    serverOfflineMessage: String = stringResource(Res.string.server_currently_offline),
    serverOfflineIcon: ImageVector = Icons.Default.Warning,
    noInternetConnectionMessage: String = stringResource(Res.string.no_internet_connection),
    noInternetConnectionIcon: ImageVector = vectorResource(Res.drawable.no_internet),
    hasBeenDisconnectedAction: () -> Unit
) {
    setUpSession(
        sessionSetupValue = SessionSetup(
            serverOfflineMessage = serverOfflineMessage,
            serverOfflineIcon = serverOfflineIcon,
            noInternetConnectionMessage = noInternetConnectionMessage,
            noInternetConnectionIcon = noInternetConnectionIcon,
            hasBeenDisconnectedAction = hasBeenDisconnectedAction
        )
    )
}

/**
 * Function to set up the [sessionSetup] instance
 *
 * @param sessionSetupValue: the setup to use for the current session
 */
fun setUpSession(
    sessionSetupValue: SessionSetup
) {
    sessionSetup = sessionSetupValue
}

/**
 * Function to set the value of the [isServerOffline] state, when the value is _true_ will be invoked
 * the [ServerOfflineUi] method, when _false_ will be displayed the normal content
 *
 * @param isServerOfflineValue: the value to set
 */
fun setServerOfflineValue(
    isServerOfflineValue: Boolean
) {
    if(::isServerOffline.isInitialized) {
        MainScope().launch {
            isServerOffline.value = isServerOfflineValue
        }
    }
}

/**
 * Function to set the value of the [hasBeenDisconnectedAction] state, when the value is _true_ will be invoked
 * the [hasBeenDisconnectedAction] method, when _false_ will be displayed the normal content
 *
 * @param hasBeenDisconnectedValue: the value to set
 */
fun setHasBeenDisconnectedValue(
    hasBeenDisconnectedValue: Boolean
) {
    if(::hasBeenDisconnected.isInitialized) {
        MainScope().launch {
            hasBeenDisconnected.value = hasBeenDisconnectedValue
        }
    }
}

/**
 * The **SessionSetup** class is useful to create a setup for the current session
 *
 * @param serverOfflineMessage: the message to use when the server is offline
 * @param serverOfflineIcon: the icon to use when the server is offline
 * @param noInternetConnectionMessage: the message to use when the internet connection is not available
 * @param noInternetConnectionIcon: the icon to use when the internet connection is not available
 * @param hasBeenDisconnectedAction: the action to execute when the user has been disconnected
 */
data class SessionSetup(
    val serverOfflineMessage: String,
    val serverOfflineIcon: ImageVector,
    val noInternetConnectionMessage: String,
    val noInternetConnectionIcon: ImageVector,
    val hasBeenDisconnectedAction: () -> Unit
)

/**
 * Function to display the correct content based on the current scenario such server offline or
 * device disconnected or no internet connection available
 *
 * @param content: the content to display in a normal scenario
 * @param viewModel: the viewmodel used by the context where this method has been invoked, this is
 * used to stop the refreshing routine when the internet connection is not available by the [NoInternetConnectionUi]
 */
@Composable
fun ManagedContent(
    content: @Composable () -> Unit,
    viewModel: EquinoxViewModel
) {
    InstantiateSessionInstances()
    AnimatedVisibility(
        visible = isServerOffline.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ServerOfflineUi()
    }
    AnimatedVisibility(
        visible = noInternetConnection.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        NoInternetConnectionUi(
            viewModel = viewModel
        )
    }
    AnimatedVisibility(
        visible = !isServerOffline.value && !noInternetConnection.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        if(hasBeenDisconnected.value)
            hasBeenDisconnectedAction()
        else {
            viewModel.restartRefresher()
            content.invoke()
        }
    }
}

/**
 * Function to instantiate the session instances to manage the different scenarios
 *
 * No-any params required
 */
@Composable
private fun InstantiateSessionInstances() {
    isServerOffline = remember { mutableStateOf(false) }
    noInternetConnection = remember { mutableStateOf(false) }
    hasBeenDisconnected = remember { mutableStateOf(false) }
    checkInternetConnection(
        noInternetConnectionState = noInternetConnection
    )
}

/**
 * Function to display the content when the server is offline
 *
 * No-any params required
 */
@Composable
@NonRestartableComposable
private fun ServerOfflineUi() {
    ErrorUI(
        errorIcon = try {
            sessionSetup.serverOfflineIcon
        } catch (e : UninitializedPropertyAccessException) {
            throw Exception("You must setup the session before, this using the setUpSession() method")
        },
        errorMessage = try {
            sessionSetup.serverOfflineMessage
        } catch (e : UninitializedPropertyAccessException) {
            throw Exception("You must setup the session before, this using the setUpSession() method")
        }
    )
}

/**
 * Function to display the content when the internet connection missing
 *
 * @param viewModel: the viewmodel used by the context from the [ManagedContent] method has been invoked, this is
 * used to stop the refreshing routine when the internet connection is not available
 *
 */
@Composable
@NonRestartableComposable
private fun NoInternetConnectionUi(
    viewModel: EquinoxViewModel
) {
    viewModel.suspendRefresher()
    ErrorUI(
        errorIcon = try {
            sessionSetup.noInternetConnectionIcon
        } catch (e : UninitializedPropertyAccessException) {
            throw Exception("You must setup the session before, this using the setUpSession() method")
        },
        errorMessage = try {
            sessionSetup.noInternetConnectionMessage
        } catch (e : UninitializedPropertyAccessException) {
            throw Exception("You must setup the session before, this using the setUpSession() method")
        }
    )
}

/**
 * Function to disconnect the current user from the session
 *
 * No-any params required
 */
private fun hasBeenDisconnectedAction() {
    try {
        sessionSetup.hasBeenDisconnectedAction.invoke()
    } catch (e : UninitializedPropertyAccessException) {
        throw Exception("You must setup the session before, this using the setUpSession() method")
    }
}
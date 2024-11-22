package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.tecknobit.equinoxcompose.components.ErrorUI
import com.tecknobit.equinoxcompose.helpers.session.SessionStatus.*
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
 * The **SessionSetup** class is useful to create a setup for the current session
 *
 * @param serverOfflineMessage The message to use when the server is offline
 * @param serverOfflineIcon The icon to use when the server is offline
 * @param noInternetConnectionMessage The message to use when the internet connection is not available
 * @param noInternetConnectionIcon The icon to use when the internet connection is not available
 * @param hasBeenDisconnectedAction The action to execute when the user has been disconnected
 */
data class SessionSetup(
    val serverOfflineMessage: String,
    val serverOfflineIcon: ImageVector,
    val noInternetConnectionMessage: String,
    val noInternetConnectionIcon: ImageVector,
    val hasBeenDisconnectedAction: () -> Unit
)

/**
 * List of the possible statuses of the session
 */
enum class SessionStatus {

    /**
     * *OPERATIONAL* -> the normal status of the session
     */
    OPERATIONAL,

    /**
     * *SERVER_OFFLINE* -> the status of the session when the related server is offline
     */
    SERVER_OFFLINE,

    /**
     * *NO_INTERNET_CONNECTION* -> the status of the session when there is no internet connection
     */
    NO_INTERNET_CONNECTION,

    /**
     * *USER_DISCONNECTED* -> the status of the session when the user has been disconnected
     */
    USER_DISCONNECTED

}

/**
 * *sessionSetup* -> the setup for the session
 */
private lateinit var sessionSetup: SessionSetup

/**
 * *sessionStatus* -> the current session status
 */
private lateinit var sessionStatus: MutableState<SessionStatus>

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
 * Method to set up the [sessionSetup] instance
 *
 * @param serverOfflineMessage The message to use when the server is offline
 * @param serverOfflineIcon The icon to use when the server is offline
 * @param noInternetConnectionMessage The message to use when the internet connection is not available
 * @param noInternetConnectionIcon The icon to use when the internet connection is not available
 * @param hasBeenDisconnectedAction The action to execute when the user has been disconnected
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
 * Method to set up the [sessionSetup] instance
 *
 * @param sessionSetupValue The setup to use for the current session
 */
fun setUpSession(
    sessionSetupValue: SessionSetup
) {
    sessionSetup = sessionSetupValue
}

/**
 * Method to set the value of the [isServerOffline] state, when the value is _true_ will be invoked
 * the [ServerOfflineUi] method, when _false_ will be displayed the normal content
 *
 * @param isServerOfflineValue The value to set
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
 * Method to set the value of the [hasBeenDisconnectedAction] state, when the value is _true_ will be invoked
 * the [hasBeenDisconnectedAction] method, when _false_ will be displayed the normal content
 *
 * @param hasBeenDisconnectedValue The value to set
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
 * Method to display the correct content based on the current scenario such server offline or
 * device disconnected or no internet connection available
 *
 * @param content The content to display in a normal scenario
 * @param viewModel The viewmodel used by the context where this method has been invoked, this is
 * used to stop the refreshing routine when the internet connection is not available by the [NoInternetConnectionUi]
 *
 * @param serverOfflineRetryText The informative text for the user
 * @param serverOfflineRetryAction The action to retry the connection to the server
 * @param noInternetConnectionRetryText The informative text for the user
 * @param noInternetConnectionRetryAction The action to retry the internet connection scan
 */
@Composable
fun ManagedContent(
    content: @Composable () -> Unit,
    viewModel: EquinoxViewModel,
    serverOfflineRetryText: String? = null,
    serverOfflineRetryAction: @Composable (() -> Unit)? = null,
    noInternetConnectionRetryText: String? = null,
    noInternetConnectionRetryAction: @Composable (() -> Unit)? = null
) {
    InstantiateSessionInstances()
    AnimatedVisibility(
        visible = isServerOffline.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        sessionStatus.value = SERVER_OFFLINE
        ServerOfflineUi(
            retryText = serverOfflineRetryText,
            retryAction = serverOfflineRetryAction
        )
    }
    AnimatedVisibility(
        visible = noInternetConnection.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        sessionStatus.value = NO_INTERNET_CONNECTION
        NoInternetConnectionUi(
            viewModel = viewModel,
            retryText = noInternetConnectionRetryText,
            retryAction = noInternetConnectionRetryAction
        )
    }
    AnimatedVisibility(
        visible = !isServerOffline.value && !noInternetConnection.value,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        if(hasBeenDisconnected.value) {
            sessionStatus.value = USER_DISCONNECTED
            hasBeenDisconnectedAction()
        } else {
            sessionStatus.value = OPERATIONAL
            viewModel.restartRefresher()
            content.invoke()
        }
    }
}

/**
 * Method to instantiate the session instances to manage the different scenarios
 */
@Composable
private fun InstantiateSessionInstances() {
    isServerOffline = remember { mutableStateOf(false) }
    noInternetConnection = remember { mutableStateOf(false) }
    hasBeenDisconnected = remember { mutableStateOf(false) }
    sessionStatus = remember { mutableStateOf(OPERATIONAL) }
    checkInternetConnection(
        noInternetConnectionState = noInternetConnection
    )
}

/**
 * Method to display the content when the server is offline
 *
 * @param retryText The informative text for the user
 * @param retryAction The action to retry the connection to the server
 */
@Composable
@NonRestartableComposable
private fun ServerOfflineUi(
    retryText: String?,
    retryAction: @Composable (() -> Unit)?
) {
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
        },
        retryText = retryText,
        retryAction = retryAction
    )
}

/**
 * Method to display the content when the internet connection missing
 *
 * @param viewModel The viewmodel used by the context from the [ManagedContent] method has been invoked, this is
 * used to stop the refreshing routine when the internet connection is not available
 * @param retryText The informative text for the user
 * @param retryAction The action to retry the internet connection scan
 *
 */
@Composable
@NonRestartableComposable
private fun NoInternetConnectionUi(
    viewModel: EquinoxViewModel,
    retryText: String?,
    retryAction: @Composable (() -> Unit)?
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
        },
        retryText = retryText,
        retryAction = retryAction
    )
}

/**
 * Method to disconnect the current user from the session
 */
private fun hasBeenDisconnectedAction() {
    try {
        sessionSetup.hasBeenDisconnectedAction.invoke()
    } catch (e : UninitializedPropertyAccessException) {
        throw Exception("You must setup the session before, this using the setUpSession() method")
    }
}

/**
 * Method to get the current status of the session as [SessionStatus]
 */
fun getCurrentSessionStatus(): SessionStatus {
    return sessionStatus.value
}
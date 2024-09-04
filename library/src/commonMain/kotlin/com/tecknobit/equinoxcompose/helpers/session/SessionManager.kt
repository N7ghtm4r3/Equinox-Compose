@file:OptIn(DelicateCoroutinesApi::class)

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
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

/**
 * The **SessionManager** interface is useful to display the correct content based on the current scenario
 * such server offline or device disconnected
 *
 * @author N7ghtm4r3 - Tecknobit
 */
interface SessionManager {

    companion object {

        private lateinit var sessionSetup: SessionSetup

        /**
         * *isServerOffline* -> state to manage the server offline scenario
         */
        private lateinit var isServerOffline: MutableState<Boolean>

        private lateinit var noInternetConnection: MutableState<Boolean>

        /**
         * *haveBeenDisconnected* -> when the account has been deleted and the session needs to
         * be detached from the device
         */
        private lateinit var haveBeenDisconnected: MutableState<Boolean>

        @Composable
        fun setUpSession(
            serverOfflineMessage: String = stringResource(Res.string.server_currently_offline),
            serverOfflineIcon: ImageVector = Icons.Default.Warning,
            noInternetConnectionMessage: String = stringResource(Res.string.no_internet_connection),
            noInternetConnectionIcon: ImageVector = vectorResource(Res.drawable.no_internet)
        ) {
            setUpSession(
                sessionSetup = SessionSetup(
                    serverOfflineMessage = serverOfflineMessage,
                    serverOfflineIcon = serverOfflineIcon,
                    noInternetConnectionMessage = noInternetConnectionMessage,
                    noInternetConnectionIcon = noInternetConnectionIcon
                )
            )
        }

        fun setUpSession(
            sessionSetup: SessionSetup
        ) {
            this.sessionSetup = sessionSetup
        }

        fun setServerOfflineValue(
            isServerOffline: Boolean
        ) {
            if(::isServerOffline.isInitialized) {
                MainScope().launch {
                    this@Companion.isServerOffline.value = isServerOffline
                }
            }
        }

        fun setHaveBeenDisconnectedValue(
            haveBeenDisconnected: Boolean
        ) {
            if(::haveBeenDisconnected.isInitialized) {
                MainScope().launch {
                    this@Companion.haveBeenDisconnected.value = haveBeenDisconnected
                }
            }
        }

    }

    //Res.string.server_currently_offline

    //https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-swing/1.8.1 to cit in docu
    data class SessionSetup(
        val serverOfflineMessage: String,
        val serverOfflineIcon: ImageVector,
        val noInternetConnectionMessage: String,
        val noInternetConnectionIcon: ImageVector
    )

    /**
     * Function to display the correct content based on the current scenario such server offline or
     * device disconnected
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
            if(haveBeenDisconnected.value)
                haveBeenDisconnected()
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
        haveBeenDisconnected = remember { mutableStateOf(false) }
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
    fun haveBeenDisconnected()

}
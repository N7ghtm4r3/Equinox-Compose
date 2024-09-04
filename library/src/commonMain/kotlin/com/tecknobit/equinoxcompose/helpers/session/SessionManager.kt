@file:OptIn(DelicateCoroutinesApi::class)

package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import com.tecknobit.equinoxcompose.components.ErrorUI
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.InetAddress

/**
 * The **SessionManager** interface is useful to display the correct content based on the current scenario
 * such server offline or device disconnected
 *
 * @author N7ghtm4r3 - Tecknobit
 */
interface SessionManager {

    companion object {

        private lateinit var sessionMessages: SessionMessages

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

        fun setSessionMessages(
            serverOfflineMessage: String,
            noInternetConnectionMessage: String
        ) {
            setSessionMessages(
                sessionMessages = SessionMessages(
                    serverOfflineMessage = serverOfflineMessage,
                    noInternetConnectionMessage = noInternetConnectionMessage
                )
            )
        }

        fun setSessionMessages(
            sessionMessages: SessionMessages
        ) {
            this.sessionMessages = sessionMessages
        }

        fun setServerOfflineValue(
            isServerOffline: Boolean
        ) {
            if(::isServerOffline.isInitialized) {
                GlobalScope.launch {
                    this@Companion.isServerOffline.value = isServerOffline
                }
            }
        }

        fun setHaveBeenDisconnectedValue(
            haveBeenDisconnected: Boolean
        ) {
            if(::haveBeenDisconnected.isInitialized) {
                GlobalScope.launch {
                    this@Companion.haveBeenDisconnected.value = haveBeenDisconnected
                }
            }
        }

    }

    //Res.string.server_currently_offline
    data class SessionMessages(
        val serverOfflineMessage: String,
        val noInternetConnectionMessage: String
    )

    /**
     * Function to display the correct content based on the current scenario such server offline or
     * device disconnected
     *
     * @param content: the content to display in a normal scenario
     */
    @Composable
    fun ManagedContent(
        content: @Composable () -> Unit
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
            NoInternetConnectionUi()
        }
        AnimatedVisibility(
            visible = !isServerOffline.value && !noInternetConnection.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            if(haveBeenDisconnected.value)
                haveBeenDisconnected()
            else
                content.invoke()
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
        StartConnectionChecker()
    }

    @Composable
    private fun StartConnectionChecker() {
        GlobalScope.launch {
            while (true) {
                try {
                    val address = InetAddress.getByName("8.8.8.8")
                    noInternetConnection.value = !(!address.equals("") && address.isReachable(500))
                } catch (e: Exception) {
                    e.printStackTrace()
                    noInternetConnection.value = true
                }
                delay(1500)
            }
        }
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
            errorIcon = Icons.Default.Warning,
            errorMessage = try {
                sessionMessages.serverOfflineMessage
            } catch (e : UninitializedPropertyAccessException) {
                throw Exception("You must set the session messages first using the setSessionMessages() method")
            }
        )
    }

    /**
     * Function to display the content when the internet connection missing
     *
     * No-any params required
     */
    @Composable
    @NonRestartableComposable
    private fun NoInternetConnectionUi() {
        ErrorUI(
            errorIcon = Icons.Default.SignalWifiConnectedNoInternet4,
            errorMessage = try {
                sessionMessages.noInternetConnectionMessage
            } catch (e : UninitializedPropertyAccessException) {
                throw Exception("You must set the session messages first using the setSessionMessages() method")
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
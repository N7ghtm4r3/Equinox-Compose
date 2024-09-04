package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import com.tecknobit.equinoxcompose.components.ErrorUI
import com.tecknobit.library.generated.resources.Res
import com.tecknobit.library.generated.resources.server_currently_offline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

/**
 * The **SessionManager** interface is useful to display the correct content based on the current scenario
 * such server offline or device disconnected
 *
 * @author N7ghtm4r3 - Tecknobit
 */
interface SessionManager {

    companion object {

        /**
         * *isServerOffline* -> state to manage the server offline scenario
         */
        private lateinit var isServerOffline: MutableState<Boolean>

        private lateinit var serverOfflineMessage: String

        /**
         * *haveBeenDisconnected* -> when the account has been deleted and the session needs to
         * be detached from the device
         */
        private lateinit var haveBeenDisconnected: MutableState<Boolean>

        fun setServerOfflineValue(
            isServerOffline: Boolean
        ) {
            if(::isServerOffline.isInitialized)
                this.isServerOffline.value = isServerOffline
        }

        fun setServerOfflineMessage(
            serverOfflineMessage: String? = null
        ) {
            if(serverOfflineMessage == null) {
                CoroutineScope(Dispatchers.Default).launch {
                    Companion.serverOfflineMessage = getString(Res.string.server_currently_offline)
                }
            } else
                this.serverOfflineMessage = serverOfflineMessage
        }

        fun setHaveBeenDisconnectedValue(
            haveBeenDisconnected: Boolean
        ) {
            if(::haveBeenDisconnected.isInitialized)
                this.haveBeenDisconnected.value = haveBeenDisconnected
        }

    }

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
        InstantiateSessionFlags()
        AnimatedVisibility(
            visible = isServerOffline.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ServerOfflineUi()
        }
        AnimatedVisibility(
            visible = !isServerOffline.value,
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
     * Function to instantiate the session flags to manage the different scenarios
     *
     * No-any params required
     */
    @Composable
    private fun InstantiateSessionFlags() {
        isServerOffline = remember { mutableStateOf(false) }
        haveBeenDisconnected = remember { mutableStateOf(false) }
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
            errorMessage = serverOfflineMessage,
        )
    }

    /**
     * Function to disconnect the current user from the session
     *
     * No-any params required
     */
    fun haveBeenDisconnected()

}
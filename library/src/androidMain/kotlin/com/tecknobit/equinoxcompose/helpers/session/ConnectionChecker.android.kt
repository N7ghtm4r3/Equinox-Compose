package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.MutableState
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

actual fun checkInternetConnection(
    noInternetConnectionState: MutableState<Boolean>,
) {
    val connectivity = Connectivity()
    connectivity.start()
    MainScope().launch {
        connectivity.statusUpdates.collect { status ->
            noInternetConnectionState.value = status == Connectivity.Status.Disconnected
        }
    }
}
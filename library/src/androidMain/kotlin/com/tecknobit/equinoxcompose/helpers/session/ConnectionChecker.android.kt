package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.MutableState
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Function to check the internet connection availability.
 *
 * The check is done by the [Connectivity] helper of the [connectivity library](https://github.com/jordond/connectivity)
 *
 * @param noInternetConnectionState: state to manage the no internet connection scenario
 */
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
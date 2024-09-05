package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.InetAddress

/**
 * *GOOGLE_DNS* -> the dns value used to check whether the internet connection is on or off on a desktop device
 */
private const val GOOGLE_DNS = "8.8.8.8"

/**
 * Function to check the internet connection availability
 *
 * @param noInternetConnectionState: state to manage the no internet connection scenario
 */
actual fun checkInternetConnection(
    noInternetConnectionState: MutableState<Boolean>,
) {
    MainScope().launch {
        while (true) {
            try {
                val address = InetAddress.getByName(GOOGLE_DNS)
                noInternetConnectionState.value = address.equals("") || !address.isReachable(500)
            } catch (e: Exception) {
                noInternetConnectionState.value = true
            }
            delay(750)
        }
    }
}
package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.InetAddress

private const val GOOGLE_DNS = "8.8.8.8"

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
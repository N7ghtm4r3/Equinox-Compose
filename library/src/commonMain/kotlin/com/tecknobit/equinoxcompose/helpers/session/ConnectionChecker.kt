package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.MutableState

expect fun checkInternetConnection(
    noInternetConnectionState: MutableState<Boolean>
)
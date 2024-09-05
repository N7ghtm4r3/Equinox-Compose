package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.tecknobit.apimanager.annotations.Structure
import com.tecknobit.apimanager.apis.ConsolePainter
import com.tecknobit.apimanager.apis.ConsolePainter.ANSIColor.*

@Structure
abstract class EquinoxScreen(
    private val loggingEnabled: Boolean = true
) {

    /**
     *
     * @param onCreate: the action to execute when the composable has been created
     * @param onStart: the action to execute when the composable has been started
     * @param onResume: the action to execute when the composable has been resumed
     * @param onPause: the action to execute when the composable has been paused
     * @param onStop: the action to execute when the composable has been stopped
     * @param onDestroy: the action to execute when the composable has been destroyed
     * @param onAny: the action to execute when in the composable has been happened any action
     * @param onDispose: the action to execute when the composable has been disposed
     */

    /**
     * *painter* -> the painter used to log the actions occurred in the composable
     */
    private val painter = ConsolePainter()

    @Composable
    fun ShowContent() {
        LifecycleManager()
        ArrangeScreenContent()
    }

    @Composable
    protected abstract fun ArrangeScreenContent()

    /**
     * Function to manage the lifecycle of the composable where this function has been invoked
     *
     * @param lifecycleOwner: the owner of the current lifecycle
     */
    @Composable
    private fun LifecycleManager(
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    ) {
        DisposableEffect(lifecycleOwner) {
            val lifecycle = lifecycleOwner.lifecycle
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    ON_CREATE -> onCreate()
                    ON_START -> onStart()
                    ON_RESUME -> onResume()
                    ON_PAUSE -> onPause()
                    ON_STOP -> onStop()
                    ON_DESTROY -> onDestroy()
                    else -> onAny()
                }
            }
            lifecycle.addObserver(observer)
            onDispose {
                onDispose()
                lifecycle.removeObserver(observer)
            }
        }
    }

    protected open fun onCreate() {
        if(loggingEnabled)
            painter.printBold(ON_CREATE, CYAN)
    }

    protected open fun onStart() {
        if(loggingEnabled)
            painter.printBold(ON_START, GREEN)
    }

    protected open fun onResume() {
        if(loggingEnabled)
            painter.printBold(ON_RESUME, BLUE)
    }

    protected open fun onPause() {
        if(loggingEnabled)
            painter.printBold(ON_PAUSE, YELLOW)
    }

    protected open fun onStop() {
        if(loggingEnabled)
            painter.printBold(ON_STOP, RED)
    }

    protected open fun onDestroy() {
        if(loggingEnabled)
            painter.printBold(ON_DESTROY, BRIGHT_RED)
    }

    protected open fun onAny() {
        if(loggingEnabled)
            painter.printBold(ON_ANY, GRAY)
    }

    protected open fun onDispose() {
        if(loggingEnabled)
            painter.printBold(ON_ANY, MAGENTA)
    }

}
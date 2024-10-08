package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.tecknobit.apimanager.annotations.Structure
import com.tecknobit.apimanager.apis.ConsolePainter
import com.tecknobit.apimanager.apis.ConsolePainter.ANSIColor.*
import com.tecknobit.equinox.FetcherManager.FetcherManagerWrapper

/**
 * The **EquinoxScreen** class is useful to create a screen with a lifecycle management similar to the Android's activities
 *
 * Related documentation: [EquinoxScreen.md](https://github.com/N7ghtm4r3/Equinox-Compose/blob/main/documd/EquinoxScreen.md.md)
 *
 * @param loggerEnabled: whether enabled the logging to log the events occurred in the [ShowContent] composable
 *
 * @author N7ghtm4r3 - Tecknobit
 * @see ViewModel
 * @see FetcherManagerWrapper
 *
 */
@Structure
abstract class EquinoxScreen(
    private val loggerEnabled: Boolean = true
) {

    /**
     * *painter* -> the painter used to log the actions occurred in the composable
     */
    private val painter = ConsolePainter()

    /**
     * Function to display the content of the screen
     *
     * Its lifecycle will be managed invoking the [LifecycleManager] method
     *
     * No-any params required
     */
    @Composable
    fun ShowContent() {
        LifecycleManager()
        ArrangeScreenContent()
    }

    /**
     * Function to arrange the content of the screen to display
     *
     * No-any params required
     */
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

    /**
     * Function invoked when the [ShowContent] composable has been created
     *
     * No-any params required
     */
    protected open fun onCreate() {
        if(loggerEnabled)
            painter.printBold(ON_CREATE, CYAN)
    }

    /**
     * Function invoked when the [ShowContent] composable has been started
     *
     * No-any params required
     */
    protected open fun onStart() {
        if(loggerEnabled)
            painter.printBold(ON_START, GREEN)
    }

    /**
     * Function invoked when the [ShowContent] composable has been resumed
     *
     * No-any params required
     */
    protected open fun onResume() {
        if(loggerEnabled)
            painter.printBold(ON_RESUME, BLUE)
    }

    /**
     * Function invoked when the [ShowContent] composable has been paused
     *
     * No-any params required
     */
    protected open fun onPause() {
        if(loggerEnabled)
            painter.printBold(ON_PAUSE, YELLOW)
    }

    /**
     * Function invoked when the [ShowContent] composable has been stopped
     *
     * No-any params required
     */
    protected open fun onStop() {
        if(loggerEnabled)
            painter.printBold(ON_STOP, RED)
    }

    /**
     * Function invoked when the [ShowContent] composable has been destroyed
     *
     * No-any params required
     */
    protected open fun onDestroy() {
        if(loggerEnabled)
            painter.printBold(ON_DESTROY, BRIGHT_RED)
    }

    /**
     * Function invoked when in the [ShowContent] composable has occurred any of the possible events
     *
     * No-any params required
     */
    protected open fun onAny() {
        if(loggerEnabled)
            painter.printBold(ON_ANY, GRAY)
    }

    /**
     * Function invoked when the [ShowContent] composable has been disposed
     *
     * No-any params required
     */
    protected open fun onDispose() {
        if(loggerEnabled)
            painter.printBold(ON_ANY, MAGENTA)
    }

}
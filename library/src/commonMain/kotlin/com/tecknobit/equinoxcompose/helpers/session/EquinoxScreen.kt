package com.tecknobit.equinoxcompose.helpers.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.tecknobit.apimanager.annotations.Structure
import com.tecknobit.apimanager.apis.ConsolePainter
import com.tecknobit.apimanager.apis.ConsolePainter.ANSIColor
import com.tecknobit.apimanager.apis.ConsolePainter.ANSIColor.*
import com.tecknobit.apimanager.formatters.TimeFormatter
import com.tecknobit.equinoxcompose.helpers.session.EquinoxScreen.EquinoxScreenEvent.ON_DISPOSE
import com.tecknobit.equinoxcompose.helpers.session.EquinoxScreen.EquinoxScreenEvent.ON_INIT
import com.tecknobit.equinoxcompose.helpers.viewmodels.EquinoxViewModel
import java.util.*

/**
 * The **EquinoxScreen** class is useful to create a screen with a lifecycle management similar to the Android's activities
 *
 * Related documentation: [EquinoxScreen.md](https://github.com/N7ghtm4r3/Equinox-Compose/blob/main/documd/EquinoxScreen.md.md)
 *
 * @property loggerEnabled: whether enabled the logging to log the events occurred in the [ShowContent] composable,
 * it is suggested to disable it in production
 * @property viewModel: if the screen has got a related viewmodel that will be used to automatically manage the refresher suspension
 * or restarting with the lifecycle events of the screen
 *
 * @author N7ghtm4r3 - Tecknobit
 *
 * @param V: generic type used to allow the use of own viewmodel in custom screens
 *
 */
@Structure
abstract class EquinoxScreen<V : EquinoxViewModel>(
    private val loggerEnabled: Boolean = true,
    protected open val viewModel: V? = null
) {

    /**
     * *EquinoxScreenEvent* -> available [EquinoxScreen] custom statuses
     */
    enum class EquinoxScreenEvent {

        /**
         * *ON_INIT* -> occurs when the screen has been initialized
         */
        ON_INIT,

        /**
         * *ON_DISPOSE* -> occurs when the screen has been disposed
         */
        ON_DISPOSE

    }

    /**
     * *timeFormatter* -> useful to format the time values
     */
    protected val timeFormatter = TimeFormatter.getInstance(Locale.getDefault())

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
        CollectStates()
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
     * Function invoked when the [EquinoxScreen] has been instantiated.
     *
     * To use this method correctly in have to invoke this in your on `init` block of your custom screen, otherwise
     * will be never invoked
     *
     * No-any params required
     *
     * ### Usage
     *
     * ```kotlin
     * class CustomScreen : EquinoxScreen() {
     *
     * 	init {
     * 		onInit();
     * 	}
     *
     * 	override fun onInit() {
     * 		super.onInit();
     * 		// your content here
     * 	}
     *
     * }
     * ```
     */
    protected open fun onInit() {
        logScreenEvent(
            event = ON_INIT.name,
            ansiColor = BRIGHT_BLUE
        )
    }

    /**
     * Function invoked when the [ShowContent] composable has been created.
     *
     * If the [viewModel] of the screen is not `null` will be set the [com.tecknobit.equinox.FetcherManager.activeContext]
     * as the current screen displayed
     *
     * No-any params required
     */
    protected open fun onCreate() {
        logScreenEvent(
            event = ON_CREATE,
            ansiColor = CYAN
        )
        viewModel?.setActiveContext(this::class.java)
    }

    /**
     * Function invoked when the [ShowContent] composable has been started
     *
     * No-any params required
     */
    protected open fun onStart() {
        logScreenEvent(
            event = ON_START,
            ansiColor = GREEN
        )
    }

    /**
     * Function invoked when the [ShowContent] composable has been resumed.
     *
     * If the [viewModel] of the screen is not `null` will be restarted the [com.tecknobit.equinox.FetcherManager.refreshRoutine]
     *
     * No-any params required
     *
     */
    protected open fun onResume() {
        logScreenEvent(
            event = ON_RESUME,
            ansiColor = BLUE
        )
        viewModel?.restartRefresher()
    }

    /**
     * Function invoked when the [ShowContent] composable has been paused.
     *
     * If the [viewModel] of the screen is not `null` will be suspended the [com.tecknobit.equinox.FetcherManager.refreshRoutine]
     *
     * No-any params required
     */
    protected open fun onPause() {
        logScreenEvent(
            event = ON_PAUSE,
            ansiColor = YELLOW
        )
        viewModel?.suspendRefresher()
    }

    /**
     * Function invoked when the [ShowContent] composable has been stopped.
     *
     * If the [viewModel] of the screen is not `null` will be suspended the [com.tecknobit.equinox.FetcherManager.refreshRoutine]
     *
     * No-any params required
     */
    protected open fun onStop() {
        logScreenEvent(
            event = ON_STOP,
            ansiColor = RED
        )
        viewModel?.suspendRefresher()
    }

    /**
     * Function invoked when the [ShowContent] composable has been destroyed.
     *
     * If the [viewModel] of the screen is not `null` will be suspended the [com.tecknobit.equinox.FetcherManager.refreshRoutine]
     *
     * No-any params required
     */
    protected open fun onDestroy() {
        logScreenEvent(
            event = ON_DESTROY,
            ansiColor = BRIGHT_RED
        )
        viewModel?.suspendRefresher()
    }

    /**
     * Function invoked when in the [ShowContent] composable has occurred any of the possible events
     *
     * No-any params required
     */
    protected open fun onAny() {
        logScreenEvent(
            event = ON_ANY,
            ansiColor = GRAY
        )
    }

    /**
     * Function invoked when the [ShowContent] composable has been disposed.
     *
     * If the [viewModel] of the screen is not `null` will be suspended the [com.tecknobit.equinox.FetcherManager.refreshRoutine]
     *
     * No-any params required
     */
    protected open fun onDispose() {
        logScreenEvent(
            event = ON_DISPOSE.name,
            ansiColor = MAGENTA
        )
        viewModel?.suspendRefresher()
    }

    /**
     * Function to log the event occurred in the current screen
     *
     * @param event: the event occurred
     * @param ansiColor: the color used to identifier the event
     */
    protected fun logScreenEvent(
        event: Event,
        ansiColor: ANSIColor
    ) {
        logScreenEvent(
            event = event.name,
            ansiColor = ansiColor
        )
    }

    /**
     * Function to log the event occurred in the current screen
     *
     * @param event: the event occurred
     * @param ansiColor: the color used to identifier the event
     */
    protected fun logScreenEvent(
        event: String,
        ansiColor: ANSIColor
    ) {
        if(loggerEnabled) {
            val logMessage = "[${this::class.java.name} - ${timeFormatter.formatNowAsString()}] -> $event"
            painter.printBold(logMessage, ansiColor)
        }
    }

    /**
     * Function to collect or instantiate the states of the screen
     *
     * No-any params required
     */
    @Composable
    protected abstract fun CollectStates()

}
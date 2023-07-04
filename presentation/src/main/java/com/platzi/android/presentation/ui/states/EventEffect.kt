package com.platzi.android.presentation.ui.states
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import kotlin.coroutines.CoroutineContext

@Composable
@NonRestartableComposable
fun EventEffect(event: StateEvent, onConsumed: () -> Unit, action: suspend () -> Unit) {
    LaunchedEffect(key1 = event, key2 = onConsumed) {
        if (event is StateEvent.Triggered) {
            action()
            onConsumed()
        }
    }
}

@Composable
@NonRestartableComposable
fun <T> EventEffect(event: StateEventWithContent<T>, onConsumed: () -> Unit, action: suspend (T) -> Unit) {
    LaunchedEffect(key1 = event, key2 = onConsumed) {
        if (event is StateEventWithContentTriggered<T>) {
            action(event.content)
            onConsumed()
        }
    }
}

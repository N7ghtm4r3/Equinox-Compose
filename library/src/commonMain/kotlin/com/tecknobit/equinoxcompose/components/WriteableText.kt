package com.tecknobit.equinoxcompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private lateinit var isInEditMode: MutableState<Boolean>

@Composable
@NonRestartableComposable
fun WriteableText(
    writeableTextColors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.primary,
        focusedContainerColor = MaterialTheme.colorScheme.primary,
        focusedIndicatorColor = MaterialTheme.colorScheme.inversePrimary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
        cursorColor = MaterialTheme.colorScheme.inversePrimary,
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White
    ),
    writeableTextWidth: Dp = 280.dp,
    writeableText: MutableState<String>,
    writeableTextStyle: TextStyle = LocalTextStyle.current.merge(
        fontSize = 20.sp
    ),
    onTyping: (String) -> Unit = {
        writeableText.value = it
    },
    placeholder: String,
    placeholderStyle: TextStyle = LocalTextStyle.current.merge(
        fontSize = 20.sp
    ),
    displayedText: String = writeableText.value,
    displayedTextStyle: TextStyle = LocalTextStyle.current.merge(
        fontSize = 20.sp
    ),
    changeEditModeClickingOnSimpleText: Boolean = true
) {
    isInEditMode = remember { mutableStateOf(false) }
    if(isInEditMode.value) {
        EquinoxTextField(
            textFieldColors = writeableTextColors,
            width = writeableTextWidth,
            value = writeableText,
            onValueChange = onTyping,
            placeholder = placeholder,
            placeholderStyle = placeholderStyle,
            textFieldStyle = writeableTextStyle,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { isInEditMode.value = false }
            )
        )
    } else {
        Text(
            modifier = if(changeEditModeClickingOnSimpleText)
                Modifier.clickable { isInEditMode.value = true }
            else
                Modifier,
            text = displayedText,
            style = displayedTextStyle
        )
    }
}

fun Modifier.disableEditModeOnTap() = this
    .pointerInput(Unit) {
        detectTapGestures {
            disableEditMode()
        }
    }

fun Modifier.disableEditModeOnFocusGain() = this
    .onFocusEvent { event ->
        if(event.hasFocus)
            disableEditMode()
    }

fun enableEditMode() {
    isInEditMode.value = true
}

fun disableEditMode() {
    isInEditMode.value = false
}
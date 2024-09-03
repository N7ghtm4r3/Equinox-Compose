package com.tecknobit.equinoxcompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
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
            value = writeableText,
            onValueChange = onTyping,
            placeholder = placeholder,
            placeholderStyle = placeholderStyle,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            textFieldStyle = writeableTextStyle,
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
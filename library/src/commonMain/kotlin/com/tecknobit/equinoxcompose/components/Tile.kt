package com.tecknobit.equinoxcompose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
@NonRestartableComposable
fun Tile(
    modifier: Modifier = Modifier,
    size: Dp = 115.dp,
    shape: Shape = RoundedCornerShape(
        size = 15.dp
    ),
    colors: CardColors = CardDefaults.cardColors(),
    contentColor: Color = LocalContentColor.current,
    elevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 3.dp
    ),
    icon: ImageVector,
    iconSize: Dp = 75.dp,
    text: StringResource,
    textStyle: TextStyle = TextStyle.Default.merge(
        color = contentColor,
        fontSize = 18.sp
    ),
    onClick: () -> Unit
) {
    Tile(
        modifier = modifier,
        size = size,
        shape = shape,
        colors = colors,
        contentColor = contentColor,
        icon = icon,
        iconSize = iconSize,
        text = stringResource(text),
        textStyle = textStyle,
        elevation = elevation,
        onClick = onClick
    )
}

@Composable
@NonRestartableComposable
fun Tile(
    modifier: Modifier = Modifier,
    size: Dp = 115.dp,
    shape: Shape = RoundedCornerShape(
        size = 15.dp
    ),
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 3.dp
    ),
    contentColor: Color = LocalContentColor.current,
    icon: ImageVector,
    iconSize: Dp = 75.dp,
    text: String,
    textStyle: TextStyle = TextStyle.Default.merge(
        color = contentColor,
        fontSize = 18.sp
    ),
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(size),
        shape = shape,
        colors = colors,
        elevation = elevation,
        onClick = onClick
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize),
                imageVector = icon,
                contentDescription = null,
                tint = contentColor
            )
            Text(
                text = text,
                style = textStyle.merge(
                    color = contentColor
                )
            )
        }
    }
}
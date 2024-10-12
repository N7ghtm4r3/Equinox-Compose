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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Tile component useful to execute action when clicked
 *
 * @param modifier: the modifier to apply to the container [Card]
 * @param size: the size of the tile
 * @param shape: the shape of the tile
 * @param containerColor: the colors scheme to apply to the tile
 * @param contentColor: the color of the content, icon and the text
 * @param elevation: the elevation of the tile
 * @param icon: the representative icon
 * @param iconSize: the size of the [icon]
 * @param text: the representative text
 * @param fontWeight: the weight to apply to the [text]
 * @param textStyle: the style to apply to the [text]
 * @param onClick: the action to execute when the tile has been clicked
 */
@Composable
@NonRestartableComposable
fun Tile(
    modifier: Modifier = Modifier,
    size: Dp = 115.dp,
    shape: Shape = RoundedCornerShape(
        size = 15.dp
    ),
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = LocalContentColor.current,
    elevation: Dp = 3.dp,
    icon: ImageVector,
    iconSize: Dp = 65.dp,
    text: StringResource,
    fontWeight: FontWeight = FontWeight.Bold,
    textStyle: TextStyle = TextStyle.Default.merge(
        color = contentColor,
        fontWeight = fontWeight
    ),
    onClick: () -> Unit
) {
    Tile(
        modifier = modifier,
        size = size,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        icon = icon,
        iconSize = iconSize,
        text = stringResource(text),
        textStyle = textStyle,
        elevation = elevation,
        onClick = onClick
    )
}

/**
 * Tile component useful to execute action when clicked
 *
 * @param modifier: the modifier to apply to the container [Card]
 * @param size: the size of the tile
 * @param shape: the shape of the tile
 * @param containerColor: the colors scheme to apply to the tile
 * @param contentColor: the color of the content, icon and the text
 * @param elevation: the elevation of the tile
 * @param icon: the representative icon
 * @param iconSize: the size of the [icon]
 * @param text: the representative text
 * @param fontWeight: the weight to apply to the [text]
 * @param textStyle: the style to apply to the [text]
 * @param onClick: the action to execute when the tile has been clicked
 */
@Composable
@NonRestartableComposable
fun Tile(
    modifier: Modifier = Modifier,
    size: Dp = 115.dp,
    shape: Shape = RoundedCornerShape(
        size = 15.dp
    ),
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = LocalContentColor.current,
    elevation: Dp = 3.dp,
    icon: ImageVector,
    iconSize: Dp = 65.dp,
    text: String,
    fontWeight: FontWeight = FontWeight.Bold,
    textStyle: TextStyle = TextStyle.Default.merge(
        color = contentColor,
        fontWeight = fontWeight
    ),
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(size),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
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
                    color = contentColor,
                    fontWeight = fontWeight
                )
            )
        }
    }
}
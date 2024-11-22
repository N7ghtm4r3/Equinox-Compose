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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
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
 * @param modifier The modifier to apply to the container [Card]
 * @param stroke The stroke to apply as dashed effect
 * @param size The size of the tile
 * @param cornerRadius The radius of the tile
 * @param containerColor The colors scheme to apply to the tile
 * @param contentColor The color of the content, icon and the text
 * @param elevation The elevation of the tile
 * @param icon The representative icon
 * @param iconSize The size of the [icon]
 * @param text The representative text
 * @param fontWeight The weight to apply to the [text]
 * @param textStyle The style to apply to the [text]
 * @param onClick The action to execute when the tile has been clicked
 */
@Composable
@NonRestartableComposable
fun DashedTile(
    modifier: Modifier = Modifier,
    stroke: Stroke = Stroke(
        width = 5f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    ),
    size: Dp = 115.dp,
    cornerRadius: Dp = 15.dp,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(containerColor),
    elevation: Dp = 0.dp,
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
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = containerColor,
                    style = stroke,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            },
        size = size,
        containerColor = Color.Transparent,
        elevation = elevation,
        contentColor = containerColor,
        icon = icon,
        iconSize = iconSize,
        text = text,
        textStyle = textStyle,
        onClick = onClick
    )
}

/**
 * Tile component useful to execute action when clicked
 *
 * @param modifier The modifier to apply to the container [Card]
 * @param size The size of the tile
 * @param shape The shape of the tile
 * @param containerColor The colors scheme to apply to the tile
 * @param contentColor The color of the content, icon and the text
 * @param elevation The elevation of the tile
 * @param icon The representative icon
 * @param iconSize The size of the [icon]
 * @param text The representative text
 * @param fontWeight The weight to apply to the [text]
 * @param textStyle The style to apply to the [text]
 * @param onClick The action to execute when the tile has been clicked
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
    contentColor: Color = contentColorFor(containerColor),
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
 * @param modifier The modifier to apply to the container [Card]
 * @param size The size of the tile
 * @param shape The shape of the tile
 * @param containerColor The colors scheme to apply to the tile
 * @param contentColor The color of the content, icon and the text
 * @param elevation The elevation of the tile
 * @param icon The representative icon
 * @param iconSize The size of the [icon]
 * @param text The representative text
 * @param fontWeight The weight to apply to the [text]
 * @param textStyle The style to apply to the [text]
 * @param onClick The action to execute when the tile has been clicked
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
    contentColor: Color = contentColorFor(containerColor),
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
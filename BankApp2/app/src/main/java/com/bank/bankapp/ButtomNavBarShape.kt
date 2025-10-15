package com.bank.bankapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BottomNavBarShape(
    private val cornerRadius: Dp = 40.dp, // Single radius for all corners
    private val dipHeight: Dp = 40.dp,
    private val dipWidth: Dp = 60.dp,
    private val dipControlOffset: Dp = 15.dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() } // Use this for all corners
        val dipHeightPx = with(density) { dipHeight.toPx() }
        val dipWidthPx = with(density) { dipWidth.toPx() }
        val dipControlOffsetPx = with(density) { dipControlOffset.toPx() }

        val halfDipWidth = dipWidthPx / 2
        val midX = size.width / 2

        val path = Path().apply {
            reset()

            // Start from top-left, after the rounded corner
            moveTo(cornerRadiusPx, 0f)

            // Line to where the pre-dip curve starts
            val preDipCurveStartX = midX - halfDipWidth - dipControlOffsetPx
            lineTo(preDipCurveStartX, 0f)

            // Smooth curve from flat top edge INTO the start of the main dip (left side)
            quadraticBezierTo(
                x1 = midX - halfDipWidth - (dipControlOffsetPx / 2),
                y1 = 0f,
                x2 = midX - halfDipWidth,
                y2 = dipHeightPx * 0.1f
            )

            // Main Dip - upside down bell curve
            quadraticBezierTo(
                x1 = midX,
                y1 = dipHeightPx,
                x2 = midX + halfDipWidth,
                y2 = dipHeightPx * 0.1f
            )

            // Smooth curve FROM the end of the main dip back to the flat top edge (right side)
            quadraticBezierTo(
                x1 = midX + halfDipWidth + (dipControlOffsetPx / 2),
                y1 = 0f,
                x2 = midX + halfDipWidth + dipControlOffsetPx,
                y2 = 0f
            )

            // Top edge right part, after the pre-dip curve
            lineTo(size.width - cornerRadiusPx, 0f)

            // Top-right corner
            arcTo(
                rect = Rect(
                    Offset(size.width - 2 * cornerRadiusPx, 0f),
                    Size(2 * cornerRadiusPx, 2 * cornerRadiusPx)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Right edge to the start of the bottom-right rounded corner
            lineTo(size.width, size.height - cornerRadiusPx) // Use cornerRadiusPx

            // Bottom-right corner
            arcTo(
                rect = Rect(
                    Offset(size.width - 2 * cornerRadiusPx, size.height - 2 * cornerRadiusPx), // Use cornerRadiusPx
                    Size(2 * cornerRadiusPx, 2 * cornerRadiusPx) // Use cornerRadiusPx
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Bottom edge to the start of the bottom-left rounded corner
            lineTo(cornerRadiusPx, size.height) // Use cornerRadiusPx

            // Bottom-left corner
            arcTo(
                rect = Rect(
                    Offset(0f, size.height - 2 * cornerRadiusPx), // Use cornerRadiusPx
                    Size(2 * cornerRadiusPx, 2 * cornerRadiusPx) // Use cornerRadiusPx
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Left edge to the start of the top-left rounded corner
            lineTo(0f, cornerRadiusPx) // Use cornerRadiusPx

            // Top-left corner
            arcTo(
                rect = Rect(
                    Offset(0f, 0f),
                    Size(2 * cornerRadiusPx, 2 * cornerRadiusPx)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            close()
        }
        return Outline.Generic(path)
    }
}


@Composable
@Preview(showBackground = true)
fun BottomNavBarShapePreviewWithDipCurves() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .size(width = 360.dp, height = 70.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = BottomNavBarShape(
                        cornerRadius = 20.dp, // Single radius for all corners
                        dipHeight = 70.dp,
                        dipWidth = 90.dp,
                        dipControlOffset = 13.dp
                    )
                )
        )
    }
}
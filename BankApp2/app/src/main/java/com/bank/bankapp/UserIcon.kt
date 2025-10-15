package com.bank.bankapp

import android.location.Address
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bank_app.R

@Composable
fun summonUserIcon(
    buttonWidth: Dp,
    buttonHeight: Dp,
    buttonShape: CornerBasedShape,
    buttonColor: Color,
    buttonContentColor: Color,
    imageVector: ImageVector,
    imageLabel: String,
    iconSize: Dp,
    hasName: Boolean,
    iconTitle: String = "",
    buttonNavController: NavHostController,
    route: String
) {

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    IconButton(
        modifier = Modifier
            .clickable(onClick = { /* Handle click */ })
            .size(buttonWidth, buttonHeight)
            .clip(buttonShape),
        colors = IconButtonColors(
            containerColor = buttonColor,
            contentColor = buttonContentColor,
            disabledContainerColor = Gray,
            disabledContentColor = LightGray,
        ),
        onClick = { /**/ },
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = imageLabel,
            modifier = Modifier
                .requiredSize(iconSize)
                .offset(0.dp, (-20).dp)
                .clickable(
                    interactionSource = null,
                    indication = LocalIndication.current,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = { buttonNavController.navigate(route) }
                ),
            tint = Color.White,
        )
    }
}
@Composable
fun summonUserIcon2(
    buttonWidth: Dp,
    buttonHeight: Dp,
    buttonShape: CornerBasedShape,
    buttonColor: Color,
    buttonContentColor: Color,
    imageAddress: Int,
    imageLabel: String,
    iconSize: Dp,
    hasName: Boolean,
    iconTitle: String = "",
    buttonNavController: NavHostController,
    route: String
) {

    val facultyGlyphic = FontFamily(
        Font(R.font.faculty_glyphic_regular)
    )

    IconButton(
        modifier = Modifier
            .clickable(onClick = { /* Handle click */ })
            .size(buttonWidth, buttonHeight)
            .clip(buttonShape),
        colors = IconButtonColors(
            containerColor = buttonColor,
            contentColor = buttonContentColor,
            disabledContainerColor = Gray,
            disabledContentColor = LightGray,
        ),
        onClick = { /**/ },
    ) {
        Icon(
            painter=painterResource(imageAddress) ,
            contentDescription = imageLabel,
            modifier = Modifier
                .requiredSize(iconSize)
                .offset(0.dp, (-20).dp)
                .clickable(
                    interactionSource = null,
                    indication = LocalIndication.current,
                    enabled = true,
                    onClickLabel = null,
                    role = null,
                    onClick = { buttonNavController.navigate(route) }
                ),
            tint = Color.White,
        )
    }
}

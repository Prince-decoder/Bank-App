package com.farm.basics.Fundamental_concept

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farm.basics.R
@Composable
fun Sam_Text()
{
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(
            text = "helow"
        )
    }
}

@Composable
fun Sam_Access()
{
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Text(
            text = "helow",
            color = Color.Blue,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp
        )
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter)
    {
        Image(painter = painterResource(R.drawable.devop),"Devop image")
    }
}
@Composable
fun Sam_TextShadaw()
{
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Text(
            text = "helow how are y my friend",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            style = TextStyle(
                shadow = Shadow(color = Color.Cyan)
            )
        )
    }
}
@Composable
fun Sam_multicolor()
{
    val colo=mutableListOf(colorResource(R.color.white),colorResource(R.color.applered))
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter)
    {
        Text(
            text = buildAnnotatedString {
                append("how are you brother")
                withStyle(
                    style = SpanStyle(
                        brush = Brush.linearGradient(
                            colors = colo
                        )
                    )
                ){
                    append("java lm;malv")
                }

            },
            modifier = Modifier.basicMarquee(),
            fontSize = 40.sp
        )
    }
}
@Composable
fun Sam_OutLinedTextField()
{
  var text by remember {
      mutableStateOf("")
  }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        OutlinedTextField(
            value=text,
            onValueChange = {
                text=it
            },
            label={
                Text("Name")
            },
            textStyle = TextStyle(
                color = colorResource(R.color.purple_700)
            ),
//            modifier = Modifier.basicMarquee()
        )
    }
}
@Composable
fun Sam_Password()
{
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text=it
            },
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
    }
}
@Composable
fun Sam_URL()
{
    val urlhandler= LocalUriHandler.current
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Text(
            buildAnnotatedString {
                append("helow")
                val link= LinkAnnotation.Url(
                    "https://www.youtube.com/watch?v=U5dE-_E1wsg&t=12529s",
                    styles = TextLinkStyles(
                        SpanStyle(fontStyle = FontStyle.Italic,
                            fontSize = 10.sp)
                    )
                ){
                    val url=(it as LinkAnnotation.Url).url
                    urlhandler.openUri(url)
                }
                withLink(link)
                {
                    append("open me")
                }
            }
        )
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun demo1()
//{
////    Sam_Access()
////    Sam_Text()
////    Sam_multicolor()
////    Sam_OutLinedTextField()
//    Sam_OutLinedTextField()
//}
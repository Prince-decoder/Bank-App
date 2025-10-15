package com.farm.basics.Fundamental_concept

import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.disableHotReloadMode
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview

//select able text and non selectable text
@Composable
fun Selectable_and_Non_Selectable_Text()
{
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        SelectionContainer {
            Column {
                Text("these are selectable text:")
                Text("1) one")
                Text("1) Two")
                Text("1) Three")
                Text("1) Four")
                DisableSelection {
                    Text("Non selectable text")
                    Text("1) FAITH")
                    Text("1) LOYAL")
                    Text("1) BELIEF")
                    Text("1) LOVE")
                }
            }
        }
    }
}
//Annotated string with the listener sample if you click the text it will navigate you
//In Jetpack Compose, an AnnotatedString is a special type of string that not only holds text but also allows you to attach annotations (extra information or metadata) to parts of the text.
@Composable
fun Annotated_String()
{
    val urthandler= LocalUriHandler.current         //LocalUriHandler→ A CompositionLocal provided by Compose that knows how to open URIs (like web links) in the correct app (usually a browser).
    //current → Gets the current instance of the UriHandler from the composition.
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(
            buildAnnotatedString {
                append("Build with")
                //TextLinkStyles defines visual styling for the link text.
                //It usually includes:
                //i)SpanStyle → Inline styling like color, font weight, font style, underline, etc.
                //ii)ParagraphStyle (optional) → Paragraph-level styling.
                val link = LinkAnnotation.Url(
                    "https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary#Box(androidx.compose.ui.Modifier)",
                    TextLinkStyles(
                        SpanStyle(
                            color = Color.Blue
                        )
                    )
                ) {
                    val url = (it as LinkAnnotation.Url).url
                    urthandler.openUri(url)     //→ Opens the URL in a browser (or appropriate app).
                }
                withLink(link) {
                    append("JetPack compose")
                }
            }
        )
    }
}



//@Preview
//@Composable
//fun demo()
//{
//    Selectable_and_Non_Selectable_Text()
//}
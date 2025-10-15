package com.farm.basics.Fundamental_concept


import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farm.basics.R
@Composable
fun SampleText()
{
   Box(modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center)
   {
       Text("prince",
           color = colorResource(R.color.applered),
           fontSize = 25.sp,
           fontStyle = FontStyle.Italic,
           fontWeight = FontWeight.Bold,
           style = TextStyle(
               shadow= Shadow(color = colorResource(R.color.white), blurRadius = 50f)
           )
       )

   }
}
@Composable
fun Sample2() {
    val rain = mutableListOf(
        colorResource(R.color.applered),
        colorResource(R.color.teal_700),
        colorResource(R.color.purple_500)
    )
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
            Text(
                text = buildAnnotatedString {
                    append("do not do this")
                    withStyle(
                        SpanStyle(
                            brush = Brush.linearGradient(
                                colors = rain
                            )
                        )
                    )
                    {
                        append("becalkanflkna")
                    }
                    append("how are you")
                }
            )
        }
    }
@Composable
fun ScrollableText() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Marquee text (needs width constraint)
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "hu lal lala lala is a slogan in Africa before attacking. They killed many Europeans.",
                modifier = Modifier.basicMarquee(),
                fontSize = 40.sp,
                fontStyle = FontStyle.Italic,
                maxLines = 3,
                //overflow = TextOverflow.Visible
            )
        }

        // Bottom text (large repeated string)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "hu lal lala lala is a slogan in Africa before attacking. They killed many Europeans."
                    .repeat(10), // don’t over-repeat
                maxLines = 2,
                overflow = TextOverflow.Ellipsis, // prevents huge overdraw
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}

//@Preview
//@Composable
//fun Show(){
//    //SampleText()
//    //Sample2()
//    ScrollableText()
//}
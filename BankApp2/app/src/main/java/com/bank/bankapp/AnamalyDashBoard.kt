package com.bank.bankapp


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import kotlin.collections.plus
import com.example.bank_app.R

data class AnomalyLogEntry(
    val timestamp: String,
    val severity: String,
    val reconError: Float
)

/*class AnomalyViewModel : ViewModel() {
    private val _anomalies = mutableStateListOf<AnomalyRecord>()
    val anomalies: List<AnomalyRecord> = _anomalies

    fun addAnomaly(record: AnomalyRecord) {
        _anomalies.add(record)
    }
}*/

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview(showBackground = true)
fun DashboardPreview() {

    val navController = rememberNavController()

    val sampleAnomalyLogs = listOf(
        AnomalyLogEntry("2023-10-01 12:00", "High", 0.95f),
        AnomalyLogEntry("2023-10-01 12:05", "Medium", 0.75f),
        AnomalyLogEntry("2023-10-01 12:10", "Low", 0.50f)
    )
    AnomalyDashboard(anomalyLogList = sampleAnomalyLogs, navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnomalyDashboard(anomalyLogList: List<AnomalyLogEntry>, navController: NavHostController) {
    val hazeState = remember { HazeState() }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(380.dp, 75.dp)
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            White.copy(alpha = 0.1f),
                            tint = HazeTint(
                                Color(128, 128, 128, 0),
                                BlendMode.Luminosity
                            ),
                            blurRadius = 30.dp,
                            noiseFactor = 0f
                        )
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {navController.navigate("home_screen")},
                    modifier = Modifier
                        .size(80.dp)
                        .clickable(true, onClick = {})
                        .padding(4.dp, 30.dp, 4.dp, 0.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Menu Button",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }

                Text(
                    "Dashboard",
                    modifier = Modifier
                        .weight(7f)
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .offset(0.dp, 43.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = facultyGlyphic,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

            }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painterResource(R.drawable.background6),
                contentDescription = "Background",
                modifier = Modifier
                    .requiredSize(900.dp)
                    .hazeSource(state = hazeState)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp, 10.dp, 4.dp)
                        .offset(0.dp, 70.dp)
                        .background(Color.Transparent),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Time", modifier = Modifier.weight(0.4f).padding(8.dp), color = Color.White, fontSize = 18.sp)
                    Text("Nature", modifier = Modifier.weight(0.3f).padding(8.dp), color = Color.White, fontSize = 18.sp)
                    Text("Score", modifier = Modifier.weight(0.3f).padding(8.dp), color = Color.White, fontSize = 18.sp)
                }
                Divider()
                Spacer(modifier = Modifier.height(70.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp, 10.dp, 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(anomalyLogList) { entry ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 10.dp, 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(entry.timestamp, modifier = Modifier.weight(0.4f).padding(8.dp), fontSize = 15.sp, color = Color.White)
                            Text(entry.severity, modifier = Modifier.weight(0.3f).padding(8.dp), fontSize = 15.sp, color = Color.White)
                            Text("%f".format(entry.reconError), modifier = Modifier.weight(0.3f).padding(8.dp), color = Color.White, fontSize = 15.sp)
                        }
                        Divider(modifier = Modifier.width(330.dp).offset((-10).dp, 0.dp), color = Color.White.copy(alpha = 0.5f))
                    }
                }

                if (anomalyLogList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No anomalies logged yet.", color = Color.Gray)
                    }
                }
            }

        }
    }
}
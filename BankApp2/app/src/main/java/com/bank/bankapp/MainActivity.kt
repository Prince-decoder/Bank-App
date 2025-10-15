package com.example.bank_app

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONArray
import org.json.JSONObject
import org.tensorflow.lite.Interpreter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.collections.toFloatArray
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.ln
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bank.bankapp.AnomalyDashboard
import com.bank.bankapp.AnomalyLogEntry
import com.bank.bankapp.CardPayScreen
import com.bank.bankapp.CardsScreen
import com.bank.bankapp.ChangeCVV
import com.bank.bankapp.ContactsScreen
import com.bank.bankapp.ExtremeAnomalyAlertDialog
import com.bank.bankapp.HighAnomalyAlertDialog
import com.bank.bankapp.HomeScreen
import com.bank.bankapp.IntroScreen
import com.bank.bankapp.PasswordScreen
import com.bank.bankapp.PayToContact
import com.bank.bankapp.PaymentAnimation
import com.bank.bankapp.PaymentsPage
import com.bank.bankapp.RegisterScreen
import com.bank.bankapp.SetPinScreen
import com.bank.bankapp.UPIPayScreen
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var globalBehaviorDataBatch: MutableList<BehaviorRecord>
    }
    val anomalyLogList = mutableStateListOf<AnomalyLogEntry>()

    private val showHighAnomalyDialog = mutableStateOf(false)
    private val showExtremeAnomalyDialog = mutableStateOf(false)
    private val currentAnomalyScore = mutableStateOf(0f)

    internal val showAnomalyDialog = mutableStateOf(false)
    internal val anomalyDialogMessage = mutableStateOf("")
    private lateinit var database: DatabaseReference
    private val sessionId = System.currentTimeMillis().toString()
    private val userId = "demo_data"
    private lateinit var sensorManager: SensorManager
    private val sensorDataBatch = mutableListOf<SensorRecord>()
    private val behaviorDataBatch = mutableListOf<BehaviorRecord>()
    private val uploadHandler = Handler(Looper.getMainLooper())
    private val uploadInterval: Long = 3_000



    data class SensorRecord(val type: Int, val values: List<Float>, val timestamp: Long)
    data class BehaviorRecord(val type: String, val timestamp: Long, val data: Map<String, Any>)

    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val timestamp = System.currentTimeMillis()
            val values = event.values.map { it }
            sensorDataBatch.add(SensorRecord(event.sensor.type, values, timestamp))
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    private val uploadRunnable = object : Runnable {
        override fun run() {
            val sessionRef = database.child("users").child(userId).child("sessions").child(sessionId)
            val label = when (userId) {
                "train_data" -> 0
                in listOf("demo_data", "sachin_data", "aman_data") -> 1
                else -> -1
            }

            if (sensorDataBatch.isNotEmpty()) {
                sessionRef.child("sensor").push().setValue(sensorDataBatch.map {
                    mapOf("type" to it.type, "values" to it.values, "timestamp" to it.timestamp, "label" to label)
                })
            }

            if (behaviorDataBatch.isNotEmpty()) {
                sessionRef.child("behavior").push().setValue(behaviorDataBatch.map {
                    mapOf("type" to it.type, "timestamp" to it.timestamp, "data" to it.data, "label" to label)
                })
            }

            detectAnomalies()
            sensorDataBatch.clear()
            behaviorDataBatch.clear()
            uploadHandler.postDelayed(this, uploadInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT, // For light mode scrim
                android.graphics.Color.TRANSPARENT  // For dark mode scrim
            )
        )

        database = FirebaseDatabase.getInstance().reference
        globalBehaviorDataBatch = behaviorDataBatch

        val label = when (userId) {
            "train_data" -> 0
            in listOf("demo_data", "sachin_data", "aman_data") -> 1
            else -> -1
        }

        val readableTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .apply { timeZone = TimeZone.getDefault() }
            .format(Date(sessionId.toLong()))

        database.child("users").child(userId).child("sessions").child(sessionId).child("metadata").setValue(
            mapOf(
                "sessionId" to sessionId,
                "startTime" to readableTime,
                "device" to Build.MODEL,
                "androidVersion" to Build.VERSION.SDK_INT,
                "label" to label
            )
        )

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val essentialSensors = listOf(
            Sensor.TYPE_ACCELEROMETER,
            Sensor.TYPE_GYROSCOPE,
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_ROTATION_VECTOR,
            Sensor.TYPE_PRESSURE,
            Sensor.TYPE_GRAVITY,
            Sensor.TYPE_LINEAR_ACCELERATION
        )
        sensorManager.getSensorList(Sensor.TYPE_ALL).forEach {
            if (it.type in essentialSensors) {
                sensorManager.registerListener(sensorListener, it, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }

        uploadHandler.postDelayed(uploadRunnable, uploadInterval)

        setContent {

            MaterialTheme {
                val hazeState = remember { HazeState() }
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        BankApp(
                            modifier = Modifier
                                .fillMaxSize()
                                .hazeSource(
                                    state = hazeState
                                ),
                            anomalyLogList = anomalyLogList,
                            activity = this@MainActivity,
                            navController = navController
                        )

                        HighAnomalyAlertDialog(
                            showDialog = showHighAnomalyDialog.value,
                            onDismiss = { showHighAnomalyDialog.value = false },
                            navController = navController, // Use the hoisted NavController
                            hazeState = hazeState,
                            anomalyScore = currentAnomalyScore.value
                        )

                        ExtremeAnomalyAlertDialog(
                            showDialog = showExtremeAnomalyDialog.value,
                            onDismiss = { showExtremeAnomalyDialog.value = false },
                            navController = navController, // Use the hoisted NavController
                            hazeState = hazeState,
                            anomalyScore = currentAnomalyScore.value
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(sensorListener)
        uploadHandler.removeCallbacks(uploadRunnable)
    }

    // Touch tracking
    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private var lastTouchTime = 0L

    @SuppressLint("ClickableViewAccessibility")
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val timestamp = System.currentTimeMillis()
        if (event.pointerCount == 1) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastTouchX = event.x
                    lastTouchY = event.y
                    lastTouchTime = timestamp
                    behaviorDataBatch.add(BehaviorRecord("TouchDown", timestamp, mapOf(
                        "x" to event.x,
                        "y" to event.y,
                        "pressure" to event.pressure,
                        "size" to event.size
                    )))
                }
                MotionEvent.ACTION_UP -> {
                    behaviorDataBatch.add(BehaviorRecord("TouchUp", timestamp, mapOf(
                        "x" to event.x,
                        "y" to event.y,
                        "pressure" to event.pressure
                    )))
                }
                MotionEvent.ACTION_MOVE -> {
                    val dt = (timestamp - lastTouchTime).coerceAtLeast(1)
                    val dx = event.x - lastTouchX
                    val dy = event.y - lastTouchY
                    val velocity = hypot(dx, dy) / dt * 1000
                    behaviorDataBatch.add(BehaviorRecord("Swipe", timestamp, mapOf(
                        "x" to event.x,
                        "y" to event.y,
                        "velocity" to velocity,
                        "pressure" to event.pressure,
                        "size" to event.size
                    )))
                    lastTouchX = event.x
                    lastTouchY = event.y
                    lastTouchTime = timestamp
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun detectAnomalies() {
        try {
            val model = Interpreter(loadModelFile("model.tflite"))
            val scalerArray = JSONArray(loadAssetText("scaler.json"))
            val thresholdJson = JSONObject(loadAssetText("thresholds.json"))

            val minArr = FloatArray(scalerArray.length()) { i ->
                val obj = scalerArray.getJSONObject(i)
                obj.getDouble("min").toFloat()
            }

            val maxArr = FloatArray(scalerArray.length()) { i ->
                val obj = scalerArray.getJSONObject(i)
                obj.getDouble("max").toFloat()
            }

            val featureList = mutableListOf<FloatArray>()
            val recentSize = minOf(sensorDataBatch.size, behaviorDataBatch.size, 10)

            for (i in 0 until recentSize) {
                val b = behaviorDataBatch.getOrNull(behaviorDataBatch.size - recentSize + i)
                val s = sensorDataBatch.getOrNull(sensorDataBatch.size - recentSize + i)
                if (b == null || s == null) continue

                val behaviorFeatures = floatArrayOf(
                    (b.data["typingSpeed"] as? Number)?.toFloat() ?: 0f,
                    (b.data["backspaceCount"] as? Number)?.toFloat() ?: 0f,
                    (b.data["typedCharCount"] as? Number)?.toFloat() ?: 0f,
                    ln(1f + ((b.data["velocity"] as? Number)?.toFloat() ?: 0f)),
                    (b.data["pressure"] as? Number)?.toFloat() ?: 0f,
                    (b.data["size"] as? Number)?.toFloat() ?: 0f,
                    (b.data["x"] as? Number)?.toFloat() ?: 0f,
                    (b.data["y"] as? Number)?.toFloat() ?: 0f
                )

                val sensorFeatures = FloatArray(3) { idx -> s.values.getOrNull(idx) ?: 0f }

                val combined = behaviorFeatures + sensorFeatures // total = 11 features
                if (combined.size != minArr.size) {
                    Log.e("AnomalyDetection", "Feature size mismatch: expected ${minArr.size}, got ${combined.size}")
                    return
                }

                val normalized = combined.mapIndexed { j, v ->
                    val min = minArr[j]
                    val max = maxArr[j]
                    ((v - min) / (max - min + 1e-6f)).coerceIn(0f, 1f)
                }.toFloatArray()

                Log.d("AnomalyDetection", "Raw combined features: ${combined.joinToString()}")
                Log.d("AnomalyDetection", "Normalized features: ${normalized.joinToString()}")

                featureList.add(normalized)
            }

            if (featureList.size < 10) {
                Log.d("AnomalyDetection", "Not enough data to run inference")
                return
            }

            val input = arrayOf(featureList.toTypedArray())
            val output = Array(1) { Array(10) { FloatArray(minArr.size) } }

            model.run(input, output)

            // Unscale output and input
            val unscaledOutput = output[0].map { timestep ->
                FloatArray(minArr.size) { j ->
                    (timestep[j] * (maxArr[j] - minArr[j] + 1e-6f)) + minArr[j]
                }
            }

            val unscaledInput = featureList.map { scaled ->
                FloatArray(minArr.size) { j ->
                    (scaled[j] * (maxArr[j] - minArr[j] + 1e-6f)) + minArr[j]
                }
            }

            Log.d("AnomalyDetection", "Unscaled input[0]: ${unscaledInput[0].joinToString()}")
            Log.d("AnomalyDetection", "Unscaled output[0]: ${unscaledOutput[0].joinToString()}")

            val reconError = unscaledInput.zip(unscaledOutput).map { (orig, pred) ->
                orig.zip(pred).sumOf { (a, b) -> abs(a - b).toDouble() }
            }.average().toFloat()

            Log.d("AnomalyDetection", "Recon error: $reconError")

            val mode = detectInteractionMode(unscaledInput[0])

            val thresholdsTyping = mapOf(
                "low" to 20f,
                "medium" to 30f,
                "high" to 40f,
                "extreme" to 50f
            )
            val thresholdsSwiping = mapOf(
                "low" to 450f,
                "medium" to 550f,
                "high" to 650f,
                "extreme" to 700f
            )

            val thresholds = if (mode == "typing") thresholdsTyping else thresholdsSwiping

            Log.d("AnomalyDetection", "Mode: $mode")
            Log.d("AnomalyDetection", "Recon error: $reconError")
            Log.d("AnomalyDetection", "Thresholds → $thresholds")

            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            runOnUiThread {
                currentAnomalyScore.value = reconError // Store score for dialogs
                when {
                    reconError > thresholds["extreme"]!! -> {
                        Log.w("AnomalyDetection", "Extreme anomaly detected. Score: $reconError")
                        anomalyLogList.add(AnomalyLogEntry(timestamp, "Extreme", reconError))
                        showExtremeAnomalyDialog.value = true
                    }
                    reconError > thresholds["high"]!! -> {
                        Log.w("AnomalyDetection", "High anomaly detected. Score: $reconError")
                        anomalyLogList.add(AnomalyLogEntry(timestamp, "High", reconError))
                        showHighAnomalyDialog.value = true
                    }
                    reconError > thresholds["medium"]!! -> {
                        Log.i("AnomalyDetection", "Medium anomaly detected. Score: $reconError")
                        anomalyLogList.add(AnomalyLogEntry(timestamp, "Medium", reconError))
                    }
                    reconError > thresholds["low"]!! -> {
                        Log.i("AnomalyDetection", "Low anomaly detected. Score: $reconError")
                        anomalyLogList.add(AnomalyLogEntry(timestamp, "Low", reconError))
                    }
                    else -> {
                        Log.d("AnomalyDetection", "✅ No anomaly detected. Score: $reconError")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("AnomalyDetection", "Error in detection: ${e.message}", e)
        }
    }

    private fun detectInteractionMode(unscaled: FloatArray): String {
        val typingSpeed = unscaled.getOrNull(0) ?: 0f      // index 0: typingSpeed
        val typedCharCount = unscaled.getOrNull(2) ?: 0f   // index 2: typedCharCount
        val swipeVelocity = unscaled.getOrNull(3) ?: 0f    // index 3: swipeVelocity

        return when {
            typingSpeed > 0f || typedCharCount > 0f -> "typing"
            swipeVelocity > 0f -> "swiping"
            else -> "unknown"
        }
    }

    private fun loadModelFile(name: String): MappedByteBuffer {
        val fileDescriptor = assets.openFd(name)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        return inputStream.channel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    private fun loadAssetText(name: String): String {
        return assets.open(name).bufferedReader().use { it.readText() }
    }

    private fun showAlert(msg: String) {
        runOnUiThread {
            anomalyDialogMessage.value = msg
            showAnomalyDialog.value = true
        }
    }
}

fun logTextFieldBehavior(
    label: String,
    oldText: String,
    newText: String,
    lastTimestamp: Long
): Long {
    val currentTimestamp = System.currentTimeMillis()
    val timeTaken = currentTimestamp - lastTimestamp
    val backspaceCount = if (newText.length < oldText.length) oldText.length - newText.length else 0
    val typedCharCount = if (newText.length > oldText.length) newText.length - oldText.length else 0

    val record = MainActivity.BehaviorRecord(
        type = "Typing",
        timestamp = currentTimestamp,
        data = mapOf(
            "field" to label,
            "oldText" to oldText,
            "newText" to newText,
            "typedCharCount" to typedCharCount,
            "backspaceCount" to backspaceCount,
            "typingSpeed" to if (typedCharCount > 0) typedCharCount.toFloat() / (timeTaken / 1000.0) else 0f,
            "duration" to timeTaken
        )
    )
    MainActivity.globalBehaviorDataBatch.add(record)

    return currentTimestamp
}

fun logDebugMetrics(
    context: Context,
    rawFeatures: FloatArray,
    normalized: FloatArray,
    reconError: Float
) {
    try {
        val logDir = File(context.filesDir, "debug_logs")
        if (!logDir.exists()) {
            logDir.mkdirs()
        }

        val logFile = File(logDir, "anomaly_debug_log.txt")
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        val logEntry = buildString {
            appendLine("Timestamp: $timestamp")
            appendLine("Raw Features: ${rawFeatures.joinToString(", ")}")
            appendLine("Normalized: ${normalized.joinToString(", ")}")
            appendLine("Recon Error: $reconError")
            appendLine("--------------------------------------------------")
        }

        FileOutputStream(logFile, true).bufferedWriter().use { writer ->
            writer.write(logEntry)
        }

        Log.d("AnomalyLogger", "✅ Debug metrics logged")

    } catch (e: Exception) {
        Log.e("AnomalyLogger", "❌ Error logging debug metrics", e)
    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BankApp(modifier: Modifier = Modifier, anomalyLogList: List<AnomalyLogEntry>, activity: ComponentActivity,
            navController: NavHostController
) {

    NavHost(navController = navController, startDestination = "intro_screen") {
        composable(
            route = "intro_screen?lockdown={lockdown}",
            arguments = listOf(navArgument("lockdown") {
                type = NavType.BoolType
                defaultValue = false
            })
        ) { backStackEntry ->
            IntroScreen(
                navController = navController,
                activity = activity,
                startLockdown = backStackEntry.arguments?.getBoolean("lockdown") ?: false
            )
        }

        composable("password_screen") {
            PasswordScreen(navController = navController, activity = activity)
        }

        composable(
            route = "home_screen"
        ) {
            HomeScreen(navController = navController)
        }

        composable("payment_screen") {
            PaymentsPage(navController = navController)
        }

        composable("register_screen") {
            RegisterScreen(navController = navController)
        }

        composable("pin_screen") {
            UPIPayScreen(navController = navController)
        }

        composable("set_pin_screen") {
            SetPinScreen(navController = navController)
        }

        composable("phonebook_screen") {
            ContactsScreen(navController = navController)
        }

        composable("pay_contacts") {
            PayToContact(navController = navController)
        }

        composable("pay_with_card") {
            CardPayScreen(navController = navController)

        }
        composable("change_cvv") {
            ChangeCVV(navController = navController)
        }
        composable("my_cards") {
            CardsScreen(navController = navController)
        }
        composable("payment_success") {
            PaymentAnimation(navController = navController)
        }

        composable("dashboard") {
            AnomalyDashboard(navController = navController, anomalyLogList = anomalyLogList)
        }
    }
}
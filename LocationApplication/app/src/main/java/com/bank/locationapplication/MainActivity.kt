package com.bank.locationapplication

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bank.locationapplication.ui.theme.LocationApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LocationViewModel=viewModel()
            LocationApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding),
                        viewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier,
          viewModel: LocationViewModel)
{
    val context1= LocalContext.current
    val locationU= LocationUtils(context1)
    LocationDisplay(locationU=locationU,
        viewModel=viewModel, context = context1)
}


@Composable
fun LocationDisplay(locationU : LocationUtils,
                    viewModel: LocationViewModel,
                    context: Context)
{
    val location=viewModel.location.value
    val requestPermition= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions() ,
        onResult={
            permissions->
            if(permissions[Manifest.permission.ACCESS_COARSE_LOCATION]==true
                && permissions[Manifest.permission.ACCESS_FINE_LOCATION]==true)
            {
                locationU.requestLocationUpdates(viewModel=viewModel)
            }
            else{
                val rationalRequired= ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )|| ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if(rationalRequired)
                {
                    Toast.makeText(context,"Location permition is equired", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(context,"go to setting to grant permition", Toast.LENGTH_SHORT).show()
                }
            }
        })
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Location not Available")
        Button(onClick = {
            if(locationU.hasLocation(context)
                ){

            }
            else
            {
                requestPermition.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }) { }

    }
}
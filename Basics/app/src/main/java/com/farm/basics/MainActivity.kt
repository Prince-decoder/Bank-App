package com.farm.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.farm.basics.Fundamental_concept.Annotated_String
import com.farm.basics.Fundamental_concept.Sam_OutLinedTextField
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.farm.basics.Fundamental_concept.AccessString
//import com.farm.basics.Fundamental_concept.Greeting
//import com.farm.basics.Fundamental_concept.SampleOutline
import com.farm.basics.Fundamental_concept.SamplePassword
import com.farm.basics.Fundamental_concept.Sample_Button
import com.farm.basics.Fundamental_concept.Sample_Checkbox
import com.farm.basics.Fundamental_concept.Sample_DetailedDrawer
import com.farm.basics.Fundamental_concept.Sample_Mainu
import com.farm.basics.Fundamental_concept.Sample_PartialBottomSheet
import com.farm.basics.Fundamental_concept.Sample_TonalButton
import com.farm.basics.Fundamental_concept.Selectable_and_Non_Selectable_Text
import com.farm.basics.ui.theme.BasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsTheme {
//                Greeting("prince")
//                SampleOutline()
//                SamplePassword()
//                Selectable_and_Non_Selectable_Text()
//                Annotated_String()
//                Sample_Button()
//                Sample_TonalButton()
//                Sam_OutLinedTextField()
//                Sample_PartialBottomSheet()
//                Sample_Checkbox()
//                Sample_Mainu()
//                Annotated_String()
                Sample_DetailedDrawer()
            }
        }
    }
}



package com.bank.bankapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bank_app.R

val facultyGlyphic = FontFamily(
    Font(R.font.faculty_glyphic_regular)
)

@Composable
@Preview(showBackground = true)
fun ContactsScreenPreview(){
    val previewNavController = rememberNavController()

    ContactsScreen(previewNavController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen(navController: NavHostController) {
    val hazeState = remember { HazeState() }
    val navigateToPay = "pay_contacts"
    val contacts = remember { mutableStateOf(
        listOf(
            Pair("Fernando Alonso", Icons.Default.Person),
            Pair("Zinedine Zidane", Icons.Default.Person),
            Pair("Mika Hakkinen", Icons.Default.Person),
            Pair("Charles Leclerc", Icons.Default.Person),
            Pair("Kimi Raikkonen", Icons.Default.Person),
            Pair("Jean Todt", Icons.Default.Person),
            Pair("Jose Mourinho", Icons.Default.Person),
            Pair("Alex Ferguson", Icons.Default.Person),
            Pair("Max Emilian Verstappen", Icons.Default.Person),
            Pair("Valentino Rossi", Icons.Default.Person),
            Pair("Laurens Vanthoor", Icons.Default.Person),
            Pair("Kevin Estre", Icons.Default.Person),
            Pair("Sebastian Vettel", Icons.Default.Person),
            Pair("Antonio Giovinazzi", Icons.Default.Person),
            Pair("Ross Brawn", Icons.Default.Person),
            Pair("Toto Wolff", Icons.Default.Person),
            Pair("Niki Lauda", Icons.Default.Person),
            Pair("Marc Marquez", Icons.Default.Person),
            Pair("Alessandro Pier Guidi", Icons.Default.Person),
            Pair("Alberto Ascari", Icons.Default.Person),
        )
    ) }
    var showDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf("") }
    var newImageRes by remember { mutableStateOf(Icons.Default.Contacts )}

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(380.dp, 75.dp)
                    .height(64.dp)
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
                        .size(60.dp)
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
                    "Pay to Phonebook",
                    modifier = Modifier
                        .weight(7f)
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .offset(0.dp, 33.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start,
                        fontFamily = facultyGlyphic,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                // 2. Add contact on icon click
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .size(60.dp)
                        .padding(4.dp, 30.dp, 4.dp, 0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PersonAddAlt1,
                        contentDescription = "Add Contact",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
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
                Modifier
                    .hazeSource(hazeState)
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.size(80.dp).fillMaxWidth())

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Add New Contact") },
                        text = {
                            Column {
                                OutlinedTextField(
                                    value = newName,
                                    onValueChange = { newName = it },
                                    label = { Text("Contact Name") }
                                )
                                // Placeholder for image picker
                                Text("Image: (default used, add picker as needed)")
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    if (newName.isNotBlank()) {
                                        contacts.value = contacts.value + Pair(newName, newImageRes)
                                        newName = ""
                                        showDialog = false
                                    }
                                }
                            ) { Text("Add") }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) { Text("Cancel") }
                        }
                    )
                }

                contacts.value.forEach { (name, imageRes) ->
                    createContactTab(
                        name = name,
                        navController = navController,
                        route = navigateToPay,
                        hazeState = hazeState,
                        imageAddress = imageRes
                    )
                }
                Spacer(Modifier.size(80.dp).fillMaxWidth())
            }
        }
    }
}

@Composable
fun createContactTab(
    name: String = "User",
    navController: NavHostController,
    route: String,
    hazeState: HazeState,
    imageAddress: ImageVector,
)
{
    Row(
        modifier = Modifier
            .size(380.dp, 65.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                true,
                onClick = { navController.navigate(route)},
                onClickLabel = null),
    ) {
        Image(
            imageVector = imageAddress,
            contentDescription = null,
            modifier = Modifier
//                .weight(1f)
                .size(60.dp)
                .padding(15.dp, 1.dp, 1.dp, 1.dp)
                .clip(CircleShape),
        )

        Text(
            text = name,
            color = Color.White,
            modifier = Modifier
                //.weight(8f)
                .padding(15.dp, 15.dp, 15.dp, 15.dp),
            style = TextStyle(
                fontSize = 19.sp,
                textAlign = TextAlign.Start,
                fontFamily = facultyGlyphic,
                fontWeight = FontWeight.W300
            )
        )
    }
}
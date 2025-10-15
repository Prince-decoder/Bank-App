package com.bank.practiceapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

data class Shopping(
    var Name:String,
    val id:Int,
    var qty: Int,
    var isediting: Boolean=false
)
@Composable
fun ShoppingList(modifier: Modifier)
{
    var showDialog by remember { mutableStateOf(false) }
    var ListsOfitems by remember { mutableStateOf(mutableListOf<Shopping>()) }
    Column(modifier= Modifier.fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(ListsOfitems) { items ->
                if(items.isediting)
                {
                    Modification(item=items,
                        onEditingComplete ={
                            editedName,editedqty->
                            ListsOfitems=ListsOfitems.map { it.copy(isediting=false) }.toMutableList()
                            val editeditem=ListsOfitems.find {
                                it.id==items.id }
                            editeditem?.let {
                                it.Name=editedName
                                it.qty=editedqty
                            }
                        }
                    )
                }
                else{
                    ListOverview(items,
                        onEdit = {
                            ListsOfitems=ListsOfitems.map { it.copy(isediting = it.id==items.id) }.toMutableList()
                        },
                        onDelete = {
                            ListsOfitems=(ListsOfitems-items).toMutableList()
                        })
                }
            }
        }
    }
        if(showDialog)
        {
            var Name by remember { mutableStateOf("") }
            var Quantity by remember { mutableStateOf("") }
            AlertDialog(onDismissRequest = {showDialog=false},
                confirmButton = {
                    Row(modifier= Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                        Button(onClick = {
                            if(Name.isNotBlank())
                            {
                                var tem=Shopping(
                                    Name=Name,
                                    ListsOfitems.size+1,
                                    qty = Quantity.toInt()
                                )
                                ListsOfitems=(ListsOfitems+tem).toMutableList()
                                showDialog=false
                                Name=""
                            }
                        }) {
                            Text("add")
                        }
                        Button(onClick = {showDialog=false}) {
                            Text("Cancel")
                        }
                    }
                },
                title = {Text(text = "Adding items",
                    fontStyle = FontStyle.Italic)},
                text = {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {

                        OutlinedTextField(
                            value = Name,
                            onValueChange = {
                                Name = it
                            },
                            modifier= Modifier.fillMaxWidth().padding(8.dp),
                            singleLine = true,
                            label = { Text("Name") })
                        Spacer(modifier= Modifier.height(8.dp))
                        OutlinedTextField(
                            value = Quantity,
                            onValueChange = {
                                Quantity = it
                            },
                            modifier= Modifier.fillMaxWidth().padding(8.dp),
                            singleLine = true,
                            label = { Text("Quantity") })
                    }
                })
        }
    }

@Composable
fun ListOverview(item: Shopping,
                 onEdit:()->Unit,
                 onDelete:()->Unit)
{
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = item.Name)
        Text(text = "qty=${item.qty}")
        IconButton(onClick = {
            onEdit()
        }) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "",
            )
        }
        IconButton(onClick = {
            onDelete()
        }) {
            Icon(Icons.Default.Delete,
                "")
        }
    }
}
@Composable
fun Modification(
    item: Shopping,
    onEditingComplete: (String, Int)-> Unit
)
{
    var EditingName by remember { mutableStateOf(item.Name) }
    var EditingQty by remember { mutableStateOf(item.qty.toString()) }
    var isEditing by remember { mutableStateOf(item.isediting) }
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Start
    )
    {
        Column{
            TextField(
                value = EditingName,
                onValueChange = {
                    EditingName=it
                },
                singleLine = true
            )
            TextField(
                value = EditingQty,
                onValueChange = {
                    EditingQty=it
                },
                singleLine = true
            )
        }
        Column {
            Button(onClick = {
                isEditing=false
                onEditingComplete(EditingName,EditingQty.toIntOrNull()?:1)
            }) {
                Text("Save")
            }
        }
    }

}
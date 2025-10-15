package com.bank.shopinglistapp


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.room.util.copy



data class Shopping(var Name:String,
               val Id:Int,
               var quantity: Int,
               var isEdit: Boolean=false
): ViewModel()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList()
{
    var sItems by remember { mutableStateOf(mutableListOf<Shopping>()) }
    var showDialog by remember { mutableStateOf(false) }
    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(onClick = {
            showDialog=true
            Toast.makeText(context,"Adding",Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier
        ) {
            Text("Add Items")
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            items(sItems){
                item->
                 if(item.isEdit)
                 {
                     ShoppingEditor(item=item, onEditComplete ={
                         editedName,editedQyt->
                         sItems=sItems.map{it.copy(isEdit=false)}.toMutableList()
                         val editedItem= sItems.find { it.Id == item.Id }
                         editedItem?.let {
                             it.Name=editedName
                             it.quantity=editedQyt
                         }
                     } )
                 }
                else
                 {
                     ShopList(item, onEditClick = {
                         // finding out which item we are editing and changing
                         sItems=sItems.map { it.copy(isEdit = it.Id==item.Id) }.toMutableList()
                     }, onDeleteClick = {
                         sItems=(sItems-item).toMutableList()
                     })
                 }
            }
        }
    }
    if(showDialog)
    {
        var Name by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        AlertDialog(onDismissRequest = {showDialog=false},
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween)//this will separate at end of row(start and end)
                {
                    Button(onClick = {
                        if(Name.isNotBlank())
                        {
                            val newItem= Shopping(
                                Name=Name,
                                Id = sItems.size+1,
                                quantity =quantity.toInt(),
                                )
                            sItems= (sItems + newItem) as MutableList<Shopping>
                            showDialog=false
                            Name=""
                        }
                    }){Text("Add")}
                    Button(onClick = {showDialog=false}) { Text("Cancel")}
                }
            },
            title = {Text("Add Shopping Item")},
            text = {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(value = Name,
                        onValueChange = {Name=it},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),
                        label = {Text("Name")}
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(value = quantity,
                        onValueChange =
                            {
                                quantity=it
                            },
                        singleLine = true,//it allow only one line to innput
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),
                        label = {Text("Quantity")}
                    )
                }
            })
    }
}
@Composable
fun ShopList(item: Shopping,
             onEditClick:()-> Unit,                 //lamda function
             onDeleteClick:()->Unit,                //lamda function

             )
{
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .border(border = BorderStroke(2.dp, Color.Magenta),
                shape = RoundedCornerShape(20)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.Name,
            modifier = Modifier.padding(12.dp)
        )

        Text(
            text = "Qty=${item.quantity}",
            fontSize = 8.sp
        )
            var Context=LocalContext.current
            var Context2=LocalContext.current
            IconButton(
                onClick = {onEditClick()
                    Toast.makeText(Context,"Edit",Toast.LENGTH_SHORT).show()}
            )
            {
                Icon(Icons.Default.Edit, contentDescription = "Editing",
                    //modifier = Modifier.size(15.dp)
                )
            }
            IconButton(
                onClick = {onDeleteClick()
                    Toast.makeText(Context2,"Delete",Toast.LENGTH_SHORT).show()}
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete",
                    //modifier = Modifier.size(15.dp)
                )
            }
        }
    }
//}
@Composable
fun ShoppingEditor(item: Shopping,
                   onEditComplete:(String,Int)->Unit)
{
    var EditingName by remember { mutableStateOf(item.Name) }
    var EditingQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEdit) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly)
    {
        Column {
            TextField(value = EditingName,
                onValueChange = { EditingName=it},
                singleLine = true,
                modifier = Modifier.wrapContentSize()
                    .padding(8.dp))
            TextField(value = EditingQuantity,
                onValueChange = {EditingQuantity=it},
                singleLine = true,
                modifier = Modifier.wrapContentSize()
                    .padding(8.dp)
            )
        }
        Column {
            Button(onClick = {
                isEditing=false
                onEditComplete(EditingName,EditingQuantity.toIntOrNull() ?:1)
            }) {
                Text("Save")
            }
        }
    }
}
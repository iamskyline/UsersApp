package com.example.usersdbapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usersdbapp.data.UsersEntity
import com.example.usersdbapp.ui.theme.UsersDbAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

val mainColor = "#e2e2e2".toColor()
val cardColor = "#e6ffec".toColor()
val inputColor = "#cdcdcd".toColor()

fun String.toColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainArea()
        }
    }
}

@Composable
fun MainArea(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mainColor)
        ) {
            InputUserInitials(mainViewModel)
            LazyColumn() {
                items(itemsList.value) { item ->
                    UserItem(item, {
                        mainViewModel.user = it
                        mainViewModel.nameTextField.value = it.Username
                        mainViewModel.surnameTextField.value = it.Usersurname
                        mainViewModel.lastnameTextField.value = it.Userlastname
                    },
                        {
                            mainViewModel.DeleteItem(it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun UserItem(
    item: UsersEntity,
    onClickEdit: (UsersEntity) -> Unit,
    onClickDelete: (UsersEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("UserItem", "Clicked on UserItem")
                    onClickEdit(item)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Text(text = item.Username, modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp))
                Text(text = item.Usersurname, modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp))
                Text(text = item.Userlastname, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp))
            }
            IconButton(modifier = Modifier.size(30.dp),
                onClick = {
                    Log.d("UserItem", "Clicked on Delete button")
                    onClickDelete(item)
                }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun InputUserInitials(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                CustomTextField(mainViewModel.nameTextField, "Имя")
                CustomTextField(mainViewModel.surnameTextField, "Фамилия")
                CustomTextField(mainViewModel.lastnameTextField, "Отчество")
            }
            IconButton(onClick = {
                mainViewModel.InsertItem()
            }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(mainViewModelType: MutableState<String>, label: String){
    OutlinedTextField(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 7.dp),
        value = mainViewModelType.value,
        onValueChange = {
            mainViewModelType.value = it
        },
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = inputColor,
        ),
        shape = RoundedCornerShape(10.dp)
    )
}
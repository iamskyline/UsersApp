package com.example.usersdbapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usersdbapp.MainViewModel
import com.example.usersdbapp.inputBorderColor
import com.example.usersdbapp.inputColor
import com.example.usersdbapp.inputColorText

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
            focusedIndicatorColor = inputBorderColor,
            focusedLabelColor = inputColorText,
            cursorColor = inputColorText
        ),
        shape = RoundedCornerShape(10.dp)
    )
}
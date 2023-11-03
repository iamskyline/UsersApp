package com.example.usersdbapp.components

import android.view.MenuItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerMenu() {
    /*LazyColumn() {
        item {
            MenuItem("Добавление пользователя", Icons.Default.Add)
        }
        item {
            MenuItem("Просмотр пользователей", Icons.Default.AccountCircle)
        }
    }*/
    Column {
        MenuItem("Добавление пользователя", Icons.Default.Add)
        MenuItem("Просмотр пользователей", Icons.Default.AccountCircle)
    }
}

@Composable
fun MenuItem(title: String, image: ImageVector) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = title, fontSize = 18.sp
            )
            Icon(
                modifier = Modifier.padding(10.dp, 0.dp),
                imageVector = image,
                contentDescription = "Сопровождащая иконка"
            )
        }
    }
}


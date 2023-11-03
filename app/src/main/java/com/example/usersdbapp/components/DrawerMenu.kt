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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenu(
    titleState: MutableState<String>, navController: NavHostController,
    drawerState: DrawerState
) {
    Column {
        MenuItem(
            titleState, "Добавление пользователя", Icons.Default.Add,
            navController, drawerState
        )
        MenuItem(
            titleState, "Просмотр пользователей", Icons.Default.AccountCircle,
            navController, drawerState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(
    titleState: MutableState<String>, title: String,
    image: ImageVector, navController: NavHostController,
    drawerState: DrawerState
) {
    val closeBarCoroutine = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    closeBarCoroutine.launch {
                        drawerState.close()
                    }
                    titleState.value = title
                    if (title == "Добавление пользователя") {
                        navController.navigate("inputScreen")
                    } else if (title == "Просмотр пользователей") {
                        navController.navigate("usersScreen")
                    }
                },
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
                contentDescription = "Сопровождающая  иконка"
            )
        }
    }
}


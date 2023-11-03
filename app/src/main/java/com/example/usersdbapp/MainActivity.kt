@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.usersdbapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usersdbapp.components.BottomBar
import com.example.usersdbapp.components.DrawerMenu
import com.example.usersdbapp.components.InputUserInitials
import com.example.usersdbapp.components.TopBar
import com.example.usersdbapp.components.UserItem
import com.example.usersdbapp.data.UsersEntity
import com.example.usersdbapp.ui.theme.UsersDbAppTheme
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

val mainColor = "#e2e2e2".toColor()
val cardColor = "#ffd392".toColor()
val cardBorderColor = "#ed8e00".toColor()
val inputColor = "#cdcdcd".toColor()
val inputBorderColor = "#ff9900".toColor()
val inputColorText = "#565656".toColor()

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

@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainArea(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory),
) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // add drawer content here
                // this is a column scope
                // so, if you add multiple elements, they are placed vertically
                DrawerMenu()
            }
        }
    ) {
        // app content
        // add scaffold here
        Scaffold(
            topBar = {
                TopBar(drawerState, coroutine)
            },
            bottomBar = {
                BottomBar()
            }
        ) { paddingValues ->
            // rest of the app's UI
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Box(modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 10.dp)
                    .clip(RoundedCornerShape(25.dp))) {
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
        }
    }
}
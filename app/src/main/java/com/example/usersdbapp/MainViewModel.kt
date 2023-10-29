package com.example.usersdbapp

import android.content.ClipData.Item
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.usersdbapp.data.UsersDB
import com.example.usersdbapp.data.UsersEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val db: UsersDB) : ViewModel() {
    val itemsList = db.dao.GetItems()
    val nameTextField = mutableStateOf("")
    val surnameTextField = mutableStateOf("")
    val lastnameTextField = mutableStateOf("")
    var user: UsersEntity? = null

    fun InsertItem() = viewModelScope.launch {
        try {
            val userItem = user?.copy(
                Username = nameTextField.value,
                Usersurname = surnameTextField.value,
                Userlastname = lastnameTextField.value
            )
                ?: UsersEntity(
                    Username = nameTextField.value,
                    Usersurname = surnameTextField.value,
                    Userlastname = lastnameTextField.value
                )
            withContext(Dispatchers.IO) {
                // Выполнение операции добавления в фоновом потоке
                db.dao.InsertItem(userItem)
            }
            user = null
            nameTextField.value = ""
            surnameTextField.value = ""
            lastnameTextField.value = ""
        } catch (e: Exception){
            Log.d("InsertException", e.toString())
        }
    }

    fun DeleteItem(item: UsersEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            // Выполнение операции удаления в фоновом потоке
            db.dao.DeleteItem(item)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val db = (checkNotNull(extras[APPLICATION_KEY]) as App).db
                return MainViewModel(db) as T
            }
        }
    }
}
package com.example.usersdbapp

import android.app.Application
import com.example.usersdbapp.data.UsersDB

class App : Application() {
    //Инициализация базы
    val db by lazy { UsersDB.createDB(this) }
}
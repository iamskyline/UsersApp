package com.example.usersdbapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    val Id: Int? = null,
    val Username: String,
    val Usersurname: String,
    val Userlastname: String
)

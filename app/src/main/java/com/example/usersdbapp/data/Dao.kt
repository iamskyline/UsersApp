package com.example.usersdbapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    //Добавление + измение
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun InsertItem(usersEntity: UsersEntity)
    //Удаление
    @Delete
     fun DeleteItem(usersEntity: UsersEntity)
    //Выборка
    @Query("SELECT * FROM UsersEntity")
    fun GetItems(): Flow<List<UsersEntity>>
}
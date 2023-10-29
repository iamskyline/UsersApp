package com.example.usersdbapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UsersEntity::class],
    version = 1,
)
abstract class UsersDB : RoomDatabase() {
    abstract val dao: Dao
    companion object{
        fun createDB(context: Context) : UsersDB{
            return Room.databaseBuilder(
                context,
                UsersDB::class.java,
                "users.db"
            ).build()
        }
    }
}
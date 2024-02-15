package com.example.homework19

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class MyApplication: Application() {
    lateinit var repo: Repository
    override fun onCreate() {
        super.onCreate()
        instance = this
        val builder = Room.databaseBuilder(this, Database::class.java, "list_db")
            .build()
            repo = Repository(builder)
    }





    companion object{
        private lateinit var instance: MyApplication
        fun getInstance() = instance
    }
}
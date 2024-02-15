package com.example.homework19

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase



@Entity
data class Task (@PrimaryKey (autoGenerate = true) val id: Int? = null, val action: String)

@Dao

interface TaskDao {
    @Insert
    fun add(task: Task)
    @Delete
    fun delete(task: Task)
    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>
}

@Database (entities = [Task::class], version = 1)
abstract class Database: RoomDatabase(){
    abstract fun taskDao(): TaskDao
}

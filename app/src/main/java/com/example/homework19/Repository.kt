package com.example.homework19

// ITS MODEL



import java.util.concurrent.Executors

class Repository(private val database: Database) {
private val executor = Executors.newSingleThreadExecutor()

    fun addToList(task: Task) = executor.execute { database.taskDao().add(task) }
    fun deleteFromList(task: Task) = executor.execute { database.taskDao().delete(task) }
    fun getAll() = database.taskDao().getAll()
}
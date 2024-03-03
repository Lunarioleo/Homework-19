package com.example.homework26

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class Database @Inject constructor() {
    fun getDatabaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance("https://fbtest-99139-default-rtdb.europe-west1.firebasedatabase.app/")
    }
}
package com.example.homework26


import com.google.firebase.database.DatabaseReference
import java.util.UUID
import javax.inject.Inject


class Repository @Inject constructor(private val db: Database) {
    fun getDatabasePath(): DatabaseReference {
        return db.getDatabaseInstance().reference.child("test_account")
            .child("todoTask")
            .child(UUID.randomUUID().toString())
    }

    fun getDatabaseReference(): DatabaseReference {
        return db.getDatabaseInstance().reference.child("test_account")
            .child("todoTask")
    }
}
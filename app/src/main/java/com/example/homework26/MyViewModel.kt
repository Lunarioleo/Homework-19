package com.example.homework26

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    private val _uiState = MutableLiveData<UiStates>()
    val uiState: LiveData<UiStates> = _uiState

    fun addTask(s: String){
        val target = repo.getDatabasePath()
        target.setValue(s).addOnCompleteListener {
        }
    }

    fun updateList(snapshot: DataSnapshot){
        val taskList = mutableListOf<String>()
        if (snapshot.exists()){
            snapshot.children.forEach {
                val task = it?.getValue(String::class.java) ?: ""
                taskList.add(task)
                _uiState.postValue(UiStates.ResultState(taskList))
            }
        }
    }

    fun getDatabaseRef(): DatabaseReference{
       return  repo.getDatabaseReference()
    }


    sealed class UiStates {
        class ResultState(val l: List<String>) : UiStates()
    }
}
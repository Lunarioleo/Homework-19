package com.example.homework19

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

//ITS VIEWMODEL

class ViewModel(): ViewModel() {
    private val repository = MyApplication.getInstance().repo
    private val _listState = MutableLiveData<States>(States.EmptyList)
    val listState: LiveData<States> = _listState

    private val observer = Observer<List<Task>> {
        _listState.postValue(States.UpdatedList(list = it))
    }

    init {
        repository.getAll().observeForever(observer)
    }

    fun addToList(action: String){
        repository.addToList(Task(action = action))
    }

    fun removeFromList(task: Task){
        repository.deleteFromList(task)
    }
    override fun onCleared() {
        repository.getAll().removeObserver(observer)
        super.onCleared()
    }

    sealed class States {
        data object EmptyList: States()
        class UpdatedList(val list:List<Task>): States()

    }
}
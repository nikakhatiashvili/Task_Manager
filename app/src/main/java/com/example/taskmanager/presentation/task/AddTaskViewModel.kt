package com.example.taskmanager.presentation.task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.domain.tasks.UserUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddTaskViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<UserUi>>(emptyList())
    val users = _users.asStateFlow()


    private val checkedUsers = mutableListOf<String>()

    fun addTask(name:String,description:String,boolean: Boolean){

        Log.d("asdasdasdasda", name + description + boolean.toString() + checkedUsers.toString())
    }

    fun handleUsers(remove: Boolean,id: String){
        if (checkedUsers.contains(id)) checkedUsers.remove(id)
        if (remove) checkedUsers.add(id) else checkedUsers.remove(id)

    }

    fun getUsers(){
        viewModelScope.launch {
            _users.emit(
                listOf(
                    UserUi("1","Nikolozi","nikaKhatiashvili44@gmail.com"),
                    UserUi("12","Lizi","LiziYvitelidze@gmail.com"),
                    UserUi("123","merab","merabGreidze@gmail.com"),
                    UserUi("1234","anano","ananoWeladze@gmail.com"),
                    UserUi("12345","natia","natiaShavladze4@gmail.com"),
                    UserUi("123456","mariam","mariamTetraze@gmail.com"),
                    UserUi("1234567","soso","sosoGvari44@gmail.com"),
                    UserUi("12345678","gio","gioDvali@gmail.com"),
                )
            )
        }
    }
}
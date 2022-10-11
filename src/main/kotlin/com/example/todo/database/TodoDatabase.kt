package com.example.todo.database

data class TodoDatabase (
    var index: Int = 0
    var todoLists: MutableList<String> = mutableListOf()
){

    fun init(){
        this.todoLists = mutableListOf()
        println("DEBUG todo database init")
    }

}


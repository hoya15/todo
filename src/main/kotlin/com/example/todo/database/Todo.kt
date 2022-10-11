package com.example.todo.database

import java.rmi.registry.LocateRegistry
import java.time.LocalDateTime

data class Todo(

    var index: Int?=null,
    var title : String?=null,
    var description : String?=null,
    var schedule: LocalDateTime?=null,
    var createAt: LocalDateTime?=null,
    var updateAt: LocalDateTime?=null
)

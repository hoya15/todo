package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.xml.ws.ServiceMode

@Service
class TodoRepositoryImpl (
     val todoDataBase: TodoDataBase
):TodoRepository{

    override fun save(todo: Todo): Todo? {

        // 1. index ??
        return todo.index?.let {  index->
            findOne( index )?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updateAt = LocalDateTime.now()
            }

        }?: kotlin.run {
            // insert
            todo.apply {
                this.index = ++todoDataBase.index
                this.createAt = LocalDateTime.now()
                this.updateAt = LocalDateTime.now()
            }.run {
                todoDataBase.todoList.add(todo)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {

        return try {
            todoList.forEach(){
                save(it)
            }
            true
        } catch (e: Exception){
            false
        }
    }

    override fun delete(index: Int): Boolean {

        val todo = findOne(index)

        return todo?.let {
            todoDataBase.todoList.remove(it)
            true
        }?:kotlin.run {
            false
        }
    }

    override fun findOne(index: Int): Todo? {

        return todoDataBase.todoList.first { it.index == index }

    }

    override fun findALl(): MutableList<Todo> {
        return  todoDataBase.todoList
    }

}
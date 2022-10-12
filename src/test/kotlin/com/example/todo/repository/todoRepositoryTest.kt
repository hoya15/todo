package com.example.todo.repository

import com.example.todo.config.AppConfig
import com.example.todo.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class todoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest(){
        val todo = Todo().apply {
            this.title = "테스트일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createAt)
        Assertions.assertNotNull(result?.updateAt)
        Assertions.assertEquals("테스트일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)
    }

    @Test
    fun saveAllTest(){
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트일정11"
                this.description = "테스트11"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트일정22"
                this.description = "테스트22"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트일정33"
                this.description = "테스트33"
                this.schedule = LocalDateTime.now()
            }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)

    }

    @Test
    fun findOneTest(){

        val todoList = mutableListOf(
            Todo().apply {
                this.title = "테스트일정11"
                this.description = "테스트11"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트일정22"
                this.description = "테스트22"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "테스트일정33"
                this.description = "테스트33"
                this.schedule = LocalDateTime.now()
            }
        )
        todoRepositoryImpl.saveAll(todoList)

        val result = todoRepositoryImpl.findOne(3)
        println(result)

        Assertions.assertNotNull(result)
        Assertions.assertEquals("테스트일정33", result?.title)

    }

    @Test
    fun update(){
        val todo = Todo().apply {
            this.title = "테스트일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepositoryImpl.save(todo)
        println(todo)

        val newTodo = Todo().apply {
            this.index = insertTodo?.index
            this.title = "업데이트 일정" // 주석추가 이원호
            this.description = "업데이트 테스트"
            this.schedule = LocalDateTime.now()
        }
        val result = todoRepositoryImpl.save(newTodo)

        Assertions.assertEquals(insertTodo?.index, todo.index)
        println(todo)
    }

}
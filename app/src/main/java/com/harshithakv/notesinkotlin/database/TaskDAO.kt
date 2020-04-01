package com.harshithakv.notesinkotlin.database

import androidx.room.*
import com.harshithakv.notesinkotlin.models.Task
import com.harshithakv.notesinkotlin.models.TaskEntity
import com.harshithakv.notesinkotlin.models.Todo

@Dao
interface TaskDAO {
    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Insert
    fun addTodo(todo: Todo)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Update
    fun updateTodo(todo:Todo)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks(): MutableList<Task>
}
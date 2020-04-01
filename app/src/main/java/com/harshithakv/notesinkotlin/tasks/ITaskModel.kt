package com.harshithakv.notesinkotlin.tasks

import com.harshithakv.notesinkotlin.models.Task
import com.harshithakv.notesinkotlin.models.Todo

typealias SuccessCallback = (Boolean) -> Unit

interface ITaskModel {

    suspend fun addTask(task: Task, callback: SuccessCallback)
    suspend fun updateTask(task: Task, callback: SuccessCallback)
    suspend fun deleteTask(task: Task, callback: SuccessCallback)
     fun retrieveTasks(callback: (List<Task>?) -> Unit)
    suspend fun updateTodo(todo: Todo, callback: SuccessCallback)

}
package com.harshithakv.notesinkotlin.tasks

interface TaskListViewContract {
    fun onTodoUpdate(taskIndex: Int, todoIndex: Int, isComplete: Boolean)
    fun onTaskDeleted(taskIndex: Int)

}
package com.harshithakv.notesinkotlin.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harshithakv.notesinkotlin.foundations.ApplicationScope
import com.harshithakv.notesinkotlin.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class TaskViewModel : ViewModel(), TaskListViewContract {

    @Inject
    lateinit var model: ITaskModel

    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData: LiveData<MutableList<Task>> = _taskListLiveData


    init {
        Toothpick.inject(this, ApplicationScope.scope)                       // linking the scope to our class
        loadData()
    }


    fun loadData() {
        GlobalScope.launch {
            model.retrieveTasks { nullableList ->
                nullableList?.let {
                    _taskListLiveData.postValue(it.toMutableList())
                }
            }
        }
    }

    override fun onTodoUpdate(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        GlobalScope.launch {
            _taskListLiveData.value?.let {
                val todo = it[taskIndex].todos[todoIndex]
                todo.apply {
                    this.isComplete = isComplete
                    this.taskId = it[taskIndex].uid
                }
                model.updateTodo(todo) {
                    loadData()

                }
            }

        }

    }

    override fun onTaskDeleted(taskIndex: Int) {
        GlobalScope.launch {
            _taskListLiveData.value?.let {
                model.deleteTask(it[taskIndex]) {
                    loadData()
                }
            }
        }
    }

}
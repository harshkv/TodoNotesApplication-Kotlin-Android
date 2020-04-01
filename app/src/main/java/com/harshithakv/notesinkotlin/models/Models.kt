package com.harshithakv.notesinkotlin.models

import android.nfc.Tag
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*


class Task(
        title: String,
        @Relation(
                parentColumn = "uid",
                entityColumn = "taskId",
                entity = Todo::class
        )
        val todos: MutableList<Todo> = mutableListOf(),
        tag: Tag? = null
) : TaskEntity(
        title = title,
        tag = tag
) {

    init {
        todos.forEach {
            it.taskId = uid
        }
    }
}

@Entity(tableName = "tasks")
open class TaskEntity(
        @PrimaryKey
        var uid: Long = UUID.randomUUID().leastSignificantBits,
        @ColumnInfo
        var title: String,
        @Embedded
        var tag: Tag? = null
)

@Entity(tableName = "todos")
data class Todo(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,
        @ColumnInfo
        var description: String,
        @ColumnInfo
        var isComplete: Boolean = false,
        @ForeignKey(
                parentColumns = ["uid"],
                childColumns = ["taskId"],
                entity = TaskEntity::class,
                onDelete = CASCADE


        )
        var taskId: Long? = null
)

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,
        @ColumnInfo
        var description: String,
        @Embedded
        var tag: Tag? = null

)

@Entity(tableName = "tags")
data class Tag(
        @PrimaryKey
        val name: String,
        @ColumnInfo(name = "color_resource_id")
        val colorResId: Int
)





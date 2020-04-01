package com.harshithakv.notesinkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harshithakv.notesinkotlin.models.Note
import com.harshithakv.notesinkotlin.models.Tag
import com.harshithakv.notesinkotlin.models.TaskEntity
import com.harshithakv.notesinkotlin.models.Todo

const val DATABASE_SCHEMA_VERSION = 1
const val DB_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [TaskEntity::class, Note::class, Todo::class, Tag::class])
abstract class RoomDatabaseClient : RoomDatabase() {


    abstract fun noteDAO(): NoteDAO
    abstract fun taskDAO(): TaskDAO

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context): RoomDatabaseClient {
            if (instance == null) {
                instance = createDatabase(context)

            }
            return instance!!

        }

        private fun createDatabase(context: Context): RoomDatabaseClient {

            return Room.databaseBuilder(context, RoomDatabaseClient::class.java, DB_NAME)
                    .build()
        }


    }

}
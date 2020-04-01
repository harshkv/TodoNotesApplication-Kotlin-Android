package com.harshithakv.notesinkotlin.notes

import com.harshithakv.notesinkotlin.application.NoteApplication
import com.harshithakv.notesinkotlin.database.RoomDatabaseClient
import com.harshithakv.notesinkotlin.models.Note
import kotlinx.coroutines.*
import javax.inject.Inject

const val TIMEOUT_DURATION_MILLIS = 3000L

class NoteLocalModel @Inject constructor() : INoteModel {

    private var databaseClient = RoomDatabaseClient.getInstance(NoteApplication.instance.applicationContext)


    private suspend fun performOperationWithTimeout(function: () -> Unit, callback: SuccessCallback) {

        val job = GlobalScope.async {
            try {
                withTimeout(TIMEOUT_DURATION_MILLIS) {
                    function.invoke()
                }
            } catch (e: java.lang.Exception) {
                callback.invoke(false)
            }
        }
        job.await()
        callback.invoke(true)
    }


    override suspend fun addNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({ databaseClient.noteDAO().addNote(note) }, callback)
    }


    override suspend fun updateNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({
            databaseClient.noteDAO().updateNote(note)
        }, callback)
    }

    override suspend fun deleteNote(note: Note, callback: SuccessCallback) {
        performOperationWithTimeout({
            databaseClient.noteDAO().deleteNote(note)
        }, callback)
    }

    override suspend fun retrieveNotes(callback: (List<Note>?) -> Unit) {
            val job = GlobalScope.async {
                withTimeoutOrNull(TIMEOUT_DURATION_MILLIS) {
                    databaseClient.noteDAO().retrieveNotes()
                }
            }
            callback.invoke(job.await())
        }
    }

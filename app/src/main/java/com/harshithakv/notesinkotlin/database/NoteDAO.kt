package com.harshithakv.notesinkotlin.database

import androidx.room.*
import com.harshithakv.notesinkotlin.models.Note

@Dao
interface NoteDAO {

    @Insert
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)


    @Query("SELECT * FROM notes")
    fun retrieveNotes(): MutableList<Note>
}
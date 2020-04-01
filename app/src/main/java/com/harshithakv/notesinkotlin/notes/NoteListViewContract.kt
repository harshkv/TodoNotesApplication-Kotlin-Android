package com.harshithakv.notesinkotlin.notes

import com.harshithakv.notesinkotlin.models.Note

interface NoteListViewContract {

    fun onDeleteNote(note: Note)
}
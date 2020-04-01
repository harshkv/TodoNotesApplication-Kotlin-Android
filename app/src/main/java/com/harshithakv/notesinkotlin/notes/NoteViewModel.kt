package com.harshithakv.notesinkotlin.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harshithakv.notesinkotlin.foundations.ApplicationScope
import com.harshithakv.notesinkotlin.models.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class NoteViewModel : ViewModel(), NoteListViewContract {

    @Inject
    lateinit var model: INoteModel
    private val _noteListLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    var noteListLiveData: LiveData<List<Note>> = _noteListLiveData


    init {
        Toothpick.inject(this, ApplicationScope.scope)
        loadData()
    }

    fun loadData() {
        GlobalScope.launch {
            model.retrieveNotes { nullableList ->
                nullableList?.let {
                    _noteListLiveData.postValue(it)
                }
            }
        }
    }

    override fun onDeleteNote(note: Note) {
        GlobalScope.launch {
            model.deleteNote(note) {
                if (it) {
                    loadData()
                }
            }
        }
    }

}
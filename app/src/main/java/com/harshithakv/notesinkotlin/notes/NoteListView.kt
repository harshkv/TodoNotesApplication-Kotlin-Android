package com.harshithakv.notesinkotlin.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshithakv.notesinkotlin.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.view.*


class NoteListView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var adapter: NoteAdapter
    private lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: NoteListViewContract


    fun initView(taDelegate: NotesListFragment.TouchActionDelegate, daDelegate: NoteListViewContract) {
        setUpDelegates(taDelegate, daDelegate)
        setUpView()
    }

    private fun setUpDelegates(taDelegate: NotesListFragment.TouchActionDelegate, daDelegate: NoteListViewContract) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        noteRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(
                touchActionDelegate = touchActionDelegate, dataActionDelegate = dataActionDelegate)
        noteRecyclerView.adapter = adapter
    }


    fun updateList(list: List<Note>) {
        adapter.updateList(list)
    }
}



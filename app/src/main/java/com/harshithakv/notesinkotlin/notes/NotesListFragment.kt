package com.harshithakv.notesinkotlin.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harshithakv.notesinkotlin.R

class NotesListFragment : Fragment() {
    lateinit var viewModel: NoteViewModel
    lateinit var touchActionDelegate: NotesListFragment.TouchActionDelegate
    lateinit var contentView: NoteListView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context?.let {
            if (it is TouchActionDelegate) {
                touchActionDelegate = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false).apply {
            contentView = this as NoteListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setContentView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun setContentView() {
        contentView.initView(touchActionDelegate, viewModel)
    }


    private fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.noteListLiveData.observe(viewLifecycleOwner, Observer { noteList ->
            contentView.updateList(noteList)
        })

    }

    companion object {
        fun newInstance() = NotesListFragment()
    }

    interface TouchActionDelegate {
        fun onAddButtonClicked(value: String)
    }
}
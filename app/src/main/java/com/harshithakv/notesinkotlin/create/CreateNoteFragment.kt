package com.harshithakv.notesinkotlin.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.harshithakv.notesinkotlin.R
import com.harshithakv.notesinkotlin.foundations.ApplicationScope
import com.harshithakv.notesinkotlin.foundations.NullFieldChecker
import com.harshithakv.notesinkotlin.models.Note
import com.harshithakv.notesinkotlin.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class CreateNoteFragment : Fragment(), NullFieldChecker {

    @Inject
    lateinit var model: INoteModel
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + "must be immplemented OnFragmentInteractionListner")
        }
    }


    fun saveNote(Callback: (Boolean) -> Unit) {
        GlobalScope.launch {
            createNote()?.let { note ->
                model.addNote(note) { success ->
                    Callback.invoke(success)
                }
            } ?: Callback.invoke(false)

        }

    }

    private fun createNote(): Note? = if (!hasNullField()) {
        Note(description = noteEditText.editableText.toString())
    } else {
        null
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun OnFragmentInteraction()
    }


    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()

    companion object {
        fun newInstance() = CreateNoteFragment()
    }
}
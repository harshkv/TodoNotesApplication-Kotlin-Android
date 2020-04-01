package com.harshithakv.notesinkotlin.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.harshithakv.notesinkotlin.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_todo.view.*

class CreateTodoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr), NullFieldChecker {

    override fun hasNullField() = todoEditText.editableText.isNullOrEmpty()


}
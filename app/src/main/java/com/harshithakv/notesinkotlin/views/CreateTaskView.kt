package com.harshithakv.notesinkotlin.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.harshithakv.notesinkotlin.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_task.view.*


class CreateTaskView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1
) : LinearLayout(context, attrs, defStyleAttr), NullFieldChecker {

    override fun hasNullField() = taskEditText.editableText.isNullOrEmpty()


}
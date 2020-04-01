package com.harshithakv.notesinkotlin.views

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.harshithakv.notesinkotlin.models.Todo
import kotlinx.android.synthetic.main.view_todo.view.*

class TodoView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 1

) : ConstraintLayout(context, attrs, defStyleAttr) {

    fun initView(todo: Todo, callback: ((Boolean) -> Unit)? = null) {

        descriptionView.text = todo.description
        completeCheckBox.isChecked = todo.isComplete
        if (todo.isComplete) {
            descriptionView.setStrikeThrough()
        }
        setUpCheckStateListener(todo, callback)
    }

    fun setUpCheckStateListener(todo: Todo, callback: ((Boolean) -> Unit)? = null) {
        completeCheckBox.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            todo.isComplete = isChecked
            callback?.invoke(isChecked)
            if (isChecked) {
                descriptionView.setStrikeThrough()
            } else {
                descriptionView.removeStrikeThrough()
            }
        }
    }
}
package com.harshithakv.notesinkotlin.foundations

import com.harshithakv.notesinkotlin.notes.INoteModel
import com.harshithakv.notesinkotlin.notes.NoteLocalModel
import com.harshithakv.notesinkotlin.tasks.ITaskModel
import com.harshithakv.notesinkotlin.tasks.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {
    val scope = Toothpick.openScope(this).apply {
        // open the scope
        installModules(ApplicationModule)
    }
}

object ApplicationModule : Module() {
    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }

}
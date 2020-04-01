package com.harshithakv.notesinkotlin.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.harshithakv.notesinkotlin.R
import com.harshithakv.notesinkotlin.create.CreateActivity
import com.harshithakv.notesinkotlin.notes.NotesListFragment
import com.harshithakv.notesinkotlin.tasks.TasksListFragment
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity(), TasksListFragment.TouchActionDelegate, NotesListFragment.TouchActionDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_tasks, R.id.navigation_notes)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navigationView, navController)


    }


    private fun goToCreateActivity(fragmentValue: String) {
        startActivity(Intent(this, CreateActivity::class.java).apply {
            putExtra(FRAGMENT_TYPE_KEY, fragmentValue)
        })
    }

    override fun onAddButtonClicked(value: String) {
        goToCreateActivity(value)
    }

    companion object {
        const val FRAGMENT_TYPE_KEY = "f_t_k"
        const val FRAGMENT_VALUE_NOTE = "f_v_n"
        const val FRAGMENT_VALUE_TASK = "f_v_t"
    }
}
package com.landos.revisioncc1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ListViewTask : AppCompatActivity() {
    private var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)

        val tasksListView = findViewById<ListView>(R.id.listViewTasks)
        val tasksArray = intent.getStringArrayExtra("tasks")
        tasks = (tasksArray?.toList() ?: mutableListOf()).toMutableList()

        val adapter = AdapterListView(this)
        tasksListView.adapter = adapter

        tasks.forEach {
            val priority = it.substring(1, 2)
            val taskName = it.substring(4)
            adapter.addTask(taskName, priority)
        }
    }
}
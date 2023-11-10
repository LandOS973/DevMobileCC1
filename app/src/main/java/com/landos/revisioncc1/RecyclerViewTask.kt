package com.landos.revisioncc1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.landos.revisioncc1.R.*

class RecyclerViewTask : AppCompatActivity(){
    var tasks = mutableListOf<String>()
    var adapter = AdapterRecyclerView(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_task)

        // load tasks and add to recycler view
        val tasks_list = findViewById<RecyclerView>(id.recycler_view)
        tasks_list.adapter = adapter

        // load tasks from savedInstanceState
        if(savedInstanceState != null) {
            Toast.makeText(this, "Restauration des données", Toast.LENGTH_LONG).show()
            val saved_tasks = savedInstanceState.getParcelableArrayList<AdapterRecyclerView.Task>("tasks")
            tasks = saved_tasks as MutableList<String>
        }else{
            // load task from intent
            val tasksArray = intent.getStringArrayExtra("tasks")
            tasks = (tasksArray?.toList() ?: mutableListOf()).toMutableList()
        }

        // add tasks to adapter
        for (task in tasks) {
            val priority = task.substring(1, 2)
            val taskName = task.substring(4)
            adapter.addTask(taskName, priority)
        }

        val item_touch_helper_callback = object  : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            // bouge les doigt sur l'element
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false // pas de mouvement
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // supprime l'element
                val position = viewHolder.itemView.tag as Int
                adapter.removeTask(position)
                // remove from list
                tasks.removeAt(position)
            }
        }

        val item_touch_helper = ItemTouchHelper(item_touch_helper_callback)
        item_touch_helper.attachToRecyclerView(tasks_list)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("tasks", tasks as ArrayList<AdapterRecyclerView.Task>)
    }


}
package com.landos.revisioncc1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast


class AdapterListView(private val context : Context) : BaseAdapter() {
    class ViewHolder(val taskName: String = "", val priority: String = "")

    val tasks = mutableListOf<ViewHolder>()
    override fun getCount(): Int {
        return tasks.size
    }

    override fun getItem(position: Int): ViewHolder {
        return tasks[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View? {
        var myView: View? = convertView
        if (myView == null) {
            myView = LayoutInflater.from(context).inflate(R.layout.task, viewGroup, false)
        }

        val taskName = myView!!.findViewById<TextView>(R.id.taskNameList)
        val priority = myView.findViewById<View>(R.id.priority)

        taskName.text = getItem(position).taskName

        when (getItem(position).priority.toInt()) {
            1 -> priority.setBackgroundColor(Color.GREEN)
            2 -> priority.setBackgroundColor(Color.YELLOW)
            3 -> priority.setBackgroundColor(Color.RED)
        }

        // add a click listener to the task name
        taskName.setOnClickListener {
            Toast.makeText(context, "Task ${getItem(position).taskName}  Priorité : ${getItem(position).priority} ", Toast.LENGTH_SHORT).show()
        }

        return myView
    }

    fun addTask(taskName: String, priority: String) {
        tasks.add(ViewHolder(taskName, priority))
        notifyDataSetChanged()
    }

}
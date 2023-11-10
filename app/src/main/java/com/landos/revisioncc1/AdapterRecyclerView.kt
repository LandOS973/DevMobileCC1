package com.landos.revisioncc1

import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterRecyclerView(val context: Context) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {
    // Parcel permet de passer des objets entre les activités
    // serializable pour les sauvegarder
    class Task(val name: String?, val priority: String?) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(priority)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Task> {
            override fun createFromParcel(parcel: Parcel): Task {
                return Task(parcel)
            }

            override fun newArray(size: Int): Array<Task?> {
                return arrayOfNulls(size)
            }
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName = itemView.findViewById<TextView>(R.id.taskNameList)
        val priority = itemView.findViewById<View>(R.id.priority)
    }

    private val tasks = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = tasks[position].name
        when (tasks[position].priority?.toInt()) {
            1 -> holder.priority.setBackgroundColor(Color.GREEN)
            2 -> holder.priority.setBackgroundColor(Color.YELLOW)
            3 -> holder.priority.setBackgroundColor(Color.RED)
        }
        holder.taskName.setOnClickListener {
            Toast.makeText(context, "Task ${tasks[position].name} ; Priorité : ${tasks[position].priority} ", Toast.LENGTH_SHORT).show()
        }
        // code to remove task from recycler view
        // recupere la position de l'element pour la retrouver au swippe
        holder.itemView.tag = position
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun addTask(name: String, priority: String) {
        tasks.add(Task(name, priority))
        notifyDataSetChanged()
    }

    fun removeTask(adapterPosition: Int) {
        tasks.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }
}
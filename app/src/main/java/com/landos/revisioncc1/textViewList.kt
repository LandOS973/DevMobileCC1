package com.landos.revisioncc1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class TextViewList : AppCompatActivity() {
    private var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_view_list)

        val tasksArray = intent.getStringArrayExtra("tasks")
        tasks = (tasksArray?.toList() ?: mutableListOf()).toMutableList()

        val textViewList = findViewById<TextView>(R.id.textView)
        textViewList.text = tasks.joinToString("\n")
    }
}

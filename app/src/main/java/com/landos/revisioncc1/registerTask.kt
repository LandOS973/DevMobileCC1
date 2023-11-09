package com.landos.revisioncc1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class registerTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_task)
    }

    fun addTask(view : View) {
        val taskName = findViewById<EditText>(R.id.taskName).text.toString()
        val taskPriority = findViewById<RadioGroup>(R.id.priority)

        if(taskName.isNotEmpty() && taskPriority.checkedRadioButtonId != -1) {
            val radioButton = findViewById<RadioButton>(taskPriority.checkedRadioButtonId)
            val priority = radioButton.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("taskName", taskName)
            when(priority) {
                "Haute" -> intent.putExtra("priority", 1)
                "Moyenne" -> intent.putExtra("priority", 2)
                "Faible" -> intent.putExtra("priority", 3)
            }
            setResult(RESULT_OK, intent)
            finish()
        }else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }


    }
}
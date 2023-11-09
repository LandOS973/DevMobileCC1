package com.landos.revisioncc1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    // MENU
    val childActivityRes = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val taskName = data?.getStringExtra("taskName")
            val priority = data?.getIntExtra("priority", 0)
            Toast.makeText(this, "Task: $taskName, Priority: $priority", Toast.LENGTH_SHORT).show()
        }
    }
    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                childActivityRes.launch(Intent(this, registerTask::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
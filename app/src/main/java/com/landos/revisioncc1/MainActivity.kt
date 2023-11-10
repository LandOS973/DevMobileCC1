package com.landos.revisioncc1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    // create data mutable list
    var tasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // load tasks from FakeData
        tasks.addAll(FakeData.get_tasks())
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
            tasks.add("<$priority> $taskName")
        }
    }
    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                childActivityRes.launch(Intent(this, registerTask::class.java))
                true
            }
            R.id.textView -> {
                val intent = Intent(this, TextViewList::class.java)
                intent.putExtra("tasks", tasks.toTypedArray())
                startActivity(intent)
                true
            }
            R.id.listView -> {
                val intent = Intent(this, ListViewTask::class.java)
                intent.putExtra("tasks", tasks.toTypedArray())
                startActivity(intent)
                true
            }
            R.id.recyclerView -> {
                val intent = Intent(this, RecyclerViewTask::class.java)
                intent.putExtra("tasks", tasks.toTypedArray())
                startActivity(intent)
                true
            }
            R.id.cycleDeVie -> {
                val intent = Intent(this, CycleDeVie::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
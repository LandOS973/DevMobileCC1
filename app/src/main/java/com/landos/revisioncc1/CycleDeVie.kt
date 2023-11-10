package com.landos.revisioncc1

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// on create => on start => on resume => on pause => on stop => on destroy

// on create / on destroy
// on start / on stop
// on resume / on pause
class CycleDeVie : AppCompatActivity(){

    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cycle_de_vie)

        // load textView
        textView = findViewById<TextView>(R.id.tv_cycle_de_vie)

        textView.append("onCreate\n")
        // restore on bundle
        if (savedInstanceState != null) {
            textView.text = savedInstanceState.getString("textView")
        }
    }
    // exercice 1 et 2
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
        textView.append("onStart\n")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
        textView.append("onResume\n")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
        textView.append("onPause\n")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
        textView.append("onStop\n")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
        textView.append("onDestroy\n")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
        textView.append("onRestart\n")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MainActivity", "onSaveInstanceState")
        textView.append("onSaveInstanceState\n")

        // save on bundle => permet de récup les infos par la suite
        outState.putString("textView", textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val textView = findViewById<TextView>(R.id.tv_cycle_de_vie)
        Log.d("MainActivity", "onRestoreInstanceState")
        textView.append("onRestoreInstanceState\n")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d("MainActivity", "onAttachedToWindow")
        textView.append("onAttachedToWindow\n")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("MainActivity", "onDetachedFromWindow")
        textView.append("onDetachedFromWindow\n")
    }
}
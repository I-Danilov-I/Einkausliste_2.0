package com.example.einkausliste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLogAct!", "onCreate1")
        Log.d("MyLogAct!", "onCreate2")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct!", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct!", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogAct!", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogAct!", "onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogAct!", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MyLogAct!", "onRestart")
    }

    fun onClickStart(view: View) {
        Log.d("MyLogAct!", "Hinzugefügt")
    }
}

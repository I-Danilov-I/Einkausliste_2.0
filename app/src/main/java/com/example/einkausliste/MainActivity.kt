package com.example.einkausliste

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi


@UnstableApi
class MainActivity : AppCompatActivity() {
    lateinit var eingabeText: EditText
    lateinit var hinzufugenButton: Button
    lateinit var adapter: ArrayAdapter<String>
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        eintraege.clear()
        eintraege.addAll(loadEinkaufsliste(this))
        Log.d("MyLogAct", "onCreate")
    }

    fun hinzufugen(view: View) {
        hinzufugen(view, adapter, eingabeText, eintraege)
        saveEinkaufsliste(this, eintraege)
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct", "onResume")
    }

    override fun onPause() {
        saveEinkaufsliste(this, eintraege)
        super.onPause()
        Log.d("MyLogAct", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogAct", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogAct", "onDestroy")
    }
}

package com.example.einkausliste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var liste: ListView
    private lateinit var eingabeText: EditText // Deklaration des EditText-Felds


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLogAct!", "onCreate1")
        Log.d("MyLogAct!", "onCreate2")

        val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, eintraege)

        liste = findViewById(R.id.liste)
        liste.adapter = adapter
        liste.visibility = View.VISIBLE

        eingabeText = findViewById(R.id.eingabeText)
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

    fun hinzufugen(view: View) {
        Log.d("MyLogAct!", "Hinzufugen")
        val neuerEintrag = eingabeText.text.toString()
        if (neuerEintrag.isNotEmpty()) {
            val adapter = liste.adapter as ArrayAdapter<String>
            adapter.add(neuerEintrag)
            adapter.notifyDataSetChanged()
            eingabeText.text.clear()
        }
    }

}

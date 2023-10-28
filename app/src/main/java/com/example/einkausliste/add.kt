package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.util.Log

fun hinzufugen(view: View) {
    lateinit var eingabeText: EditText
    lateinit var adapter: ArrayAdapter<String>
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")

    val neuerEintrag = eingabeText.text.toString()

    if (neuerEintrag.isNotEmpty()) {
        adapter.add(neuerEintrag)
        adapter.notifyDataSetChanged()
        eingabeText.text.clear()
        Log.d("MyLogAct", "com.example.einkausliste.com.example.einkausliste.hinzufugen")
    }
}

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
    private lateinit var eingabeText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyLogAct!", "onCreate1")
        Log.d("MyLogAct!", "onCreate2")

        // Vorhandene Einträge in einer Liste
        val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")

        // Adapter für die ListView erstellen und mit den Einträgen verknüpfen
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, eintraege)

        // ListView im Layout finden und Adapter zuweisen
        liste = findViewById(R.id.liste)
        liste.adapter = adapter
        liste.visibility = View.VISIBLE

        // EditText-Feld im Layout finden
        eingabeText = findViewById(R.id.eingabeText)
    }

    // onStart, onResume, onPause usw. - Methoden bleiben unverändert

    // Funktion zum Hinzufügen eines neuen Eintrags
    fun hinzufugen(view: View) {
        Log.d("MyLogAct!", "Hinzufugen")

        // Das Eingabefeld sichtbar machen
        eingabeText.visibility = View.VISIBLE

        // Text aus dem EditText-Feld abrufen
        val neuerEintrag = eingabeText.text.toString()


        // Sicherstellen, dass der Text nicht leer ist
        if (neuerEintrag.isNotEmpty()) {
            // Den Eintrag dem Adapter hinzufügen und die ListView aktualisieren
            val adapter = liste.adapter as ArrayAdapter<String>
            adapter.add(neuerEintrag)
            adapter.notifyDataSetChanged()

            // Das EditText-Feld leeren
            eingabeText.text.clear()

        }
    }

}

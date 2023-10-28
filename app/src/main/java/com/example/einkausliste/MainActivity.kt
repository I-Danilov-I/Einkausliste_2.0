package com.example.einkausliste

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi

@UnstableApi
class MainActivity : AppCompatActivity() {
    lateinit var liste: ListView // Die ListView für die Einkaufsliste
    lateinit var eingabeText: EditText // Das EditText-Feld für die Eingabe
    lateinit var hinzufugenButton: Button // Die Schaltfläche zum Hinzufügen
    lateinit var adapter: ArrayAdapter<String> // Ein Adapter für die ListView
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl") // Eine Liste von Einträgen
    lateinit var textBlinker: TextBlinker // Ein TextBlinker-Objekt für die Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Die Benutzeroberfläche der Activity festlegen

        this.setupUI() // Die Benutzeroberfläche initialisieren
        loadEinkaufsliste() // Die Einkaufsliste aus den gespeicherten Daten laden
        Log.d("MyLogAct", "onCreate") // Log-Nachricht: Activity wurde erstellt
    }

    // Methode zum Speichern der Einkaufsliste
    private fun saveEinkaufsliste() {
        val eintraegeSet = HashSet(eintraege) // Die Einträge in ein Set umwandeln
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE) // SharedPreferences initialisieren
        val editor = sharedPreferences.edit() // Einen Editor für SharedPreferences erstellen
        editor.putStringSet("einkaufsliste", eintraegeSet) // Die Einkaufsliste in SharedPreferences speichern
        editor.apply() // Änderungen in SharedPreferences anwenden
        Log.d("MyLogAct", "saveEinkaufsliste") // Log-Nachricht: Einkaufsliste wurde gespeichert
    }

    // Methode zum Laden der Einkaufsliste
    private fun loadEinkaufsliste() {
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE) // SharedPreferences initialisieren
        val savedEintraegeSet = sharedPreferences.getStringSet("einkaufsliste", null) // Einkaufsliste aus SharedPreferences laden

        if (savedEintraegeSet != null) {
            eintraege.clear() // Die aktuelle Liste leeren
            eintraege.addAll(savedEintraegeSet) // Gespeicherte Einträge zur Liste hinzufügen
            adapter.notifyDataSetChanged() // Den Adapter aktualisieren, um die Änderungen in der ListView anzuzeigen
        }
        Log.d("MyLogAct", "loadEinkaufsliste") // Log-Nachricht: Einkaufsliste wurde geladen
    }

    // ...

    // Weitere Activity-Lebenszyklus-Methoden, hier nicht kommentiert

    // ...

    override fun onDestroy() {
        saveEinkaufsliste() // Die Einkaufsliste speichern, bevor die Activity zerstört wird
        textBlinker.stopBlinkAnimation() // Die TextBlinker-Animation stoppen
        super.onDestroy()
        Log.d("MyLogAct", "onDestroy") // Log-Nachricht: Activity wurde zerstört
    }
}

// Importieren von erforderlichen Klassen und Bibliotheken
package com.example.einkausliste

import android.os.Bundle // Import für die Bundle-Klasse, um Daten zwischen Aktivitäten zu übertragen
import android.view.View // Import für die View-Klasse, die für die Benutzeroberfläche verwendet wird
import android.widget.ArrayAdapter // Import für die ArrayAdapter-Klasse, um Daten an eine ListView zu binden
import android.widget.EditText // Import für die EditText-Klasse, die ein Texteingabefeld repräsentiert
import android.widget.Button // Import für die Button-Klasse, die eine Schaltflächen-UI repräsentiert
import androidx.appcompat.app.AppCompatActivity // Import für die AppCompatActivity-Klasse, die die Basisaktivitätsklasse für Android-Apps ist
import androidx.media3.common.util.Log // Import für die Log-Klasse zur Erstellung und Anzeige von Log-Nachrichten
import androidx.media3.common.util.UnstableApi // Import für die UnstableApi-Annotation zur Kennzeichnung von instabilen APIs

@UnstableApi
class MainActivity : AppCompatActivity() {
    lateinit var eingabeText: EditText // Das EditText-Feld zur Eingabe neuer Einträge
    lateinit var hinzufugenButton: Button // Der Button zum Hinzufügen neuer Einträge
    lateinit var adapter: ArrayAdapter<String> // Der Adapter für die ListView
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl") // Die Liste der Einkäufe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI() // Initialisierung der Benutzeroberfläche
        eintraege.clear() // Löscht die Standard-Einträge
        eintraege.addAll(loadEinkaufsliste(this)) // Fügt die geladenen Einträge hinzu
        Log.d("MyLogAct", "onCreate") // Log-Nachricht zur Anzeige in der Konsole
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct", "onStart") // Log-Nachricht zur Anzeige in der Konsole
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct", "onResume") // Log-Nachricht zur Anzeige in der Konsole
    }

    override fun onPause() {
        saveEinkaufsliste(this, eintraege) // Speichert die Einkaufsliste
        super.onPause()
        Log.d("MyLogAct", "onPause") // Log-Nachricht zur Anzeige in der Konsole
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogAct", "onStop") // Log-Nachricht zur Anzeige in der Konsole
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogAct", "onDestroy") // Log-Nachricht zur Anzeige in der Konsole
    }
}

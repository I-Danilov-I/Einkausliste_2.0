// Importanweisungen: Diese Bibliotheken und Klassen werden benötigt, um die Funktionen der App zu realisieren
package com.example.einkausliste

// Importieren der Android Bundle-Klasse
import android.os.Bundle

// Importieren der AppCompatActivity-Klasse aus dem androidx-Paket
import androidx.appcompat.app.AppCompatActivity

// Importieren der Log-Klasse, um Debug-Nachrichten zu generieren
import android.util.Log

// Importieren der View-Klasse
import android.view.View

// Importieren der ArrayAdapter-Klasse für die Verwaltung der ListView-Daten
import android.widget.ArrayAdapter

// Importieren der EditText-Klasse zur Verarbeitung von Texteingaben
import android.widget.EditText

// Importieren der ListView-Klasse für die Darstellung von Listenansichten
import android.widget.ListView


// Die MainActivity-Klasse erbt von AppCompatActivity, um eine Android-Anwendungsaktivität zu erstellen
class MainActivity : AppCompatActivity() {

    // Deklaration von Instanzvariablen für die ListView und das EditText-Feld
    private lateinit var liste: ListView
    private lateinit var eingabeText: EditText

    // Die Methode onCreate wird aufgerufen, wenn die Aktivität erstellt wird
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Das Layout der Aktivität festlegen
        setContentView(R.layout.activity_main)

        // Debug-Nachrichten, um den Lebenszyklus der Aktivität zu verfolgen
        Log.d("MyLogAct!", "onCreate1")
        Log.d("MyLogAct!", "onCreate2")

        // Vorhandene Einträge in einer Liste
        val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")

        // Adapter für die ListView erstellen und mit den Einträgen verknüpfen
        val adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)

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
        Log.d("MyLogAct!", "Hinzufügen")

        // Das Eingabefeld sichtbar machen
        eingabeText.visibility = View.VISIBLE

        // Text aus dem EditText-Feld abrufen
        val neuerEintrag = eingabeText.text.toString()

        // Sicherstellen, dass der Text nicht leer ist
        if (neuerEintrag.isNotEmpty()) {
            // Den Eintrag dem Adapter hinzufügen und die ListView aktualisieren
            val adapter = liste.adapter as? ArrayAdapter<String>

            if (adapter != null) {
                adapter.add(neuerEintrag)
                adapter.notifyDataSetChanged()
            } else {
                // Behandeln Sie den Fall, in dem der Adapter keine ArrayAdapter<String> ist.
                Log.e("MyLogAct!", "Ungültiger Adapter-Typ")
            }

            // Das EditText-Feld leeren
            eingabeText.text.clear()
        }
    }
}

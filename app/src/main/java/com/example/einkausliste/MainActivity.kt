// Import-Anweisungen: Diese Bibliotheken und Klassen werden benötigt
package com.example.einkausliste

// Das Android-Paket für die Arbeit mit Farben
import android.graphics.Color

// Das Android-Paket für die Verwaltung von App-Zuständen und Daten
import android.os.Bundle

// Das Android-Paket für die Arbeit mit Threads und Aufgabenplanung
import android.os.Handler

// Das Android-Paket für Benutzeroberflächenelemente
import android.view.View

// Die ArrayAdapter-Klasse wird für die Verwaltung der Daten in einer ListView verwendet
import android.widget.ArrayAdapter

// Die EditText-Klasse ist ein Eingabefeld zur Erfassung von Benutzereingaben
import android.widget.EditText

// Die ListView-Klasse wird für die Darstellung von Listenansichten in der Benutzeroberfläche verwendet
import android.widget.ListView

// Die Button-Klasse wird für die Verwaltung von Schaltflächen in der Benutzeroberfläche verwendet
import android.widget.Button

// Die AppCompatActivity-Klasse ist eine Basisklasse für Android-Anwendungsaktivitäten und stellt die Hauptkomponente der App dar
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi

// Importieren Sie die EditTextBlinker-Klasse
import com.example.einkausliste.TextBlinker

// Die MainActivity-Klasse erbt von AppCompatActivity, um eine Android-Anwendungsaktivität zu erstellen
@UnstableApi
class MainActivity : AppCompatActivity() {
    // Deklaration von Instanzvariablen für die ListView, das EditText-Feld und den "Hinzufügen"-Button
    private lateinit var liste: ListView
    private lateinit var eingabeText: EditText
    private lateinit var hinzufugenButton: Button

    // Neue Instanzvariable für den Adapter
    private lateinit var adapter: ArrayAdapter<String>
    // Deklaration einer Instanzvariable für die Einkaufsliste als MutableSet
    private var eintraegeSet: MutableSet<String> = HashSet()

    // Instanzvariable für die TextBlinker-Klasse
    private lateinit var textBlinker: TextBlinker

    // Die onCreate-Methode wird aufgerufen, wenn die Aktivität erstellt wird
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vorhandene Einträge in einer Liste
        val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")

        // Adapter für die ListView erstellen und mit den Einträgen verknüpfen
        adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)

        // Die ListView im Layout finden und den Adapter zuweisen
        liste = findViewById(R.id.liste)
        liste.adapter = adapter
        liste.visibility = View.VISIBLE

        // Das EditText-Feld im Layout finden
        eingabeText = findViewById(R.id.eingabeText)
        // Den "Hinzufügen"-Button im Layout finden
        hinzufugenButton = findViewById(R.id.hinzufugen)

        // Starten Sie die Blinkanimation des EditText-Hintergrunds beim Starten der Aktivität
        textBlinker = TextBlinker(eingabeText)
        textBlinker.startBlinkAnimation()

        loadEinkaufsliste()
        Log.d("MyLogAct", "onCreate")
    }

    // Die Methode zum Hinzufügen eines neuen Eintrags
    fun hinzufugen(view: View) {
        val neuerEintrag = eingabeText.text.toString()

        if (neuerEintrag.isNotEmpty()) {
            val adapter = liste.adapter as? ArrayAdapter<String>

            if (adapter != null) {
                adapter.add(neuerEintrag)
                adapter.notifyDataSetChanged()

                // Füge den neuen Eintrag auch zum eintraegeSet hinzu
                eintraegeSet.add(neuerEintrag)

                // Das EditText-Feld leeren
                eingabeText.text.clear()
                saveEinkaufsliste()
                Log.d("MyLogAct", "hinzufugen")
            }
        }
    }

    // Speichere die Einkaufsliste in den SharedPreferences
    private fun saveEinkaufsliste() {
        val eintraegeList = eintraegeSet.toMutableList()
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("einkaufsliste", eintraegeList.toSet())
        editor.apply()
        Log.d("MyLogAct", "saveEinkausliste")
    }

    // Lade die Einkaufsliste aus den SharedPreferences und kombiniere sie mit den Standard-Einträgen
    private fun loadEinkaufsliste() {
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)
        val savedEintraegeSet = sharedPreferences.getStringSet("einkaufsliste", emptySet())

        if (savedEintraegeSet != null) {
            eintraegeSet.clear()  // Lösche alle Einträge aus der aktuellen eintraegeSet
            eintraegeSet.addAll(savedEintraegeSet)  // Füge die gespeicherten Einträge hinzu
        }

        adapter.clear()
        adapter.addAll(eintraegeSet)
        adapter.notifyDataSetChanged()
        Log.d("MyLogAct", "loadEinkaufsliste")
    }

    // Die onStart-Methode wird aufgerufen, wenn die Aktivität sichtbar wird
    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct", "onStart")
    }

    // Die onResume-Methode wird aufgerufen, wenn die Aktivität in den Vordergrund tritt
    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct", "onResume")
    }

    // Die onPause-Methode wird aufgerufen, wenn die Aktivität pausiert wird
    override fun onPause() {
        saveEinkaufsliste()
        super.onPause()
        Log.d("MyLogAct", "onPause")
    }

    // Die onStop-Methode wird aufgerufen, wenn die Aktivität nicht mehr sichtbar ist
    override fun onStop() {
        saveEinkaufsliste()
        super.onStop()
        Log.d("MyLogAct", "onStop")
    }

    // Die onDestroy-Methode wird aufgerufen, wenn die Aktivität zerstört wird
    override fun onDestroy() {
        // Speichern der Einträge
        saveEinkaufsliste()
        textBlinker.stopBlinkAnimation()
        super.onDestroy()
        Log.d("MyLogAct", "onDestroy")
    }
}


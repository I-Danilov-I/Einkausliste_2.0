package com.example.einkausliste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var liste: ListView // Deklaration einer ListView-Variablen

    // Diese Methode wird beim Start der Aktivität aufgerufen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Das Layout für diese Aktivität festlegen
        Log.d("MyLogAct!", "onCreate1") // Eine Log-Nachricht ausgeben
        Log.d("MyLogAct!", "onCreate2") // Eine weitere Log-Nachricht ausgeben

        // Hier erstellen Sie die Liste mit Einträgen
        val eintraege = listOf("Wasser", "Kartoffel", "Sonnenblumenöl")

        // Hier erstellen Sie einen Adapter, um die Liste mit der ListView zu verknüpfen
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eintraege)

        liste = findViewById(R.id.liste) // Die ListView initialisieren
        liste.adapter = adapter // Den Adapter mit der ListView verknüpfen

        // Die Liste anzeigen, indem Sie sie sichtbar machen
        liste.visibility = View.VISIBLE
    }

    // Diese Methode wird aufgerufen, wenn die Aktivität gestartet wird
    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct!", "onStart") // Log-Nachricht für den Start der Aktivität ausgeben
    }

    // Diese Methode wird aufgerufen, wenn die Aktivität wieder in den Vordergrund kommt
    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct!", "onResume") // Log-Nachricht für das Wiederaufnehmen der Aktivität ausgeben
    }

    // Diese Methode wird aufgerufen, wenn die Aktivität pausiert wird
    override fun onPause() {
        super.onPause()
        Log.d("MyLogAct!", "onPause") // Log-Nachricht für das Pausieren der Aktivität ausgeben
    }

    // Diese Methode wird aufgerufen, wenn die Aktivität zerstört wird
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyLogAct!", "onDestroy") // Log-Nachricht für die Zerstörung der Aktivität ausgeben
    }

    // Diese Methode wird aufgerufen, wenn die Aktivität gestoppt wird
    override fun onStop() {
        super.onStop()
        Log.d("MyLogAct!", "onStop") // Log-Nachricht für das Stoppen der Aktivität ausgeben
    }

    // Diese Methode wird aufgerufen, wenn die Aktivität neu gestartet wird
    override fun onRestart() {
        super.onRestart()
        Log.d("MyLogAct!", "onRestart") // Log-Nachricht für das Wiederherstellen der Aktivität ausgeben
    }

    // Diese Methode wird aufgerufen, wenn der Button geklickt wird
    fun onClickStart(view: View) {
        // Der 'view'-Parameter wird später im Programmverlauf verwendet
        Log.d("MyLogAct!", "Hinzugefügt") // Log-Nachricht für den Klick auf den Button ausgeben
    }
}

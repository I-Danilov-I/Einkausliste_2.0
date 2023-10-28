package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.media3.common.util.Log
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi

@OptIn(UnstableApi::class)
fun MainActivity.setupUI() {
    // Initialisierung des Adapters für die ListView
    adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)

    // Initialisierung der ListView und Zuweisung des Adapters
    val liste: ListView = findViewById(R.id.liste) // Die ListView zur Anzeige der Einkaufsliste
    liste.adapter = adapter

    // Sichtbarkeit der ListView setzen
    liste.visibility = View.VISIBLE

    // Initialisierung des EingabeText-EditText-Felds
    eingabeText = findViewById(R.id.eingabeText)

    // Initialisierung des Hinzufügen-Buttons
    hinzufugenButton = findViewById(R.id.hinzufugen)

    // Initialisierung des TextBlinker-Objekts und Starten der Blinkanimation
    val textBlinker: TextBlinker =
        TextBlinker(eingabeText) // Ein TextBlinker-Objekt zur Animation des EditText-Felds
    textBlinker.startBlinkAnimation()

    // Log-Nachricht zur Anzeige in der Konsole
    Log.d("MyLogAct", "setupUI")
}

// Die Funktion setupUI initialisiert die Benutzeroberfläche der App. Sie erstellt
// einen Adapter für die ListView, weist die ListView dem Adapter zu, setzt die Sichtbarkeit
// und initialisiert UI-Elemente wie EditText und Button. Zusätzlich wird ein TextBlinker
// für das EditText initialisiert und die Blinkanimation gestartet.


package com.example.einkausliste

import android.view.View // Importieren der View-Klasse aus dem Android-Framework, die für die Benutzeroberfläche verwendet wird
import android.widget.ArrayAdapter // Importieren der ArrayAdapter-Klasse aus dem Android-Framework, um Daten an eine ListView zu binden
import android.widget.ListView // Importieren der ListView-Klasse aus dem Android-Framework, um eine Liste anzuzeigen
import androidx.media3.common.util.Log // Importieren der Log-Klasse aus dem Android-Framework, um Log-Nachrichten zu erstellen und anzuzeigen
import androidx.annotation.OptIn // Importieren der OptIn-Annotation aus dem Android-Framework
import androidx.appcompat.app.AppCompatActivity // Importieren der AppCompatActivity-Klasse aus dem Android-Framework, um eine Aktivität zu erstellen
import androidx.media3.common.util.UnstableApi // Importieren der UnstableApi-Klasse aus dem Android-Framework für die Nutzung von nicht stabilen APIs


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
    val textBlinker =
        TextBlinker(eingabeText) // Ein TextBlinker-Objekt zur Animation des EditText-Felds
    textBlinker.startBlinkAnimation()

    // Log-Nachricht zur Anzeige in der Konsole
    Log.d("MyLogAct", "setupUI")
}

// Die Funktion setupUI initialisiert die Benutzeroberfläche der App. Sie erstellt
// einen Adapter für die ListView, weist die ListView dem Adapter zu, setzt die Sichtbarkeit
// und initialisiert UI-Elemente wie EditText und Button. Zusätzlich wird ein TextBlinker
// für das EditText initialisiert und die Blinkanimation gestartet.


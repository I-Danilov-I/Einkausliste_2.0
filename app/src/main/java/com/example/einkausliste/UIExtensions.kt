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

    // Festlegen eines Langklick-Listeners für die ListView
    liste.setOnItemLongClickListener { _, _, position, _ ->
        val item = adapter.getItem(position)
        if (item != null) {
            eintraege.remove(item)
            adapter.notifyDataSetChanged()
            Log.d("MyLogAct", "Eintrag gelöscht: $item")
        }
        true
    }

    // Sichtbarkeit der ListView setzen
    liste.visibility = View.VISIBLE

    // Initialisierung des EingabeText-EditText-Felds
    eingabeText = findViewById(R.id.eingabeText)

    // Initialisierung des Hinzufügen-Buttons und Festlegen eines Klick-Listeners
    hinzufugenButton = findViewById(R.id.hinzufugen)
    hinzufugenButton.setOnClickListener {
        val neuerEintrag = eingabeText.text.toString()
        if (neuerEintrag.isNotEmpty()) {
            eintraege.add(neuerEintrag)
            adapter.notifyDataSetChanged()
            eingabeText.text.clear()
            Log.d("MyLogAct", "Eintrag hinzugefügt: $neuerEintrag")
        }
    }

    // Initialisierung des TextBlinker-Objekts und Starten der Blinkanimation
    val textBlinker = TextBlinker(eingabeText) // Ein TextBlinker-Objekt zur Animation des EditText-Felds
    textBlinker.startBlinkAnimation()

    Log.d("MyLogAct", "setupUI")
}

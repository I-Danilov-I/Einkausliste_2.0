// add.kt-Datei
package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.util.Log

fun hinzufugen(view: View, adapter: ArrayAdapter<String>, eingabeText: EditText, eintraege: MutableList<String>) {
    // Extrahieren des eingegebenen Texts aus dem EditText-Feld
    val neuerEintrag = eingabeText.text.toString()

    // Überprüfung, ob der eingegebene Text nicht leer ist
    if (neuerEintrag.isNotEmpty()) {
        // Hinzufügen des neuen Eintrags zur Liste
        adapter.add(neuerEintrag)
        // Benachrichtigung des Adapters über die Änderung der Daten
        adapter.notifyDataSetChanged()
        // Löschen des Texts im EditText-Feld
        eingabeText.text.clear()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "Eintrag hinzugefügt: $neuerEintrag")
    }
}

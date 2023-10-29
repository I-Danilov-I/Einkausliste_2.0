package com.example.einkausliste

import android.widget.ArrayAdapter // Importieren der ArrayAdapter-Klasse aus dem Android-Framework, um Daten an eine ListView zu binden
import android.widget.EditText // Importieren der EditText-Klasse aus dem Android-Framework, die ein Texteingabefeld repräsentiert
import android.widget.ListView // Importieren der ListView-Klasse aus dem Android-Framework, um eine Liste anzuzeigen
import android.widget.AdapterView // Importieren der AdapterView-Klasse aus dem Android-Framework
import android.util.Log // Importieren der Log-Klasse aus dem Android-Framework, um Log-Nachrichten zu erstellen und anzuzeigen

// Funktion zum Hinzufügen eines Eintrags zur ListView und Aktualisieren der Daten
fun hinzufugen(adapter: ArrayAdapter<String>, eingabeText: EditText) {
    // Extrahieren des eingegebenen Texts aus dem EditText-Feld
    val neuerEintrag = eingabeText.text.toString()

    // Überprüfung, ob der eingegebene Text nicht leer ist
    if (neuerEintrag.isNotEmpty()) {
        // Hinzufügen des neuen Eintrags zum Adapter
        adapter.add(neuerEintrag)
        // Benachrichtigung des Adapters über die Änderung der Daten
        adapter.notifyDataSetChanged()
        // Löschen des Texts im EditText-Feld
        eingabeText.text.clear()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "Eintrag hinzugefügt: $neuerEintrag")
    }
}

// Funktion zum Löschen eines ausgewählten Eintrags aus der ListView und Aktualisieren der Daten
fun loeschen(adapter: ArrayAdapter<String>, listView: ListView, eintraege: MutableList<String>) {
    // Überprüfung, ob ein Eintrag in der ListView ausgewählt ist
    val position = listView.checkedItemPosition
    if (position != AdapterView.INVALID_POSITION) {
        // Extrahieren des ausgewählten Eintrags aus dem Adapter
        val geloeschterEintrag = adapter.getItem(position)
        // Entfernen des ausgewählten Eintrags aus der Liste
        eintraege.remove(geloeschterEintrag)
        // Benachrichtigung des Adapters über die Änderung der Daten
        adapter.notifyDataSetChanged()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "Eintrag gelöscht: $geloeschterEintrag")
    }
}

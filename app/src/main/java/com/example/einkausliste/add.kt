// add.kt-Datei
package com.example.einkausliste

// Importieren der Klasse "View" aus dem Android-Framework, die für die Benutzeroberfläche verwendet wird
import android.view.View

// Importieren der Klasse "ArrayAdapter" aus dem Android-Framework, um Daten an eine ListView zu binden
import android.widget.ArrayAdapter

// Importieren der Klasse "EditText" aus dem Android-Framework, die ein Texteingabefeld repräsentiert
import android.widget.EditText

// Importieren der Klasse "Log" aus dem Android-Framework, um Log-Nachrichten zu erstellen und anzuzeigen
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

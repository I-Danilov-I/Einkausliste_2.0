package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.util.Log

fun hinzufugen(view: View) {
    // Deklaration der lokalen Variablen
    lateinit var eingabeText: EditText // EditText-Feld für die Eingabe
    lateinit var adapter: ArrayAdapter<String> // Adapter für die ListView
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl") // Liste der Einträge

    // Abrufen des eingegebenen Texts aus dem EditText-Feld
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
        Log.d("MyLogAct", "com.example.einkausliste.com.example.einkausliste.hinzufugen")
    }
}

// Diese Funktion wird aufgerufen, wenn der "Hinzufügen"-Button in der Benutzeroberfläche
// geklickt wird. Sie liest den eingegebenen Text aus dem EditText-Feld, fügt ihn zur Liste
// hinzu, aktualisiert die ListView und zeigt eine Log-Nachricht an, wenn ein Eintrag hinzugefügt wird.

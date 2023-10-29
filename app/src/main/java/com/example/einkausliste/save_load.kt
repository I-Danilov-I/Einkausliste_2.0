package com.example.einkausliste

import android.content.Context // Import für die Context-Klasse, um auf App-spezifische Ressourcen zuzugreifen
import android.util.Log // Import für die Log-Klasse zur Erstellung und Anzeige von Log-Nachrichten

// Funktion zum Speichern der Einkaufsliste in den SharedPreferences
fun saveEinkaufsliste(context: Context, eintraege: List<String>) {
    // Erzeugen eines Sets aus der Liste der Einkäufe
    val eintraegeSet = HashSet(eintraege)

    // Zugriff auf die SharedPreferences der App mit dem Namen "Einkaufsliste"
    val sharedPreferences = context.getSharedPreferences("Einkaufsliste", Context.MODE_PRIVATE)

    // Erzeugen eines Editors, um Daten in die SharedPreferences zu schreiben
    val editor = sharedPreferences.edit()

    // Speichern des Sets als StringSet mit dem Schlüssel "einkaufsliste"
    editor.putStringSet("einkaufsliste", eintraegeSet)

    // Anwenden der Änderungen und Speichern der Daten
    editor.apply()

    // Log-Nachricht zur Anzeige in der Konsole
    Log.d("MyLogAct", "saveEinkaufsliste")
}

// Funktion zum Laden der Einkaufsliste aus den SharedPreferences
fun loadEinkaufsliste(context: Context): List<String> {
    // Zugriff auf die SharedPreferences der App mit dem Namen "Einkaufsliste"
    val sharedPreferences = context.getSharedPreferences("Einkaufsliste", Context.MODE_PRIVATE)

    // Laden des StringSets mit dem Schlüssel "einkaufsliste"
    val savedEintraegeSet = sharedPreferences.getStringSet("einkaufsliste", null)

    // Log-Nachricht zur Anzeige in der Konsole
    Log.d("MyLogAct", "loadEinkaufsliste")

    // Konvertieren des geladenen StringSets in eine Liste, oder eine leere Liste, wenn null
    return savedEintraegeSet?.toList() ?: emptyList()
}

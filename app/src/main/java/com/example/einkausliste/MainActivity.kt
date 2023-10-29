package com.example.einkausliste
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.einkausliste.TextBlinker

@UnstableApi
class MainActivity : AppCompatActivity() {
    lateinit var eingabeText: EditText // Das EditText-Feld zur Eingabe neuer Einträge
    lateinit var hinzufugenButton: Button // Der Button zum Hinzufügen neuer Einträge
    lateinit var adapter: ArrayAdapter<String> // Der Adapter für die ListView
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl") // Die Liste der Einkäufe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisierung der Benutzeroberfläche
        setupUI()

        // Laden der Einkaufsliste aus den SharedPreferences
        loadEinkaufsliste()

        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onCreate")
    }

    // Diese Funktion wird aufgerufen, wenn die Aktivität erstellt wird.
    fun hinzufugen(view: View) {
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
            // Speichern der aktualisierten Einkaufsliste in den SharedPreferences
            saveEinkaufsliste()
            // Log-Nachricht zur Anzeige in der Konsole
            Log.d("MyLogAct", "Eintrag hinzugefügt: $neuerEintrag")
        }
    }

    // Diese Funktion wird aufgerufen, wenn der "Hinzufügen"-Button geklickt wird.

    private fun saveEinkaufsliste() {
        // Konvertieren der Liste der Einkäufe in ein Set
        val eintraegeSet = HashSet(eintraege)

        // Zugriff auf die SharedPreferences für die Einkaufsliste
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)

        // Erstellen eines Editors für die SharedPreferences
        val editor = sharedPreferences.edit()

        // Speichern des Sets in den SharedPreferences
        editor.putStringSet("einkaufsliste", eintraegeSet)
        editor.apply() // Anwenden der Änderungen

        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "Einkaufsliste gespeichert")
    }

    // Diese Funktion speichert die Einkaufsliste in den SharedPreferences.

    private fun loadEinkaufsliste() {
        // Zugriff auf die SharedPreferences für die Einkaufsliste
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)

        // Laden des Sets aus den SharedPreferences
        val savedEintraegeSet = sharedPreferences.getStringSet("einkaufsliste", null)

        if (savedEintraegeSet != null) {
            // Überprüfung, ob gespeicherte Einträge vorhanden sind
            eintraege.clear()
            eintraege.addAll(savedEintraegeSet)
            // Aktualisieren der ListView durch Benachrichtigung des Adapters
            adapter.notifyDataSetChanged()
        }

        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "Einkaufsliste geladen")
    }


    override fun onStart() {
        super.onStart()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onStart")
    }

    override fun onResume() {
        super.onResume()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onResume")
    }

    override fun onPause() {
        super.onPause()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onPause")
    }

    override fun onStop() {
        super.onStop()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onStop")
    }

    override fun onDestroy() {
        // Speichern der Einkaufsliste und Stoppen der Textanimation
        saveEinkaufsliste()
        super.onDestroy()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onDestroy")
    }
}

// Diese Funktionen werden in verschiedenen Lebenszyklusphasen der Aktivität aufgerufen.

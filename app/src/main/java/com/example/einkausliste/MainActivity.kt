
// Importieren von erforderlichen Klassen und Bibliotheken
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
    // Deklaration der Variablen für Benutzeroberflächenelemente
    lateinit var eingabeText: EditText // Das EditText-Feld zur Eingabe neuer Einträge
    lateinit var hinzufugenButton: Button // Der Button zum Hinzufügen neuer Einträge
    lateinit var adapter: ArrayAdapter<String> // Der Adapter für die ListView
    val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl") // Die Liste der Einkäufe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setzen des Layouts für die Aktivität aus der XML-Datei
        setContentView(R.layout.activity_main)

        // Initialisierung der Benutzeroberfläche
        setupUI()

        // Laden der Einkaufsliste aus den SharedPreferences

        loadEinkaufsliste()

        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onCreate")
    }

    // Handler-Funktion für den "Hinzufügen"-Button
    fun hinzufugen(view: View) {
        // Rufen Sie die ausgelagerte Funktion auf und übergeben Sie die erforderlichen Parameter.
        hinzufugen(view, adapter, eingabeText, eintraege)
    }

    // Diese Funktion wird aufgerufen, um die Einkaufsliste in den SharedPreferences zu speichern.
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

    // Diese Funktion wird aufgerufen, um die Einkaufsliste aus den SharedPreferences zu laden.
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

    // Diese Funktion wird aufgerufen, wenn die Aktivität gestartet wird.
    override fun onStart() {
        super.onStart()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onStart")
    }

    // Diese Funktion wird aufgerufen, wenn die Aktivität fortgesetzt wird.
    override fun onResume() {
        super.onResume()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onResume")
    }

    // Diese Funktion wird aufgerufen, wenn die Aktivität pausiert wird.
    override fun onPause() {
        saveEinkaufsliste()
        super.onPause()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onPause")
    }

    // Diese Funktion wird aufgerufen, wenn die Aktivität gestoppt wird.
    override fun onStop() {
        saveEinkaufsliste()
        super.onStop()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onStop")
    }

    // Diese Funktion wird aufgerufen, wenn die Aktivität zerstört wird.
    override fun onDestroy() {
        // Speichern der Einkaufsliste und Stoppen der Textanimation
        saveEinkaufsliste()
        super.onDestroy()
        // Log-Nachricht zur Anzeige in der Konsole
        Log.d("MyLogAct", "onDestroy")
    }
}

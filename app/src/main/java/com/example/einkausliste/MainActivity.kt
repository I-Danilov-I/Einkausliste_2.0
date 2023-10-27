// Import-Anweisungen: Diese Bibliotheken und Klassen werden benötigt
package com.example.einkausliste

// Das Android-Paket für die Arbeit mit Farben
import android.graphics.Color

// Das Android-Paket für die Verwaltung von App-Zuständen und Daten
import android.os.Bundle

// Das Android-Paket für die Arbeit mit Threads und Aufgabenplanung
import android.os.Handler

// Das Android-Paket für Benutzeroberflächenelemente
import android.view.View

// Das Android-Paket für Animationen in der Benutzeroberfläche
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

// Die ArrayAdapter-Klasse wird für die Verwaltung der Daten in einer ListView verwendet
import android.widget.ArrayAdapter

// Die EditText-Klasse ist ein Eingabefeld zur Erfassung von Benutzereingaben
import android.widget.EditText

// Die ListView-Klasse wird für die Darstellung von Listenansichten in der Benutzeroberfläche verwendet
import android.widget.ListView

// Die Button-Klasse wird für die Verwaltung von Schaltflächen in der Benutzeroberfläche verwendet
import android.widget.Button

// Die AppCompatActivity-Klasse ist eine Basisklasse für Android-Anwendungsaktivitäten und stellt die Hauptkomponente der App dar
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi

@UnstableApi // Die MainActivity-Klasse erbt von AppCompatActivity, um eine Android-Anwendungsaktivität zu erstellen
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    // Deklaration von Instanzvariablen für die ListView, das EditText-Feld und den "Hinzufügen"-Button
    private lateinit var liste: ListView
    private lateinit var eingabeText: EditText
    private lateinit var hinzufugenButton: Button
    private var isEditTextBlinking = false
    private lateinit var adapter: ArrayAdapter<String> // Neue Instanzvariable für den Adapter

    // Deklaration einer Instanzvariable für die Einkaufsliste als MutableSet
    private var eintraegeSet: MutableSet<String> = HashSet()




    // Die onCreate-Methode wird aufgerufen, wenn die Aktivität erstellt wird
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vorhandene Einträge in einer Liste
        val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")


        // Adapter für die ListView erstellen und mit den Einträgen verknüpfen
        adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)

        // Die ListView im Layout finden und den Adapter zuweisen
        liste = findViewById(R.id.liste)
        liste.adapter = adapter
        liste.visibility = View.VISIBLE

        // Das EditText-Feld im Layout finden
        eingabeText = findViewById(R.id.eingabeText)
        // Den "Hinzufügen"-Button im Layout finden
        hinzufugenButton = findViewById(R.id.hinzufugen)

        // Starten Sie die Blinkanimation des EditText-Hintergrunds beim Starten der Aktivität
        startEditTextBlinkAnimation()
        loadEinkaufsliste()
        Log.d("MyLogAct", "onCreate")

    }



    //---------------------------------------------------------------------------------------------
    // Starten Sie die Blinkanimation des EditText-Hintergrunds
    private fun startEditTextBlinkAnimation() {
        if (!isEditTextBlinking) {
            // Erstellen Sie eine Alpha-Blinkanimation
            val blinkAnimation = AlphaAnimation(0.1f, 1.0f)
            blinkAnimation.duration = 2000 // Dauer der Animation in Millisekunden
            blinkAnimation.startOffset = 20 // Startverzögerung der Animation
            blinkAnimation.repeatCount = Animation.INFINITE // Unendlich oft wiederholen

            // Starten Sie die Animation auf dem EditText
            eingabeText.startAnimation(blinkAnimation)

            // Ändern Sie die Hintergrundfarbe des EditText während des Blinkens
            val handler = Handler()
            val colors = intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT) // Hier können Sie die Farben anpassen
            var colorIndex = 0

            handler.post(object : Runnable {
                override fun run() {
                    if (isEditTextBlinking) {
                        eingabeText.setBackgroundColor(colors[colorIndex])
                        colorIndex = (colorIndex + 1) % colors.size
                        handler.postDelayed(this, 500) // Ändern Sie die Geschwindigkeit nach Bedarf
                    } else {
                        eingabeText.setBackgroundColor(Color.TRANSPARENT)
                    }
                }
            })

            isEditTextBlinking = true
        }
    }


    //-------------------------------------------------------------------------------------------------
    // Die Methode zum Hinzufügen eines neuen Eintrags
    fun hinzufugen(view: View) {
        val neuerEintrag = eingabeText.text.toString()

        if (neuerEintrag.isNotEmpty()) {
            val adapter = liste.adapter as? ArrayAdapter<String>

            if (adapter != null) {
                adapter.add(neuerEintrag)
                adapter.notifyDataSetChanged()

                // Füge den neuen Eintrag auch zum eintraegeSet hinzu
                eintraegeSet.add(neuerEintrag)

                // Das EditText-Feld leeren
                eingabeText.text.clear()
                saveEinkaufsliste()
                Log.d("MyLogAct", "hinzufugen")
            }
        }
    }


//_____Speichere die Einkaufsliste in den SharedPreferences________________________________________
    private fun saveEinkaufsliste() {
        val eintraegeList = eintraegeSet.toMutableList()
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("einkaufsliste", eintraegeList.toSet())
        editor.apply()
        Log.d("MyLogAct", "saveEinkausliste")
    }


// Lade die Einkaufsliste aus den SharedPreferences und kombiniere sie mit den Standard-Einträgen
    private fun loadEinkaufsliste() {
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)
        val savedEintraegeSet = sharedPreferences.getStringSet("einkaufsliste", emptySet())

        if (savedEintraegeSet != null) {
            eintraegeSet.clear()  // Lösche alle Einträge aus der aktuellen eintraegeSet
            eintraegeSet.addAll(savedEintraegeSet)  // Füge die gespeicherten Einträge hinzu
        }

        adapter.clear()
        adapter.addAll(eintraegeSet)
        adapter.notifyDataSetChanged()
        Log.d("MyLogAct", "loadEinkaufsliste")
    }








    //-------------------------------------------------------------------------------------------------
    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct", "onResume")
    }


    override fun onPause() {
        saveEinkaufsliste()
        super.onPause()
        Log.d("MyLogAct", "onPause")
    }

    override fun onStop() {
        saveEinkaufsliste()
        super.onStop()
        Log.d("MyLogAct", "onStop")
    }

    override fun onDestroy() {
        saveEinkaufsliste()
        super.onDestroy()
        Log.d("MyLogAct", "onDestroy")
    }
}

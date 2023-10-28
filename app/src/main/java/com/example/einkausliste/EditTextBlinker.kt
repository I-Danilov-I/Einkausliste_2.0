package com.example.einkausliste

// Import-Anweisungen: Diese Bibliotheken und Klassen werden benötigt
import android.graphics.Color // Importieren des Android-Pakets für die Arbeit mit Farben
import android.os.Handler // Importieren des Android-Pakets für die Arbeit mit Threads und Aufgabenplanung
import android.os.Looper // Importieren des Android-Pakets für den Looper
import android.view.View // Importieren des Android-Pakets für die Ansicht
import android.view.animation.AlphaAnimation // Importieren der Klasse für die Alpha-Animation
import android.view.animation.Animation // Importieren der Klasse für Animationen
import android.widget.EditText // Importieren der EditText-Klasse

class TextBlinker(private val editText: EditText) {
    private var isBlinking = false

    // Konstruktor für die TextBlinker-Klasse, nimmt ein EditText-Feld als Parameter
    // und initialisiert die isBlinking-Variable auf false
    // Dieser Konstruktor wird aufgerufen, wenn ein TextBlinker-Objekt erstellt wird.
    // Es wird das übergebene EditText-Feld für die Blinkanimation verwendet.
    fun startBlinkAnimation() {
        if (!isBlinking) {
            // Erstellen einer Alpha-Animation von 0.1f (undurchsichtig) zu 1.0f (vollständig sichtbar)
            val blinkAnimation = AlphaAnimation(0.1f, 1.0f)
            blinkAnimation.duration = 2000 // Dauer der Animation in Millisekunden (hier: 2 Sekunden)
            blinkAnimation.startOffset = 20 // Verzögerung vor dem Start der Animation (hier: 20 Millisekunden)
            blinkAnimation.repeatCount = Animation.INFINITE // Wiederholen der Animation unendlich

            editText.startAnimation(blinkAnimation) // Starten der Animation auf dem EditText-Feld

            val handler = Handler(Looper.getMainLooper()) // Initialisieren eines Handlers mit dem Haupt-Looper
            val colors = intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT)
            var colorIndex = 0

            // Erstellen eines Runnable-Objekts, das die Hintergrundfarbe des EditText-Felds ändert
            // und die Animation steuert
            handler.post(object : Runnable {
                override fun run() {
                    if (isBlinking) {
                        editText.setBackgroundColor(colors[colorIndex]) // Ändern der Hintergrundfarbe
                        colorIndex = (colorIndex + 1) % colors.size
                        handler.postDelayed(this, 500) // Erneutes Posten dieses Runnables nach 500 Millisekunden
                    } else {
                        editText.setBackgroundColor(Color.TRANSPARENT)
                    }
                }
            })

            isBlinking = true
        }
    }

    // Die Methode stopBlinkAnimation stoppt die Blinkanimation
    fun stopBlinkAnimation() {
        isBlinking = false // Setzen von isBlinking auf false, um die Animation zu stoppen
        editText.clearAnimation() // Löschen der Animation auf dem EditText-Feld
        editText.setBackgroundColor(Color.TRANSPARENT) // Zurücksetzen der Hintergrundfarbe auf transparent
    }
}

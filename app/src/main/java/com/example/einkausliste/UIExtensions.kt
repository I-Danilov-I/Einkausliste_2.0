package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import androidx.media3.common.util.Log
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi

@OptIn(UnstableApi::class)
fun MainActivity.setupUI() {
    adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)
    liste = findViewById(R.id.liste)
    liste.adapter = adapter
    liste.visibility = View.VISIBLE
    eingabeText = findViewById(R.id.eingabeText)
    hinzufugenButton = findViewById(R.id.hinzufugen)
    textBlinker = TextBlinker(eingabeText)
    textBlinker.startBlinkAnimation()
    Log.d("MyLogAct", "setepUI")
}



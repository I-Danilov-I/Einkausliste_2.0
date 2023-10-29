package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.media3.common.util.Log
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi

@OptIn(UnstableApi::class)
fun MainActivity.setupUI() {
    adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)
    val liste: ListView = findViewById(R.id.liste)
    liste.adapter = adapter

    liste.setOnItemLongClickListener { parent, view, position, id ->
        val item = adapter.getItem(position)
        if (item != null) {
            eintraege.remove(item)
            adapter.notifyDataSetChanged()
            Log.d("MyLogAct", "Eintrag gelöscht: $item")
        }
        true
    }

    liste.visibility = View.VISIBLE
    eingabeText = findViewById(R.id.eingabeText)
    hinzufugenButton = findViewById(R.id.hinzufugen)
    val textBlinker = TextBlinker(eingabeText)
    textBlinker.startBlinkAnimation()
    Log.d("MyLogAct", "setupUI")
}

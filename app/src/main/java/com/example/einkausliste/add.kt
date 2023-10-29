package com.example.einkausliste

import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.AdapterView
import android.util.Log

fun hinzufugen(view: View, adapter: ArrayAdapter<String>, eingabeText: EditText, eintraege: MutableList<String>) {
    val neuerEintrag = eingabeText.text.toString()
    if (neuerEintrag.isNotEmpty()) {
        adapter.add(neuerEintrag)
        adapter.notifyDataSetChanged()
        eingabeText.text.clear()
        Log.d("MyLogAct", "Eintrag hinzugefügt: $neuerEintrag")
    }
}

fun loeschen(view: View, adapter: ArrayAdapter<String>, listView: ListView, eintraege: MutableList<String>) {
    val position = listView.checkedItemPosition
    if (position != AdapterView.INVALID_POSITION) {
        val geloeschterEintrag = adapter.getItem(position)
        eintraege.remove(geloeschterEintrag)
        adapter.notifyDataSetChanged()
        Log.d("MyLogAct", "Eintrag gelöscht: $geloeschterEintrag")
    }
}

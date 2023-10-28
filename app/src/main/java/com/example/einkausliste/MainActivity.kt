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
    private lateinit var liste: ListView
    private lateinit var eingabeText: EditText
    private lateinit var hinzufugenButton: Button
    private lateinit var adapter: ArrayAdapter<String>
    private val eintraege = mutableListOf("Wasser", "Kartoffel", "Sonnenblumenöl")
    private lateinit var textBlinker: TextBlinker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ui()
        loadEinkaufsliste()
        Log.d("MyLogAct", "onCreate")
    }

    private fun ui(){
        adapter = ArrayAdapter(this, R.layout.list_item, R.id.list_item_text, eintraege)
        liste = findViewById(R.id.liste)
        liste.adapter = adapter
        liste.visibility = View.VISIBLE

        eingabeText = findViewById(R.id.eingabeText)
        hinzufugenButton = findViewById(R.id.hinzufugen)

        textBlinker = TextBlinker(eingabeText)
        textBlinker.startBlinkAnimation()

    }


    fun hinzufugen(view: View) {
        val neuerEintrag = eingabeText.text.toString()

        if (neuerEintrag.isNotEmpty()) {
            adapter.add(neuerEintrag)
            adapter.notifyDataSetChanged()
            eingabeText.text.clear()
            saveEinkaufsliste()
            Log.d("MyLogAct", "hinzufugen")
        }
    }

    private fun saveEinkaufsliste() {
        val eintraegeSet = HashSet(eintraege)
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("einkaufsliste", eintraegeSet)
        editor.apply()
        Log.d("MyLogAct", "saveEinkaufsliste")
    }

    private fun loadEinkaufsliste() {
        val sharedPreferences = getSharedPreferences("Einkaufsliste", MODE_PRIVATE)
        val savedEintraegeSet = sharedPreferences.getStringSet("einkaufsliste", null)

        if (savedEintraegeSet != null) {
            eintraege.clear()
            eintraege.addAll(savedEintraegeSet)
            adapter.notifyDataSetChanged()
        }
        Log.d("MyLogAct", "loadEinkaufsliste")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MyLogAct", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MyLogAct", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyLogAct", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLogAct", "onStop")
    }

    override fun onDestroy() {
        saveEinkaufsliste()
        textBlinker.stopBlinkAnimation()
        super.onDestroy()
        Log.d("MyLogAct", "onDestroy")
    }
}

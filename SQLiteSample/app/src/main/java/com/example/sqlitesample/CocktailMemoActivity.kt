package com.example.sqlitesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class CocktailMemoActivity : AppCompatActivity() {

    private var cocktailId = -1
    private var cocktailName = ""
    private var tvCocktailName: TextView? = null
    private var btnSave: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_memo)

        tvCocktailName = findViewById(R.id.tvCocktailName)
        btnSave = findViewById(R.id.btnSave)
        val lvCocktail = findViewById<ListView>(R.id.lvCocktail)
        lvCocktail.setOnItemClickListener { parent, _, position, _ ->
            cocktailId = position
            cocktailName = parent.getItemAtPosition(position).toString()
            tvCocktailName?.text = cocktailName
            btnSave?.isEnabled = true
        }
    }

    fun onSaveButtonClick(view: View) {
        val etNote = findViewById<EditText>(R.id.etNote)
        tvCocktailName?.text = getString(R.string.tv_name)
        etNote.setText("")
        btnSave?.isEnabled = false
    }
}

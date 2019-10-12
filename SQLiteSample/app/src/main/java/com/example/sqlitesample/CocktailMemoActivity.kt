package com.example.sqlitesample

import android.annotation.SuppressLint
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

    @Suppress("NAME_SHADOWING")
    @SuppressLint("Recycle")
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

            val helper = DatabaseHelper(this)
            val db = helper.writableDatabase
            db.use { db ->
                val sql = "SELECT * FROM cocktailmemo WHERE _id = $cocktailId"
                val cursor = db.rawQuery(sql, null)
                var note = ""
                while (cursor.moveToNext()) {
                    val indexNote = cursor.getColumnIndex("note")
                    note = cursor.getString(indexNote)
                }
                val etNote = findViewById<EditText>(R.id.etNote)
                etNote.setText(note)
            }
        }
    }

    @Suppress("NAME_SHADOWING")
    fun onSaveButtonClick(view: View) {
        val etNote = findViewById<EditText>(R.id.etNote)
        val note = etNote.text.toString()

        val helper = DatabaseHelper(this)
        val db = helper.writableDatabase

        db.use { db ->
            // すでに入っているデータを削除
            val sqlDelete = "DELETE FROM cocktailmemo WHERE _id = ?"
            var stmt = db.compileStatement(sqlDelete)
            stmt.bindLong(1, cocktailId.toLong())
            stmt.executeUpdateDelete()

            // 新しいデータを追加
            val sqlInsert = "INSERT INTO cocktailmemo (_id, name, note) VALUES (?, ?, ?)"
            stmt = db.compileStatement(sqlInsert)
            stmt.bindLong(1, cocktailId.toLong())
            stmt.bindString(2, cocktailName)
            stmt.bindString(3, note)
            stmt.executeInsert()
        }
        tvCocktailName?.text = getString(R.string.tv_name)
        etNote.setText("")
        btnSave?.isEnabled = false
    }
}

package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.example.a202109kotlinfinalporject.MyDBHelper
import com.example.a202109kotlinfinalporject.R

class RecordsActivity : AppCompatActivity() {

    private lateinit var sqLiteDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        val listView = findViewById<ListView>(R.id.recordsListView)


        sqLiteDatabase = MyDBHelper(this).writableDatabase
        setListener()

        /*listView.adapter = MyAdapter(this, null, //TODO:get sql database
            R.layout.foodlayout)*/
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.recordsReturnButton)

        returnButton.setOnClickListener {
            //startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }
    }
}
package com.example.a202109kotlinfinalporject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RecordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        setListener()
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.recordsReturnButton)

        returnButton.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
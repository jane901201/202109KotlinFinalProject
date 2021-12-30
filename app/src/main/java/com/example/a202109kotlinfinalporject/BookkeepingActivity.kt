package com.example.a202109kotlinfinalporject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BookkeepingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_keeping)

        setListener()
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.bookkeepingReturnButton)

        returnButton.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
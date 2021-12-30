package com.example.a202109kotlinfinalporject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FeedPetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_pet)

        setListener()
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.feedPetReturnButton)

        returnButton.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
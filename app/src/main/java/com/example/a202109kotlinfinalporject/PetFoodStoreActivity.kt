package com.example.a202109kotlinfinalporject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PetFoodStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_food_store)

        setListener()
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.storeReturnButton)

        returnButton.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
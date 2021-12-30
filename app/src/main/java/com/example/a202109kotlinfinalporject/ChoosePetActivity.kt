package com.example.a202109kotlinfinalporject

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ChoosePetActivity : AppCompatActivity() {

    private lateinit var choosePet: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosepet)

        setLisener()

    }

    private fun setLisener() {
        var boxImageButton = findViewById<ImageButton>(R.id.boxImageButton)
        var eggImageButton = findViewById<ImageButton>(R.id.eggImageButton)
        var stoneImageButton = findViewById<ImageButton>(R.id.stoneImageButton)
        var editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)

        boxImageButton.setOnClickListener {
            choosePet = "box"
            showToast("Choose ${choosePet}")
            nextActivitySetting()
        }

        eggImageButton.setOnClickListener {
            choosePet = "egg"
            showToast("Choose ${choosePet}")
            nextActivitySetting()
        }

        stoneImageButton.setOnClickListener {
            choosePet = "stone"
            showToast("Choose ${choosePet}")
            nextActivitySetting()
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    private fun nextActivitySetting() {



        startActivity(Intent(this, MainMenuActivity::class.java))
    }
}
package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.a202109kotlinfinalporject.R

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

        boxImageButton.setOnClickListener {
            if(findViewById<EditText>(R.id.editTextTextPersonName).length() < 1) {
                showToast("請輸入寵物名稱")
            } else {
                choosePet = "箱子"
                showToast("選擇 ${choosePet}")
                nextActivitySetting()
            }
        }

        eggImageButton.setOnClickListener {
            if(findViewById<EditText>(R.id.editTextTextPersonName).length() < 1) {
                showToast("請輸入寵物名稱")
            } else {
                choosePet = "蛋"
                showToast("選擇 ${choosePet}")
                nextActivitySetting()
            }
        }

        stoneImageButton.setOnClickListener {
            if(findViewById<EditText>(R.id.editTextTextPersonName).length() < 1) {
                showToast("請輸入寵物名稱")
            } else {
                choosePet = "石頭"
                showToast("選擇 ${choosePet}")
                nextActivitySetting()
            }
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    private fun nextActivitySetting() {
        val bundle = Bundle()
        bundle.putString("name","${findViewById<EditText>(R.id.editTextTextPersonName).text}")
        bundle.putString("pet", choosePet)
        setResult(RESULT_OK, Intent().putExtras(bundle))
        finish()
    }
}
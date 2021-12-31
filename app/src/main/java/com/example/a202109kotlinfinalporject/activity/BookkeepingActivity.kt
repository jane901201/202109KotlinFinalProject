package com.example.a202109kotlinfinalporject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.a202109kotlinfinalporject.R

class BookkeepingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_keeping)

        setListener()

    }

    private fun setListener() {
        setReturnButton()
        //TODO:setCheckButton()
    }

    private fun setCheckButton() {
        var checkButton = findViewById<Button>(R.id.checkButton)

        checkButton.setOnClickListener {
            val radioGroup= findViewById<RadioGroup>(R.id.radioGroup)
            val costEditText = findViewById<EditText>(R.id.CostEditText)
            val recordsNameEditText = findViewById<EditText>(R.id.recordNameEditView)

            //將飲料名稱、甜度、冰塊放入Bundle
            val b = Bundle()

            b.putString("cost", costEditText.text.toString()) //TODO:change to putInt()
            b.putString("record_name", recordsNameEditText.text.toString())
            b.putString("cost_type", radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())

            //透過setResult將資料回傳
            setResult(Activity.RESULT_OK, Intent().putExtras(b))
            //結束Main3Activity
            finish()


        }
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.bookkeepingReturnButton)

        returnButton.setOnClickListener {
            finish()
            //startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
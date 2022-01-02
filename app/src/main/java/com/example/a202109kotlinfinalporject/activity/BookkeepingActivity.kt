package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        setCheckButton()
    }

    private fun setCheckButton() {
        var checkButton = findViewById<ImageButton>(R.id.checkImageButton)


        checkButton.setOnClickListener {
            val radioGroup= findViewById<RadioGroup>(R.id.radioGroup)
            var costType: String = ""
            val costEditText = findViewById<EditText>(R.id.CostEditText)
            val recordsNameEditText = findViewById<EditText>(R.id.recordNameEditView)

            var  cost = costEditText.text.toString().toInt()
            if(radioGroup.checkedRadioButtonId == 2131231244) costType = "收入"
            else if(radioGroup.checkedRadioButtonId == 2131231243)costType = "支出"

            val bundle = Bundle()

            bundle.putString("costType", costType)
            bundle.putString("name", recordsNameEditText.text.toString())
            bundle.putInt("price", cost)
            setResult(RESULT_OK, Intent().putExtras(bundle))
            finish()
        }
    }


    private fun setReturnButton() {
        var returnButton = findViewById<ImageButton>(R.id.bookkeepingReturnImageButton)

        returnButton.setOnClickListener {
            finish()
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}
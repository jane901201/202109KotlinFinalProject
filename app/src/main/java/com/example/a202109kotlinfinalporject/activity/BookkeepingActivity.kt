package com.example.a202109kotlinfinalporject.activity

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.a202109kotlinfinalporject.MyDBHelper
import com.example.a202109kotlinfinalporject.R

class BookkeepingActivity : AppCompatActivity() {

    private lateinit var sqlLiteDatabase: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_keeping)

        sqlLiteDatabase = MyDBHelper(this).writableDatabase

        setListener()

    }

    private fun setListener() {
        setReturnButton()
        setCheckButton()
    }

    private fun setCheckButton() {
        var checkButton = findViewById<Button>(R.id.checkButton)

        checkButton.setOnClickListener {
            setSQL()

            finish()
        }
    }

    private fun setSQL() {
        val radioGroup= findViewById<RadioGroup>(R.id.radioGroup)
        var costType: String = ""
        val costEditText = findViewById<EditText>(R.id.CostEditText)
        val recordsNameEditText = findViewById<EditText>(R.id.recordNameEditView)

        var  cost = costEditText.text
        if(radioGroup.checkedRadioButtonId == 2131231244) costType = "收入"
        else if(radioGroup.checkedRadioButtonId == 2131231243)costType = "支出"

        try {
            sqlLiteDatabase.execSQL(
                "INSERT INTO recordsTable(type, name, price) VALUES('${costType}', '${recordsNameEditText.text}', '${cost}')",
            )
            showToast("新增成功")
        } catch (e: Exception) {
            showToast("新增失敗:$e")
            Log.i("BookkeepingActivity" ,e.toString())
        }
    }


    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.bookkeepingReturnButton)

        returnButton.setOnClickListener {
            finish()
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}
package com.example.a202109kotlinfinalporject.activity

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        //TODO:setCheckButton()
    }

    private fun setCheckButton() {
        var checkButton = findViewById<Button>(R.id.checkButton)

        checkButton.setOnClickListener {
            //TODO:setSQL()
            //val radioGroup= findViewById<RadioGroup>(R.id.radioGroup)
            //val costEditText = findViewById<EditText>(R.id.CostEditText)
            //val recordsNameEditText = findViewById<EditText>(R.id.recordNameEditView)

            //將飲料名稱、甜度、冰塊放入Bundle
           //val b = Bundle()

            //b.putString("cost", costEditText.text.toString())
            //b.putString("record_name", recordsNameEditText.text.toString())
            //b.putString("cost_type", radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())

            //透過setResult將資料回傳
            //setResult(Activity.RESULT_OK, Intent().putExtras(b))
            //結束Main3Activity
            finish()


        }
    }

    private fun setSQL() {
        val radioGroup= findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        val costEditText = findViewById<EditText>(R.id.CostEditText)
        val recordsNameEditText = findViewById<EditText>(R.id.recordNameEditView)


        try {
            sqlLiteDatabase.execSQL(
                "INSERT INTO recordsTable(costtype, name, price) VALUES(?, ?, ?)",
                arrayOf(selectedRadioButton.text.toString(),
                    recordsNameEditText.text.toString(), costEditText.text.toString())
            )
        } catch (e: Exception) {
            showToast("新增失敗:$e")
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
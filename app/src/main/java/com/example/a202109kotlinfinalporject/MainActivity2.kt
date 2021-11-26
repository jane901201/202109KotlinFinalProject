package com.example.a202109kotlinfinalporject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity2 : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        data?.extras?.let {
            //驗證發出對象，確認 SecActivity 執行的狀態
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                //讀取 Bundle 資料
                findViewById<TextView>(R.id.numberTextView).text =
                    "桌號: ${it.getString("number")}"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //將變數與 XML 元件綁定
        val checkButton = findViewById<Button>(R.id.checkButton)
        val menuSelectRadioGroup = findViewById<RadioGroup>(R.id.menuSelectRadioGroup)
        checkButton.setOnClickListener {
                //宣告 Bundle
                val b = Bundle()
                //取得 EditText 字串內容，把飲料名稱、甜度與冰塊資訊放入 Bundle
                b.putString("number", menuSelectRadioGroup.findViewById<RadioButton>
                    (menuSelectRadioGroup.checkedRadioButtonId).text.toString())
                //用 Activity.RESULT_OK 標記執行狀態並記錄 Intent
                setResult(Activity.RESULT_OK, Intent().putExtras(b))
                finish()
        }
    }
}
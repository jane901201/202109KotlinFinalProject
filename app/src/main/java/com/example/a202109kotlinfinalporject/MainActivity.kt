package com.example.a202109kotlinfinalporject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        data?.extras?.let {
            //驗證發出對象，確認 SecActivity 執行的狀態
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                //讀取 Bundle 資料
                findViewById<TextView>(R.id.menuInformationText).text =
                    "主餐: ${it.getString("menu")}\n" //+
                    //"桌號: ${it.getString("number")}"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterNumberEditText = findViewById<EditText>(R.id.enterNumberEditText);
        val numberInformationTextView = findViewById<TextView>(R.id.numberInformationTextView);

        findViewById<Button>(R.id.menuSelectButton).setOnClickListener {

            //val b = Bundle()
            //取得 EditText 字串內容，把飲料名稱、甜度與冰塊資訊放入 Bundle
            //b.putString("number", enterNumberEditText.text.toString())

             numberInformationTextView.text = "桌號：" + enterNumberEditText.text;

            //透過 Intent 切換至 SecActivity 並傳遞 requestCode 作為識別編號
            val intent = Intent(this, MainActivity2::class.java)
            startActivityForResult(intent, 1)
        }
    }
}
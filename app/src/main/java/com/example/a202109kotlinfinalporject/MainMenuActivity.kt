package com.example.a202109kotlinfinalporject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainMenuActivity : AppCompatActivity() {

    private var userName: String = ""
    private var pet: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                //讀取並顯示寵物選擇頁返回的資料
                findViewById<TextView>(R.id.petNameTextView).text= it.data?.getStringExtra("name")+
                        "的寵物"+it.data?.getStringExtra("pet")
            }
        }


        if(userName == "" && pet == "") {
            val intent = Intent( this, ChoosePetActivity::class.java)
            register.launch(intent)
        }

    }
}

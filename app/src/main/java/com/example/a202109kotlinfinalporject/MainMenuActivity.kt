package com.example.a202109kotlinfinalporject

import android.app.Activity
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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
                userName = it.data?.getStringExtra("name").toString()
                pet = it.data?.getStringExtra("pet").toString()

                setPetImage()
            }
        }


        if(userName == "" && pet == "") {
            val intent = Intent( this, ChoosePetActivity::class.java)
            register.launch(intent)
        }

        //setPetImage()

    }

    private fun setPetImage() {
        val petImageButton = findViewById<ImageButton>(R.id.mainMenuPetButton)

        if(pet == "箱子") {
            petImageButton.setImageResource(R.drawable.box)
        }else if(pet == "蛋") {
            petImageButton.setImageResource(R.drawable.egg)
        }else if(pet == "石頭") {
            petImageButton.setImageResource(R.drawable.stone)
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}

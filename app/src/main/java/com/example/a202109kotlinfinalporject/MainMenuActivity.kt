package com.example.a202109kotlinfinalporject

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainMenuActivity : AppCompatActivity() {

    private lateinit var sqlLiteDatabase: SQLiteDatabase
    private var foodItems: ArrayList<FoodItem> = ArrayList()
    private var userData: UserData = UserData("", "", 999, 0)//TODO:Test coin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        sqlLiteDatabase = MyDBHelper(this).writableDatabase

        setSQL()
        registerUserNameAndChoosePet()
        setListener()

    }

    override fun onDestroy() {
        sqlLiteDatabase.close() //TODO:May have some problem
        super.onDestroy()
    }

    private fun setSQL() {
        try {
            /*sqlLiteDatabase.execSQL(
                "INSERT INTO FoodTable(name, count, price) VALUES(?, ?, ?)",
                arrayOf(ed_number.text.toString(),
                    ed_name.text.toString(), ed_phone.text.toString(), ed_address.text.toString())
            )*/
        } catch (e: Exception) {
            showToast("新增失敗:$e")
        }
    }

    private fun setListener() {
        val bookKeepingButton = findViewById<ImageButton>(R.id.openBookkeepingImageButton)
        val recordsButton = findViewById<ImageButton>(R.id.openRecordsImageButton)

        bookKeepingButton.setOnClickListener {
            showToast("bookKeeping")
            startActivity(Intent(this, BookkeepingActivity::class.java))
        }

        recordsButton.setOnClickListener {
            showToast("recordsButton")
            startActivity(Intent(this, RecordsActivity::class.java))
        }

        setStoreButton()
        setPetButton()
    }

    private fun setStoreButton() {
        val storeButton = findViewById<ImageButton>(R.id.openStoreImageButton)

        storeButton.setOnClickListener {
            showToast("storeButton")
            startActivity(Intent(this, PetFoodStoreActivity::class.java))
        }
    }

    private fun setPetButton() {
        val petButton = findViewById<ImageButton>(R.id.mainMenuPetButton)

        petButton.setOnClickListener {
            showToast("petButton")
            startActivity(Intent(this, FeedPetActivity::class.java))
        }
    }

    private fun registerUserNameAndChoosePet() {


            if(userData.name == "" && userData.pet == "") {
            val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if(it.resultCode == Activity.RESULT_OK){
                    //讀取並顯示寵物選擇頁返回的資料
                    findViewById<TextView>(R.id.petNameTextView).text= it.data?.getStringExtra("name")+
                            "的寵物"+it.data?.getStringExtra("pet")
                    userData.name = it.data?.getStringExtra("name").toString()
                    userData.pet = it.data?.getStringExtra("pet").toString()

                    setPetImage()
                }
            }

            val intent = Intent( this, ChoosePetActivity::class.java)
            register.launch(intent)
        }
    }

    private fun setPetImage() {
        val petImageButton = findViewById<ImageButton>(R.id.mainMenuPetButton)

        if(userData.pet == "箱子") {
            petImageButton.setImageResource(R.drawable.box)
        }else if(userData.pet == "蛋") {
            petImageButton.setImageResource(R.drawable.egg)
        }else if(userData.pet == "石頭") {
            petImageButton.setImageResource(R.drawable.stone)
        }
    }

    private fun SetFoodItem() {
        var photo: Int = 0
        var name: String = ""
        var price: Int = 0

        foodItems.add(FoodItem(photo, name, count = 0, price))
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}

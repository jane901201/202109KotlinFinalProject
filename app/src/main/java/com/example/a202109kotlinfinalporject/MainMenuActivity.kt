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

    private var userName: String = ""
    private var pet: String = ""
    private var coin: Int = 0


    private lateinit var sqlLiteDatabase: SQLiteDatabase
    private var foodItems: ArrayList<FoodItem> = ArrayList()
    private lateinit var userData: UserData


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
        val petButton = findViewById<ImageButton>(R.id.mainMenuPetButton)
        val storeButton = findViewById<ImageButton>(R.id.openStoreImageButton)
        val bookKeepingButton = findViewById<ImageButton>(R.id.openBookkeepingImageButton)
        val recordsButton = findViewById<ImageButton>(R.id.openRecordsImageButton)

        petButton.setOnClickListener {
            showToast("petButton")
            startActivity(Intent(this, FeedPetActivity::class.java))
        }

        storeButton.setOnClickListener {
            showToast("storeButton")
            startActivity(Intent(this, PetFoodStoreActivity::class.java))
        }

        bookKeepingButton.setOnClickListener {
            showToast("bookKeeping")
            startActivity(Intent(this, BookkeepingActivity::class.java))
        }

        recordsButton.setOnClickListener {
            showToast("recordsButton")
            startActivity(Intent(this, RecordsActivity::class.java))
        }
    }

    private fun registerUserNameAndChoosePet() {


        if(userName == "" && pet == "") {
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

            val intent = Intent( this, ChoosePetActivity::class.java)
            register.launch(intent)
        }
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

    private fun SetFoodItem() {
        var photo: Int = 0
        var name: String = ""
        var price: Int = 0

        foodItems.add(FoodItem(photo, name, count = 0, price))
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}

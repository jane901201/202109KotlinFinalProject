package com.example.a202109kotlinfinalporject.activity

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.a202109kotlinfinalporject.MyDBHelper
import com.example.a202109kotlinfinalporject.R
import com.example.a202109kotlinfinalporject.dataclass.FoodItem
import com.example.a202109kotlinfinalporject.dataclass.UserData

class MainMenuActivity : AppCompatActivity() {

    private lateinit var sqlLiteDatabase: SQLiteDatabase
    private var foodItems: ArrayList<FoodItem> = ArrayList()
    private var userData: UserData = UserData("", "", 999, 0)//TODO:Test coin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        sqlLiteDatabase = MyDBHelper(this).writableDatabase

        if(foodItems.count() == 0) setFoodItem()
        setSQL()
        if(userData.name == "" && userData.pet == "") registerUserNameAndChoosePet()
        val storeRegister = storeRegister()
        setStoreButton(storeRegister)

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

        setPetButton()
    }

    private fun setStoreButton(storeRegister: ActivityResultLauncher<Intent>) {
        val storeButton = findViewById<ImageButton>(R.id.openStoreImageButton)

        storeButton.setOnClickListener {
            showToast("storeButton")
            /*val storeRegister = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if(it.resultCode == Activity.RESULT_OK){
                    //讀取並顯示寵物選擇頁返回的資料
                    userData.coin = it.data?.getIntExtra("coin", 0)!! //TODO:May have problem
                    findViewById<TextView>(R.id.petCoinTextView).text= userData.coin.toString()
                    for(i in 0..foodItems.count()) {
                        var foodCount: String = "foodCount$i"
                        foodItems[i].count = it.data?.getIntExtra(foodCount, 0)!!
                        Log.i("MainMenuActivity" ,"${foodItems[i].name} count ${foodItems[i].count}")
                    }
                }
            }*/

            val bundle = Bundle()
            /*var foodItemCounts = IntArray(6)
            for(i in 0..foodItems.count()) {
                var foodCount: String = "foodCount$i"
                foodItemCounts[i] = foodItems[i].count

                Log.i("MainMenuActivity" ,"${foodItems[i].name} count ${foodItems[i].count}")
            }*/

            //bundle.putParcelable("foodItems", foodItems)
            //bundle.putIntArray("foodItemsArray", foodItemCounts)
            bundle.putInt("coin", userData.coin)

            val intent = Intent( this, PetFoodStoreActivity::class.java)
            intent.putExtras(bundle)
            storeRegister.launch(intent)
        }
    }

    private fun setPetButton() {
        val petButton = findViewById<ImageButton>(R.id.mainMenuPetButton)

        petButton.setOnClickListener {
            showToast("petButton")
            startActivity(Intent(this, FeedPetActivity::class.java))
        }
    }

    private fun storeRegister(): ActivityResultLauncher<Intent> {
        val storeRegister = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                //讀取並顯示寵物選擇頁返回的資料
                userData.coin = it.data?.getIntExtra("coin", 0)!! //TODO:May have problem
                findViewById<TextView>(R.id.petCoinTextView).text= userData.coin.toString()
                for(i in 0..foodItems.count()) {
                    var foodCount: String = "foodCount$i"
                    foodItems[i].count = it.data?.getIntExtra(foodCount, 0)!!
                    Log.i("MainMenuActivity" ,"${foodItems[i].name} count ${foodItems[i].count}")
                }
            }
        }

        return storeRegister
    }

    private fun registerUserNameAndChoosePet() {
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

    private fun setFoodItem() {
        val foodName = ArrayList<String>()
        foodName.add("火")
        foodName.add("油")
        foodName.add("剪刀")
        foodName.add("膠水")
        foodName.add("木鎬")
        foodName.add("砂紙")

        val foodPrice = ArrayList<Int>()
        foodPrice.add(10)
        foodPrice.add(30)
        foodPrice.add(50)
        foodPrice.add(20)
        foodPrice.add(200)
        foodPrice.add(30)

        val array = resources.obtainTypedArray(R.array.food_image_list)
        for(i in 0 until array.length()) {
            var photo: Int = array.getResourceId(i, 0)
            var name: String = foodName[i]
            var price: Int = foodPrice[i]

            foodItems.add(FoodItem(photo, name, count = 0, price))
        }

        showToast("FoodItem count${foodItems.count()}")
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}

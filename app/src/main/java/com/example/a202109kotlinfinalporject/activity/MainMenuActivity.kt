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
import com.example.a202109kotlinfinalporject.dataclass.RecordsData
import com.example.a202109kotlinfinalporject.dataclass.UserData

class MainMenuActivity : AppCompatActivity() {

    private lateinit var sqlLiteDatabase: SQLiteDatabase
    private var foodItems: ArrayList<FoodItem> = ArrayList()
    private var recordsData:ArrayList<RecordsData> = ArrayList()
    private var userData: UserData = UserData("", "", 999, 0)//TODO:Test coin


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        sqlLiteDatabase = MyDBHelper(this).writableDatabase

        if(foodItems.count() == 0) setFoodItem()
        //setSQL()
        if(userData.name == "" && userData.pet == "") registerUserNameAndChoosePet()
        val storeRegister = storeRegister()
        setStoreButton(storeRegister)
        findViewById<TextView>(R.id.mainMenuPetCoinTextView).text = userData.coin.toString()
        setListener()


    }

    override fun onDestroy() {
        sqlLiteDatabase.execSQL("DROP TABLE if exists recordsTable")
        sqlLiteDatabase.close()
        super.onDestroy()
    }


    private fun setListener() {
        val bookKeepingButton = findViewById<ImageButton>(R.id.openBookkeepingImageButton)
        var bookkeepingRegister = bookKeepingRegister()

        bookKeepingButton.setOnClickListener {
            showToast("bookKeeping")
            val intent = Intent(this, BookkeepingActivity::class.java)
            bookkeepingRegister.launch(intent)
            //startActivity(Intent(this, BookkeepingActivity::class.java))
        }

        setRecordsButton()
        val feedPetRegister = feedPetRegister()
        setFeedPetButton(feedPetRegister)
    }

    private fun setStoreButton(storeRegister: ActivityResultLauncher<Intent>) {
        val storeButton = findViewById<ImageButton>(R.id.openStoreImageButton)

        storeButton.setOnClickListener {
            showToast("storeButton")

            val bundle = Bundle()
            var foodItemCounts = IntArray(6)
            for(i in 0..(foodItemCounts.size - 1)) {
                foodItemCounts[i] = foodItems[i].count
                Log.i("MainMenuActivity" ,"$i count ${foodItemCounts[i]}")
            }

            //TODO:bundle.putParcelable("foodItems", foodItems)
            bundle.putIntArray("foodItemsArray", foodItemCounts)
            bundle.putInt("coin", userData.coin)

            val intent = Intent( this, PetFoodStoreActivity::class.java)
            intent.putExtras(bundle)
            storeRegister.launch(intent)
        }
    }

    private fun setFeedPetButton(feedPetRegister: ActivityResultLauncher<Intent>) {
        val petButton = findViewById<ImageButton>(R.id.mainMenuPetButton)

        petButton.setOnClickListener {
            showToast("petButton")
            val bundle = Bundle()
            var foodItemCounts = IntArray(6)
            for(i in 0..(foodItemCounts.size - 1)) {
                foodItemCounts[i] = foodItems[i].count
                Log.i("MainMenuActivity" ,"$i count ${foodItemCounts[i]}")
            }

            //TODO:bundle.putParcelable("foodItems", foodItems)
            bundle.putIntArray("foodItemsArray", foodItemCounts)
            bundle.putString("petName", userData.pet)

            val intent = Intent( this, FeedPetActivity::class.java)
            intent.putExtras(bundle)
            feedPetRegister.launch(intent)
        }
    }

    private fun setRecordsButton() {
        val recordsButton = findViewById<ImageButton>(R.id.openRecordsImageButton)

        recordsButton.setOnClickListener {
            showToast("recordsButton")
            var bundle = Bundle()

            var costTypes: ArrayList<String> = ArrayList()
            var names: ArrayList<String> = ArrayList()
            var prices = IntArray(recordsData.size)
            for(i in 0..(recordsData.size - 1)) {
                costTypes.add(recordsData[i].costType)
            }
            for(i in 0..(recordsData.size - 1)) {
                names.add(recordsData[i].name)
            }
            for(i in 0..(recordsData.size - 1)) {
                prices[i] = recordsData[i].price
            }


            bundle.putStringArrayList("costType", costTypes)
            bundle.putStringArrayList("name", names)
            bundle.putIntArray("price", prices)

            val intent = Intent( this, RecordsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun storeRegister(): ActivityResultLauncher<Intent> {
        val storeRegister = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                //讀取並顯示寵物選擇頁返回的資料
                userData.coin = it.data?.getIntExtra("coin", 0)!!
                var foodItemCounts = it.data?.getIntArrayExtra("foodItemCounts")
                findViewById<TextView>(R.id.mainMenuPetCoinTextView).text= userData.coin.toString()
                for(i in 0..(foodItems.count() -1)) {
                    var foodCount: String = "foodCount$i"
                    foodItems[i].count = foodItemCounts?.get(i) ?:
                            Log.i("MainMenuActivity" ,"${foodItems[i].name} count ${foodItems[i].count}")
                }
            }
        }

        return storeRegister
    }

    private fun feedPetRegister(): ActivityResultLauncher<Intent> {
        val feedPetRegister = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                //讀取並顯示寵物選擇頁返回的資料
                userData.growPoint = it.data?.getIntExtra("growPoint", 0)!!
                findViewById<TextView>(R.id.mainMenuGrowPointTextView).text = "成長值:${userData.growPoint}"
                Log.i("MainMenuActivity", userData.growPoint.toString())
                var foodItemCounts = it.data?.getIntArrayExtra("foodItemCounts")
                findViewById<TextView>(R.id.mainMenuPetCoinTextView).text= userData.coin.toString()
                for(i in 0..(foodItems.count() -1)) {
                    var foodCount: String = "foodCount$i"
                    foodItems[i].count = foodItemCounts?.get(i) ?:
                            Log.i("MainMenuActivity" ,"${foodItems[i].name} count ${foodItems[i].count}")
                }
            }
        }

        return feedPetRegister
    }
    private fun bookKeepingRegister(): ActivityResultLauncher<Intent> {
        val bookKeepRegister = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val costType: String = it.data?.getStringExtra("costType")!!
                val name: String = it.data?.getStringExtra("name")!!
                val price : Int = it.data?.getIntExtra("price", 0)!!
                recordsData.add(RecordsData(costType, name, price))
                Log.i("MainMenuActivity", recordsData.count().toString())
            }
        }

        return bookKeepRegister
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

            foodItems.add(FoodItem(photo, name, 0, price))
        }

        showToast("FoodItem count${foodItems.count()}")
        array.recycle()
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}

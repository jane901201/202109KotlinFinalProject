package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.a202109kotlinfinalporject.MyAdapter
import com.example.a202109kotlinfinalporject.R
import com.example.a202109kotlinfinalporject.dataclass.FoodItem

class PetFoodStoreActivity : AppCompatActivity() {

    private var foodItems: ArrayList<FoodItem> = ArrayList()
    private lateinit var foodItemCounts: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_food_store)

        val petCoin = findViewById<TextView>(R.id.petStorePetCoinTextView)

        intent?.extras?.let{
            petCoin.text = "寵物幣:${it.getInt("coin").toString()}"
            foodItemCounts = it.getIntArray("foodItemsArray")!!
            Log.i("PetFoodStoreActivity", foodItemCounts.size.toString())
        }
        setFoodItem()
        setListener()
        setGridView()
    }

    private fun setGridView() {
        //將變數與 XML 元件綁定
        val gridView = findViewById<GridView>(R.id.storeFoodGridView)
        val count = ArrayList<String>() //儲存購買數量資訊
        val item = ArrayList<FoodItem>() //TODO:Temp


        //val priceRange = IntRange(10, 100) //建立價格範圍
        /*val array =
            resources.obtainTypedArray(R.array.food_image_list) //從 R 類別讀取圖檔
        for(i in 0 until array.length()) {
            val photo = array.getResourceId(i,0) //水果圖片 Id
            val name = "Test" //TODO:水果名稱
            val price = priceRange.random() //亂數產生價格
            count.add("${i+1}個") //新增可購買數量資訊
            item.add(FoodItem(photo, name, foodItemCounts[i] ,price)) //新增水果資訊
        }*/
        //array.recycle() //釋放圖檔資源
        //設定橫向顯示列數
        gridView.numColumns = 2
        //建立 MyAdapter 物件，並傳入 adapter_vertical 作為畫面
        //gridView.adapter = MyAdapter(this, item, R.layout.store_food_layout)
        gridView.adapter = MyAdapter(this, foodItems, R.layout.store_food_layout)
        //建立 MyAdapter 物件，並傳入 adapter_horizontal 作為畫面

        gridView.onItemClickListener
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

            foodItems.add(FoodItem(photo, name, foodItemCounts[i], price))
        }
        array.recycle()
    }


    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.storeReturnButton)

        returnButton.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("coin", 88)

            setResult(RESULT_OK, Intent().putExtras(bundle))
            finish()
        }
    }
}
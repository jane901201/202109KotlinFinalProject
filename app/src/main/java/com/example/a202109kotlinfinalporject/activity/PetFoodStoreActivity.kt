package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.a202109kotlinfinalporject.MyAdapter
import com.example.a202109kotlinfinalporject.R
import com.example.a202109kotlinfinalporject.dataclass.FoodItem

class PetFoodStoreActivity : AppCompatActivity() {

    private lateinit var foodItem: FoodItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_food_store)

        setListener()
        //TODO:setGridView()


    }

    private fun setGridView() {
        //將變數與 XML 元件綁定
        val gridView = findViewById<GridView>(R.id.storeFoodGridView)
        val count = ArrayList<String>() //儲存購買數量資訊
        val item = ArrayList<FoodItem>() //儲存水果資訊


        val priceRange = IntRange(10, 100) //建立價格範圍
        val array =
            resources.obtainTypedArray(R.array.food_image_list) //從 R 類別讀取圖檔
        for(i in 0 until array.length()) {
            val photo = array.getResourceId(i,0) //水果圖片 Id
            val name = "" //TODO:水果名稱
            val price = priceRange.random() //亂數產生價格
            count.add("${i+1}個") //新增可購買數量資訊
            item.add(FoodItem(photo, name, count = 0 ,price)) //新增水果資訊
        }
        array.recycle() //釋放圖檔資源
        //設定橫向顯示列數
        gridView.numColumns = 3
        //建立 MyAdapter 物件，並傳入 adapter_vertical 作為畫面
        gridView.adapter = MyAdapter(this, item, R.layout.store_food_layout)
        //建立 MyAdapter 物件，並傳入 adapter_horizontal 作為畫面

        gridView.onItemClickListener
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.storeReturnButton)

        returnButton.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
        }
    }
}
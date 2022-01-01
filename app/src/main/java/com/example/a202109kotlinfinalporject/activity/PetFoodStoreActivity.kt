package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.view.get
import com.example.a202109kotlinfinalporject.MyAdapter
import com.example.a202109kotlinfinalporject.R
import com.example.a202109kotlinfinalporject.dataclass.FoodItem

class PetFoodStoreActivity : AppCompatActivity() {

    private var foodItems: ArrayList<FoodItem> = ArrayList()
    private lateinit var foodItemCounts: IntArray
    private var coin: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_food_store)

        val petCoin = findViewById<TextView>(R.id.petStorePetCoinTextView)

        intent?.extras?.let{
            petCoin.text = "寵物幣:${it.getInt("coin").toString()}"
            coin = it.getInt("coin")
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

        //設定橫向顯示列數
        gridView.numColumns = 2
        gridView.adapter = MyAdapter(this, foodItems, R.layout.store_food_layout)
        //建立 MyAdapter 物件，並傳入 store_food_layout 作為畫面

        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItemText: FoodItem = parent.getItemAtPosition(position) as FoodItem
                val selectedItem = view.findViewById<TextView>(R.id.foodCountTextView)
                if(coin > 0 && coin >= selectedItemText.price) {
                    Log.i("PetFoodStoreActivity", selectedItemText.name)
                    cost(selectedItemText.price)
                    addCount(selectedItemText.name, selectedItem)
                }
                else showToast("沒有足夠的金錢")
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

            foodItems.add(FoodItem(photo, name, foodItemCounts[i], price))
        }
        array.recycle()
    }

    private fun cost(price: Int) {
        var petStoreCoin = findViewById<TextView>(R.id.petStorePetCoinTextView)
        coin -= price
        petStoreCoin.text = coin.toString()
    }

    private fun addCount(name: String, textView: TextView) {
        for(i in 0..(foodItemCounts.size - 1)) {
            if(foodItems[i].name == name) {
                foodItemCounts[i]++
                foodItems[i].count++
                textView.text = foodItems[i].count.toString()
                break
            }
        }
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.storeReturnButton)

        returnButton.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("coin", coin)
            bundle.putIntArray("foodItemCounts", foodItemCounts)
            setResult(RESULT_OK, Intent().putExtras(bundle))
            finish()
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}
package com.example.a202109kotlinfinalporject.activity

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.a202109kotlinfinalporject.MyDBHelper
import com.example.a202109kotlinfinalporject.R

class RecordsActivity : AppCompatActivity() {

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var toastQueryString: String

    private var costTypes: ArrayList<String> = ArrayList()
    private var names: ArrayList<String> = ArrayList()
    private lateinit var prices: IntArray

    private var incomeTotal: Int = 0
    private var repenseTotoal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        getIntentData()

        var totalIncomeTextView = findViewById<TextView>(R.id.totalIncomeTextView)
        var totalRespenseTextView = findViewById<TextView>(R.id.totalRepenseTextView)
        var totalCountTextView = findViewById<TextView>(R.id.totalCountTextView)



        val listView = findViewById<ListView>(R.id.recordsListView)
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, items)

        setIncomeTotal()
        setRepenseTotal()

        totalIncomeTextView.text = "總收入:${incomeTotal}"
        totalRespenseTextView.text = "總支出:${repenseTotoal}"
        totalCountTextView.text = "損益:${getTotalCount()}"

        setListener()
        getItem()
        listView.adapter = adapter
    }

    private fun setIncomeTotal() {
        for(i in 0 until prices.size) {
            if(costTypes[i] == "收入") incomeTotal += prices[i]
        }

        Log.i("RecordsActivity", "$incomeTotal")
    }

    private fun setRepenseTotal() {

        for(i in 0 until prices.size) {
            //Log.i("RecordsActivity", (names[i] == "支出").toString())
            if(costTypes[i] == "支出") repenseTotoal += prices[i]
        }
        Log.i("RecordsActivity", "$repenseTotoal")
    }

    private fun getTotalCount(): Int {
        return incomeTotal - repenseTotoal
    }

    private fun getIntentData() {
        intent?.extras?.let{
            costTypes = it.getStringArrayList("costType") as ArrayList<String>
            names = it.getStringArrayList("name") as ArrayList<String>
            prices = it.getIntArray("price")!!
        }
    }

    private fun getItem() {
        items.clear()
        setItems()
        adapter.notifyDataSetChanged()
    }

    private fun setItems() {
        for(i in 0 until names.size) {
            items.add("${costTypes[i]}\t\t\t\t\t\t\t\t\t\t\t\t\t\t ${names[i]}\t\t\t\t\t\t\t\t\t" +
                    " ${prices[i]}元")
        }
    }

    private fun setListener() {
        setReturnButton()
    }

    private fun setReturnButton() {
        var returnButton = findViewById<Button>(R.id.recordsReturnButton)

        returnButton.setOnClickListener {
            finish()
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()

}
package com.example.a202109kotlinfinalporject.activity

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.a202109kotlinfinalporject.MyDBHelper
import com.example.a202109kotlinfinalporject.R

class RecordsActivity : AppCompatActivity() {

    private var items: ArrayList<String> = ArrayList()
    private lateinit var sqLiteDatabase: SQLiteDatabase
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var toastQueryString: String

    private var costTypes: ArrayList<String> = ArrayList()
    private var names: ArrayList<String> = ArrayList()
    private lateinit var prices: IntArray


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        val listView = findViewById<ListView>(R.id.recordsListView)
        sqLiteDatabase = MyDBHelper(this).writableDatabase
        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, items)

        intent?.extras?.let{
            costTypes = it.getStringArrayList("costType") as ArrayList<String>
            names = it.getStringArrayList("name") as ArrayList<String>
            prices = it.getIntArray("price")!!
        }


        setListener()

        getSQLData()
        listView.adapter = adapter
    }

    private fun getSQLData() {
        toastQueryString = "select * from recordsTable"
        val c = sqLiteDatabase.rawQuery(toastQueryString, null)
        c.moveToFirst() //從第一筆開始輸出
        items.clear() //清空舊資料
        setItems()
        items.add("支出\t\t\t\t\t\t\t\t\t\t\t\t\t\t 名稱1:\t\t\t\t\t\t\t\t\t" +
                " 1000")//TODO:Test
        items.add("支出\t\t\t\t\t\t\t\t\t\t\t\t\t\t 名稱2:\t\t\t\t\t\t\t\t\t" +
                " 1000")//TODO:Test
        items.add("支出\t\t\t\t\t\t\t\t\t\t\t\t\t\t 名稱3:\t\t\t\t\t\t\t\t\t" +
                " 1000")//TODO:Test
        showToast("共有${c.count}筆資料")
        /*TODO:for (i in 0 until c.count) {
            //加入新資料
            items.add(":${c.getString(1)}\t\t\t\t\t\t\t\t\t\t\t\t\t\t :${c.getString(2)}\t\t\t\t\t\t\t\t\t" +
                    " $:${c.getInt(3)}")
            c.moveToNext() //移動到下一筆
        }*/
        adapter.notifyDataSetChanged() //更新列表資料
        c.close() //關閉 Cursor
    }

    private fun setItems() {
        for(i in 0 until names.size) {
            items.add(":${costTypes[i]}\t\t\t\t\t\t\t\t\t\t\t\t\t\t :${names[i]}\t\t\t\t\t\t\t\t\t" +
                    " $:${prices[i]}")
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
package com.example.a202109kotlinfinalporject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
//自訂建構子並繼承 SQLiteOpenHelper 類別
class MyDBHelper (
    context: Context,
    name: String = database,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = v
) : SQLiteOpenHelper(context, name, factory, version) {
    companion object {
        private const val database = "myDatabase" //資料庫名稱
        private const val v = 1 //資料庫版本
    }
    override fun onCreate(db: SQLiteDatabase) {
        //建立 myTable(雖然想用老師的預設名稱，但這個Database不給用阿阿) 資料表，PRIMARY KEY為唯一值，不能重複，就連測試結束都不會消失
        db.execSQL(
            "CREATE TABLE myTable(number integer PRIMARY KEY, name text NOT NULL, phone integer NOT NULL, address text NOT NULL)"
        )
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        //升級資料庫版本時，刪除舊資料表，並重新執行 onCreate()，建立新資料表
        db.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }
}
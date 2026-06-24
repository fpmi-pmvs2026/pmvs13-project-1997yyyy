package com.example.weatherappproject // ⚠️ 确保包名一致

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CityDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_NAME = "weather_app.db"
        const val DB_VERSION = 1
        const val TABLE_NAME = "favorite_cities"
        const val COL_ID = "id"
        const val COL_CITY_NAME = "city_name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_CITY_NAME TEXT UNIQUE NOT NULL
            )
        """
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // 添加收藏城市
    fun addCity(cityName: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues().apply {
            put(COL_CITY_NAME, cityName)
        }
        // 如果城市已存在 (UNIQUE 约束)，insert 会返回 -1
        val result = db.insert(TABLE_NAME, null, cv)
        return result != -1L
    }

    // 获取所有收藏城市
    fun getAllCities(): List<String> {
        val cities = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COL_CITY_NAME), null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                cities.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return cities
    }

    // 删除收藏城市
    fun removeCity(cityName: String) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COL_CITY_NAME = ?", arrayOf(cityName))
    }
}
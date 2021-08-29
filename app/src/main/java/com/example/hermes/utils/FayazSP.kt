package com.example.pin_1.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by p32929 on 7/17/2018.
 */
object FayazSP {
    var sharedPreferences: SharedPreferences? = null

    //
    fun init(context: Context): SharedPreferences? {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("Lockscreen", Context.MODE_PRIVATE)
        }
        return sharedPreferences
    }

    //
    fun put(title: String?, value: Boolean) {
        sharedPreferences!!.edit().putBoolean(title, value).apply()
    }

    fun put(title: String?, value: Float) {
        sharedPreferences!!.edit().putFloat(title, value).apply()
    }

    fun put(title: String?, value: Int) {
        sharedPreferences!!.edit().putInt(title, value).apply()
    }

    fun put(title: String?, value: Long) {
        sharedPreferences!!.edit().putLong(title, value).apply()
    }

    fun put(title: String?, value: String?) {
        sharedPreferences!!.edit().putString(title, value).apply()
    }

    //
    fun getBoolean(title: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(title, defaultValue)
    }

    fun getFloat(title: String?, defaultValue: Float): Float {
        return sharedPreferences!!.getFloat(title, defaultValue)
    }

    fun getInt(title: String?, defaultValue: Int): Int {
        return sharedPreferences!!.getInt(title, defaultValue)
    }

    fun getLong(title: String?, defaultValue: Long): Long {
        return sharedPreferences!!.getLong(title, defaultValue)
    }

    fun getString(title: String?, defaultValue: String?): String? {
        return sharedPreferences!!.getString(title, defaultValue)
    }

    //
    fun clearAll() {
        sharedPreferences!!.edit().clear().commit()
    }
}
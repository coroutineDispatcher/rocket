package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences


class Rocket {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    companion object {
        fun launch(context: Context, fileName: String): Rocket = Rocket().apply {
            sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            sharedPreferencesEditor = sharedPreferences.edit()
        }
    }

    fun readString(key: String, value: String = ""): String? = sharedPreferences.getString(key, value)

    fun writeString(key: String, value: String) {
        sharedPreferencesEditor.putString(key, value).apply()
    }

    fun readInt(key: String, value: Int = 0): Int = sharedPreferences.getInt(key, value)

    fun writeInt(key: String, value: Int) {
        sharedPreferencesEditor.putInt(key, value).apply()
    }

    fun readBoolean(key: String, value: Boolean = false): Boolean = sharedPreferences.getBoolean(key, value)

    fun writeBoolean(key: String, value: Boolean) {
        sharedPreferencesEditor.putBoolean(key, value).apply()
    }

    fun readFloat(key: String, value: Float = 0f) = sharedPreferences.getFloat(key, value)

    fun writeFloat(key: String, value: Float) {
        sharedPreferencesEditor.putFloat(key, value).apply()
    }

    fun readLong(key: String, value: Long = 0L) = sharedPreferences.getLong(key, value)

    fun writeLong(key: String, value: Long) {
        sharedPreferencesEditor.putLong(key, value).apply()
    }

    fun type(key: String, value: MutableSet<String>) {
        sharedPreferencesEditor.putStringSet(key, value).apply()
    }

    fun crash() {
        sharedPreferencesEditor.clear().apply()
    }

    fun drop(key: String) {
        sharedPreferencesEditor.remove(key).apply()
    }
}
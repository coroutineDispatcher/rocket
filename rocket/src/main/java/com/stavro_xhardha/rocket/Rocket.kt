package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Rocket {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    companion object {
        fun launch(context: Context, fileName: String, mode: Int = Context.MODE_PRIVATE): Rocket = Rocket().apply {
            sharedPreferences = context.getSharedPreferences(fileName, mode)
            sharedPreferencesEditor = sharedPreferences.edit()
        }
    }

    suspend fun readString(key: String): String? = withContext(Dispatchers.IO) { sharedPreferences.getString(key, "") }

    suspend fun writeString(key: String, value: String) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putString(key, value).apply()
        }
    }

    suspend fun readInt(key: String): Int = withContext(Dispatchers.IO) { sharedPreferences.getInt(key, 0) }

    suspend fun writeInt(key: String, value: Int) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putInt(key, value).apply()
        }
    }

    suspend fun readBoolean(key: String): Boolean =
        withContext(Dispatchers.IO) { sharedPreferences.getBoolean(key, false) }

    suspend fun writeBoolean(key: String, value: Boolean) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putBoolean(key, value).apply()
        }
    }

    suspend fun readFloat(key: String) = withContext(Dispatchers.IO) { sharedPreferences.getFloat(key, 0f) }

    suspend fun writeFloat(key: String, value: Float) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putFloat(key, value).apply()
        }
    }

    suspend fun readLong(key: String) = withContext(Dispatchers.IO) { sharedPreferences.getLong(key, 0L) }

    suspend fun writeLong(key: String, value: Long) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putLong(key, value).apply()
        }
    }

    suspend fun writeSet(key: String, value: MutableSet<String>) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putStringSet(key, value).apply()
        }
    }

    suspend fun readSet(key: String) =
        withContext(Dispatchers.IO) { sharedPreferences.getStringSet(key, mutableSetOf()) }

    suspend fun crash() {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.clear().apply()
        }
    }

    suspend fun drop(key: String) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.remove(key).apply()
        }
    }
}

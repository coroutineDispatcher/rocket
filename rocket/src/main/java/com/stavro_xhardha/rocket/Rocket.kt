package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Rocket private constructor() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    companion object {
        /**
         * Method for starting the Rocket's instance.
         * @param context context of the Singleton you are instantiating it
         * @param fileName file name of the SharedPreferences you wish to provide
         * @param mode, the way you want to access it.
         * @see Context for other modes
         */
        fun launch(context: Context, fileName: String, mode: Int = Context.MODE_PRIVATE): Rocket = Rocket().apply {
            sharedPreferences = context.getSharedPreferences(fileName, mode)
            sharedPreferencesEditor = sharedPreferences.edit()
        }
    }

    /**
     * Get's a String from SharedPreferences
     * @param key the key provided to find the stored value
     * @return the data of type String if found if not returns an empty String
     * @throws ClassCastException if the key is found but is not a String
     */
    suspend fun readString(key: String): String = withContext(Dispatchers.IO) {
        sharedPreferences.getString(key, "")
            ?: throw ClassCastException(" The key exists, but it's not of type String ")
    }

    /**
     * Writes a String to SharedPreferences
     * @param key: The key you wish to save the value
     * @param value: The value you provide for the given key
     */
    suspend fun writeString(key: String, value: String) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putString(key, value).apply()
        }
    }

    /**
     * Reads an Int from SharedPreferences. If no value is found the method returns 0 by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Int
     */
    suspend fun readInt(key: String): Int = withContext(Dispatchers.IO) { sharedPreferences.getInt(key, 0) }

    /**
     * Writes a Int to SharedPreferences
     * @param key: The key you wish to save the value
     * @param value: The value you provide for the given key
     */
    suspend fun writeInt(key: String, value: Int) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putInt(key, value).apply()
        }
    }

    /**
     * Reads an Boolean from SharedPreferences. If no value is found the method returns false by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Boolean
     */
    suspend fun readBoolean(key: String): Boolean =
        withContext(Dispatchers.IO) { sharedPreferences.getBoolean(key, false) }

    /**
     * Writes a Boolean to SharedPreferences
     * @param key: The key you wish to save the value
     * @param value: The value you provide for the given key
     */
    suspend fun writeBoolean(key: String, value: Boolean) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putBoolean(key, value).apply()
        }
    }

    /**
     * Reads an Float from SharedPreferences. If no value is found the method returns 0f by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Float
     */
    suspend fun readFloat(key: String): Float = withContext(Dispatchers.IO) { sharedPreferences.getFloat(key, 0f) }

    /**
     * Writes a Float to SharedPreferences
     * @param key: The key you wish to save the value
     * @param value: The value you provide for the given key
     */
    suspend fun writeFloat(key: String, value: Float) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putFloat(key, value).apply()
        }
    }

    /**
     * Reads an Long from SharedPreferences. If no value is found the method returns 0L by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Long
     */
    suspend fun readLong(key: String): Long = withContext(Dispatchers.IO) { sharedPreferences.getLong(key, 0L) }

    /**
     * Writes a Long to SharedPreferences
     * @param key: The key you wish to save the value
     * @param value: The value you provide for the given key
     */
    suspend fun writeLong(key: String, value: Long) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putLong(key, value).apply()
        }
    }

    /**
     * Reads an Long from SharedPreferences. If no value is found the method returns 0L by default
     * @param key: the key provided to find the stored value
     * @return MutableSet<String> the set saved to SharedPreferences
     * @throws ClassCastException
     */
    suspend fun readSet(key: String): MutableSet<String> =
        withContext(Dispatchers.IO) {
            sharedPreferences.getStringSet(key, mutableSetOf())
                ?: throw ClassCastException("The key exists, but it's not of type StringSet")
        }

    /**
     * Writes a MutableSet<String> to SharedPreferences
     * @param key: The key you wish to save the value
     * @param value: The value you provide for the given key
     */
    suspend fun writeSet(key: String, value: MutableSet<String>) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.putStringSet(key, value).apply()
        }
    }

    /**
     * Clears all values saved to Shared Preferences
     */
    suspend fun crash() {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.clear().apply()
        }
    }

    /**
     * Removes a custom value by providing a key
     * @param key: The key provided to delete its value
     */
    suspend fun drop(key: String) {
        withContext(Dispatchers.IO) {
            sharedPreferencesEditor.remove(key).apply()
        }
    }
}

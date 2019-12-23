package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * This is the main and only true class of this small project
 */
class Rocket private constructor() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    companion object {
        /**
         * Method for starting the Rocket's instance.
         * @param context context of the Singleton you are instantiating it
         * @param fileName file name of the SharedPreferences you wish to provide
         * @param mode, the way you want to access it.
         */
        fun launch(context: Context, fileName: String, mode: Int = Context.MODE_PRIVATE): Rocket =
            Rocket().apply {
                sharedPreferences = context.getSharedPreferences(fileName, mode)
                sharedPreferencesEditor = sharedPreferences.edit()
            }
    }

    /**
     * Get's a String from SharedPreferences
     * @param key the key provided to find the stored value
     * @return the data of type String if found if not returns an empty String
     * @throws java.lang.ClassCastException if the key is found but is not a String
     */
    @Throws(java.lang.ClassCastException::class)
    fun readString(key: String): String = sharedPreferences.getString(key, "")
        ?: throw java.lang.ClassCastException("The key exists, but its' value not of type String")

    /**
     * Get's a String from SharedPreferences
     * @param key the key provided to find the stored value
     * @return the data of type Flow<String> if found if not returns an empty String by default
     * @throws IllegalStateException when the method inside throws an error of type java.lang.ClassCastException
     * @see readString
     */
    @Throws(IllegalStateException::class)
    fun readStringAsFlow(key: String): Flow<String> = flow {
        val writtenString = readString(key)
        emit(writtenString)
    }.catch {
        error("The key exists, but its' value not of type String")
    }

    /**
     * Writes a String to SharedPreferences
     * @param key: The key you wish to save to retrieve the value with
     * @param value: The value you provide for the given key
     */
    fun writeString(key: String, value: String) {
        sharedPreferencesEditor.putString(key, value).apply()
    }

    /**
     * Reads an Int from SharedPreferences. If no value is found the method returns 0 by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Int
     */
    fun readInt(key: String): Int = sharedPreferences.getInt(key, 0)

    /**
     * Reads an Int from SharedPreferences. If no value is found the method emits 0 by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Flow<Int>
     */
    fun readIntAsFlow(key: String): Flow<Int> = flow {
        val writtenInteger = readInt(key)
        emit(writtenInteger)
    }

    /**
     * Writes a Int to SharedPreferences
     * @param key: The key you wish to save to retrieve the value with
     * @param value: The value you provide for the given key
     */
    fun writeInt(key: String, value: Int) {
        sharedPreferencesEditor.putInt(key, value).apply()
    }

    /**
     * Reads an Boolean from SharedPreferences. If no value is found the method returns false by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Boolean
     */
    fun readBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    /**
     * Reads a Boolean from SharedPreferences but value is retrieved as a Flow<Boolean>
     * @return Flow<Boolean>
     */
    fun readBooleanAsFlow(key: String): Flow<Boolean> = flow {
        val writtenBooleanValue = readBoolean(key)
        emit(writtenBooleanValue)
    }

    /**
     * Writes a Boolean to SharedPreferences
     * @param key: The key you wish to save to retrieve the value with
     * @param value: The value you provide for the given key
     */
    fun writeBoolean(key: String, value: Boolean) {
        sharedPreferencesEditor.putBoolean(key, value).apply()
    }

    /**
     * Reads an Float from SharedPreferences. If no value is found the method returns 0f by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Float
     */
    fun readFloat(key: String): Float = sharedPreferences.getFloat(key, 0f)

    /**
     * Reads a Float from SharedPreferences as Flow<Float>
     * @return Flow<Float>
     * @see readFloat
     */
    fun readFloatAsFLow(key: String): Flow<Float> = flow {
        val writtenFloat = readFloat(key)
        emit(writtenFloat)
    }

    /**
     * Writes a Float to SHaredPreferences
     * @param key: The key you wish to save to retrieve the value with
     * @param value: The value you provide for the given key
     */
    fun writeFloat(key: String, value: Float) {
        sharedPreferencesEditor.putFloat(key, value).apply()
    }

    /**
     * Reads an Long from SharedPreferences. If no value is found the method returns 0L by default
     * @param key: the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Long
     */
    fun readLong(key: String): Long = sharedPreferences.getLong(key, 0L)

    /**
     * Reads a Long from SharedPreferences as Flow<Long>
     * @return Flow<Long>
     * @see readLong
     */
    fun readLongAsFlow(key: String): Flow<Long> = flow {
        val writtenLong = readLong(key)
        emit(writtenLong)
    }

    /**
     * Writes a Long value to SharedPreferences
     * @param key: The key you wish to save to retrieve the value with
     * @param value: The value you provide for the given key
     */
    fun writeLong(key: String, value: Long) {
        sharedPreferencesEditor.putLong(key, value).apply()
    }

    /**
     * Reads an Long from SharedPreferences. If no value is found the method returns 0L by default
     * @param key: the key provided to find the stored value
     * @return MutableSet<String> the set saved to SharedPreferences
     * @throws java.lang.ClassCastException
     */
    @Throws(java.lang.ClassCastException::class)
    fun readSet(key: String): MutableSet<String> =
        sharedPreferences.getStringSet(key, mutableSetOf())
            ?: throw java.lang.ClassCastException("The key exists, but its' value is not of type StringSet")


    /**
     * Reads a Set<String> from SharedPreferences as Flow<String>
     * @return Flow<String>
     * @see readSet
     * @throws java.lang.ClassCastException when a value with the corresponding key is found but is not of type Set<String>
     */
    @ExperimentalCoroutinesApi
    @Throws(java.lang.ClassCastException::class)
    fun readSetAsFlow(key: String): Flow<String> = readSet(key).asFlow()
        .catch {
            error("The key exists, but its' value is not of type StringSet")
        }

    /**
     * Writes a MutableSet<String> to SharedPreferences
     * @param key: The key you wish to save to retrieve the value with
     * @param value: The value you provide for the given key
     */
    fun writeSet(key: String, value: MutableSet<String>) {
        sharedPreferencesEditor.putStringSet(key, value).apply()
    }

    /**
     * Clears all values saved to Shared Preferences
     */
    fun crash() {
        sharedPreferencesEditor.clear().apply()
    }

    /**
     * Removes a custom value by providing a vararg of keys
     * @param key: The keys provided to delete its value
     */
    fun drop(vararg key: String) {
        key.forEach {
            sharedPreferencesEditor.remove(it).apply()
        }
    }

    /**
     * @see drop
     * Runs inside a coroutine
     */
    suspend fun dropSuspended(vararg key: String) {
        withContext(Dispatchers.IO) {
            key.forEach {
                sharedPreferencesEditor.remove(it).apply()
            }
        }
    }
}

package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * This is the main and only true class of this small project
 */
@OptIn(ExperimentalCoroutinesApi::class)
class Rocket private constructor() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    companion object {
        /**
         * Method for starting the Rocket's instance.
         * @param [context] context of the Singleton you are instantiating it
         * @param [fileName] file name of the SharedPreferences you wish to provide
         * @param [mode] the way you want to access it.
         */
        fun launch(context: Context, fileName: String, mode: Int = Context.MODE_PRIVATE): Rocket =
            Rocket().apply {
                sharedPreferences = context.getSharedPreferences(fileName, mode)
                sharedPreferencesEditor = sharedPreferences.edit()
            }
    }

    /**
     * Reads a String from SharedPreferences
     * @param [key] the key provided to find the stored value
     * @return [String] the data of type String if found if not returns an empty String
     * @throws [java.lang.ClassCastException] if the key is found but is not a String
     */
    @Throws(java.lang.ClassCastException::class)
    fun readString(key: String, defaultStringValue: String = ""): String =
        sharedPreferences.getString(key, defaultStringValue)
            ?: throw java.lang.ClassCastException("The key exists, but its' value not of type String")

    /**
     * Flow support for getting a String value from SharedPreferences
     * @param [key] the key provided to find the stored value
     * @return [Flow] the data of type Flow<String> if found if not returns an empty String by default
     * @throws [IllegalStateException] when the method inside throws an error of type java.lang.ClassCastException
     * @see [readString]
     */
    @Throws(IllegalStateException::class)
    fun readStringAsFlow(key: String, defaultStringValue: String = ""): Flow<String> = flow {
        val writtenString = readString(key, defaultStringValue)
        emit(writtenString)
    }.catch {
        error("The key exists, but its' value not of type String")
    }

    /**
     * Writes a String to SharedPreferences
     * @param [key] The key you wish to save to retrieve the value with
     * @param [value] The value you provide for the given key
     */
    fun writeString(key: String, value: String) {
        sharedPreferencesEditor.putString(key, value).apply()
    }

    /**
     * Reads an Int from SharedPreferences. If no value is found the method returns 0 by default
     * @param [key] the key provided to find the stored value
     * @return [Int] the value saved to SharedPreferences of type Int
     */
    fun readInt(key: String, defaultIntValue: Int = 0): Int =
        sharedPreferences.getInt(key, defaultIntValue)

    /**
     * Flow support for getting and Int from SharedPreferences. If no value is found the method emits 0 by default
     * @param [key] the key provided to find the stored value
     * @return [Flow] the value saved to SharedPreferences of type Flow<Int>
     */
    fun readIntAsFlow(key: String, defaultIntValue: Int = 0): Flow<Int> = flow {
        val writtenInteger = readInt(key, defaultIntValue)
        emit(writtenInteger)
    }

    /**
     * Writes a Int to SharedPreferences
     * @param [key] The key you wish to save to retrieve the value with
     * @param [value] The value you provide for the given key
     */
    fun writeInt(key: String, value: Int) {
        sharedPreferencesEditor.putInt(key, value).apply()
    }

    /**
     * Reads an Boolean from SharedPreferences. If no value is found the method returns false by default
     * @param [key] the key provided to find the stored value
     * @return [Boolean] the value saved to SharedPreferences of type Boolean
     */
    fun readBoolean(key: String, defaultBooleanValue: Boolean = false): Boolean =
        sharedPreferences.getBoolean(key, defaultBooleanValue)

    /**
     * Flow support for getting a Boolean from SharedPreferences
     * @see [readBoolean]
     * @return[Flow] the value saved to SharedPreferences of type Flow<Boolean>
     */
    fun readBooleanAsFlow(key: String, defaultBooleanValue: Boolean = false): Flow<Boolean> = flow {
        val writtenBooleanValue = readBoolean(key, defaultBooleanValue)
        emit(writtenBooleanValue)
    }

    /**
     * Writes a Boolean to SharedPreferences
     * @param [key] The key you wish to save to retrieve the value with
     * @param [value] The value you provide for the given key
     */
    fun writeBoolean(key: String, value: Boolean) {
        sharedPreferencesEditor.putBoolean(key, value).apply()
    }

    /**
     * Reads an Float from SharedPreferences. If no value is found the method returns 0f by default
     * @param [key] the key provided to find the stored value
     * @return the value saved to SharedPreferences of type Float
     */
    fun readFloat(key: String, defaultFloatValue: Float = 0f): Float =
        sharedPreferences.getFloat(key, defaultFloatValue)

    /**
     * Flow support for getting a Float from SharedPreferences
     * @return [Flow] the value saved to SharedPreferences of type Flow<Float>
     * @see [readFloat]
     */
    fun readFloatAsFLow(key: String, defaultFloatValue: Float = 0f): Flow<Float> = flow {
        val writtenFloat = readFloat(key, defaultFloatValue)
        emit(writtenFloat)
    }

    /**
     * Writes a Float to SharedPreferences
     * @param [key] The key you wish to save to retrieve the value with
     * @param [value] The value you provide for the given key
     */
    fun writeFloat(key: String, value: Float) {
        sharedPreferencesEditor.putFloat(key, value).apply()
    }

    /**
     * Reads an Long from SharedPreferences. If no value is found the method returns 0L by default
     * @param [key] the key provided to find the stored value
     * @return [Long] the value saved to SharedPreferences of type Long
     */
    fun readLong(key: String, defaultLongValue: Long = 0L): Long =
        sharedPreferences.getLong(key, defaultLongValue)

    /**
     * Flow support for getting a Log value from SharedPreferences
     * @return [Flow] the value saved to SharedPreferences of type Flow<Long>
     * @see [readLong]
     */
    fun readLongAsFlow(key: String, defaultLongValue: Long = 0L): Flow<Long> = flow {
        val writtenLong = readLong(key, defaultLongValue)
        emit(writtenLong)
    }

    /**
     * Writes a Long value to SharedPreferences
     * @param [key] The key you wish to save to retrieve the value with
     * @param [value] The value you provide for the given key
     */
    fun writeLong(key: String, value: Long) {
        sharedPreferencesEditor.putLong(key, value).apply()
    }

    /**
     * Reads an Long from SharedPreferences. If no value is found the method returns 0L by default
     * @param [key] the key provided to find the stored value
     * @return [MutableSet] the value saved to SharedPreferences of type MutableSet<String>
     * @throws [java.lang.ClassCastException]
     */
    @Throws(java.lang.ClassCastException::class)
    fun readSet(
        key: String,
        defaultSetValue: MutableSet<String> = mutableSetOf()
    ): MutableSet<String> =
        sharedPreferences.getStringSet(key, defaultSetValue)
            ?: throw java.lang.ClassCastException("The key exists, but its' value is not of type StringSet")

    /**
     * Flow support for getting a stream Flow<String>
     * @return [Flow] the value saved to SharedPreferences of type Flow<String>
     * @see [readSet]
     * @throws [java.lang.ClassCastException] when a value with the corresponding key is found but is not of type Set<String>
     */
    @ExperimentalCoroutinesApi
    @Throws(java.lang.ClassCastException::class)
    fun readSetAsFlow(key: String): Flow<String> = readSet(key).asFlow()
        .catch {
            error("The key exists, but its' value is not of type StringSet")
        }

    /**
     * Writes a MutableSet<String> to SharedPreferences
     * @param [key] The key you wish to save to retrieve the value with
     * @param [value] The value you provide for the given key
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
     * Removes a number of saved values found by a key
     * @param [key] The keys provided to delete its value
     */
    fun drop(vararg key: String) {
        key.forEach {
            sharedPreferencesEditor.remove(it).apply()
        }
    }
}
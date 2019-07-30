package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class RocketTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var rocket: Rocket
    private val DEFAULT_TEST_KEY = "rocket_is_awesome"

    @Before
    fun setUp() {
        sharedPreferences = mock()
        sharedPreferencesEditor = mock()
        val context = mock<Context>()
        `when`(context.getSharedPreferences(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(
            sharedPreferences
        )
        `when`(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        rocket = Rocket.launch(context, "FileName", Context.MODE_PRIVATE)
    }

    @After
    fun tearDown() {
        print(" TESTING IS FINISHED ")
    }

    @Test
    fun `when SharedPreferences return 0 so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(0)

            val valueReturned = rocket.readInt(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, 0)
        }
    }

    @Test
    fun `when SharedPreferences return a positive integer value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(4000)

            val valueReturned = rocket.readInt(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, 4000)
        }
    }

    @Test
    fun `when SharedPreferences return a negative integer value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(-1231)

            val valueReturned = rocket.readInt(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, -1231)
        }
    }

    @Test
    fun `when SharedPreferences return 0f so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(0f)

            val valueReturned = rocket.readFloat(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, 0f)
        }
    }

    @Test
    fun `when SharedPreferences return a positive float value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(4000f)

            val valueReturned = rocket.readFloat(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, 4000f)
        }
    }

    @Test
    fun `when SharedPreferences return a negative float value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(-1231f)

            val valueReturned = rocket.readFloat(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, -1231f)
        }
    }

    @Test
    fun `when SharedPreferences return 0L so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(0L)

            val valueReturned = rocket.readLong(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, 0L)
        }
    }

    @Test
    fun `when SharedPreferences return a positive long value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(4000L)

            val valueReturned = rocket.readLong(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, 4000L)
        }
    }

    @Test
    fun `when SharedPreferences return a negative long value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(-1231L)

            val valueReturned = rocket.readLong(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, -1231L)
        }
    }

    @Test
    fun `when SharedPreferences return true long value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getBoolean(DEFAULT_TEST_KEY, false)).thenReturn(false)

            val valueReturned = rocket.readBoolean(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, false)
        }
    }

    @Test
    fun `when SharedPreferences return true value so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getBoolean(DEFAULT_TEST_KEY, false)).thenReturn(true)

            val valueReturned = rocket.readBoolean(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, true)
        }
    }

    @Test
    fun `when SharedPreferences return emptySet so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf())).thenReturn(mutableSetOf())

            val valueReturned = rocket.readSet(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, mutableSetOf<String>())
        }
    }

    @Test
    fun `when SharedPreferences return filled set so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf())).thenReturn(
                mutableSetOf(
                    "Beni",
                    "Moza"
                )
            )

            val valueReturned = rocket.readSet(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, mutableSetOf("Beni", "Moza"))
        }
    }

    @Test
    fun `when SharedPreferences return empty String, so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenReturn("")

            val valueReturned = rocket.readString(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, "")
        }
    }

    @Test
    fun `when SharedPreferences returns a non empty String, so does rocket`() {
        runBlocking {
            `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenReturn("BENI")

            val valueReturned = rocket.readString(DEFAULT_TEST_KEY)

            assertEquals(valueReturned, "BENI")
        }
    }

    @Test(expected = ClassCastException::class)
    fun `when value exists in the SharedPreferences, but it is not a String`() {
        runBlocking {
            `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenThrow(ClassCastException::class.java)

            rocket.readString(DEFAULT_TEST_KEY)
        }
    }

    @Test(expected = ClassCastException::class)
    fun `when key is not a StringSet readSet method should throw ClassCastException`() {
        runBlocking {
            `when`(
                sharedPreferences.getStringSet(
                    DEFAULT_TEST_KEY,
                    mutableSetOf()
                )
            ).thenThrow(ClassCastException::class.java)

            rocket.readSet(DEFAULT_TEST_KEY)
        }
    }

    @Test
    fun `when writing String, rocket SharedPrefsEditor should execute`() {
        runBlocking {
            `when`(sharedPreferencesEditor.putString(DEFAULT_TEST_KEY, "Beni")).thenReturn(sharedPreferencesEditor)

            rocket.writeString(DEFAULT_TEST_KEY, "Beni")

            verify(sharedPreferencesEditor.putString(DEFAULT_TEST_KEY, "Beni")).apply()
        }
    }

    @Test
    fun `when writing Int, rocket SharedPrefsEditor should execute`() {
        runBlocking {
            `when`(sharedPreferencesEditor.putInt(DEFAULT_TEST_KEY, 1)).thenReturn(sharedPreferencesEditor)

            rocket.writeInt(DEFAULT_TEST_KEY, 1)

            verify(sharedPreferencesEditor.putInt(DEFAULT_TEST_KEY, 1)).apply()
        }
    }

    @Test
    fun `when writing Float, rocket SharedPrefsEditor should execute`() {
        runBlocking {
            `when`(sharedPreferencesEditor.putFloat(DEFAULT_TEST_KEY, 1f)).thenReturn(sharedPreferencesEditor)

            rocket.writeFloat(DEFAULT_TEST_KEY, 1f)

            verify(sharedPreferencesEditor.putFloat(DEFAULT_TEST_KEY, 1f)).apply()
        }
    }

    @Test
    fun `when writing Long, rocket SharedPrefsEditor should execute`() {
        runBlocking {
            `when`(sharedPreferencesEditor.putLong(DEFAULT_TEST_KEY, 1L)).thenReturn(sharedPreferencesEditor)

            rocket.writeLong(DEFAULT_TEST_KEY, 1L)

            verify(sharedPreferencesEditor.putLong(DEFAULT_TEST_KEY, 1L)).apply()
        }
    }

    @Test
    fun `when writing Boolean, rocket SharedPrefsEditor should execute`() {
        runBlocking {
            `when`(sharedPreferencesEditor.putBoolean(DEFAULT_TEST_KEY, true)).thenReturn(sharedPreferencesEditor)

            rocket.writeBoolean(DEFAULT_TEST_KEY, true)

            verify(sharedPreferencesEditor.putBoolean(DEFAULT_TEST_KEY, true)).apply()
        }
    }

    @Test
    fun `when writing StringSet, rocket SharedPrefsEditor should execute`() {
        runBlocking {
            `when`(sharedPreferencesEditor.putStringSet(DEFAULT_TEST_KEY, mutableSetOf("Beni", "Moza"))).thenReturn(
                sharedPreferencesEditor
            )

            rocket.writeSet(DEFAULT_TEST_KEY, mutableSetOf("Beni", "Moza"))

            verify(sharedPreferencesEditor.putStringSet(DEFAULT_TEST_KEY, mutableSetOf("Beni", "Moza"))).apply()
        }
    }
}
package com.stavro_xhardha.rocket

import android.content.Context
import android.content.SharedPreferences
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RocketTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private lateinit var rocket: Rocket
    private val DEFAULT_TEST_KEY = "rocket_is_awesome"
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        sharedPreferences = mock()
        sharedPreferencesEditor = mock()
        val context = mock<Context>()
        `when`(
            context.getSharedPreferences(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenReturn(
            sharedPreferences
        )
        `when`(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)

        rocket = Rocket.launch(context, "FileName", Context.MODE_PRIVATE)
    }

    @After
    fun tearDown() {
        print(" TESTING IS FINISHED ")
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when SharedPreferences return 0 so does rocket`() {
        `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(0)

        val valueReturned = rocket.readInt(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, 0)
    }

    @Test
    fun `when SharedPreferences return a positive integer value so does rocket`() {
        `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(4000)

        val valueReturned = rocket.readInt(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, 4000)
    }

    @Test
    fun `when SharedPreferences return a negative integer value so does rocket`() {
        `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(-1231)

        val valueReturned = rocket.readInt(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, -1231)
    }

    @Test
    fun `when SharedPreferences return 0 so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(0)

            rocket.readIntAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher).collect {
                assertEquals(it, 0)
            }
        }
    }

    @Test
    fun `when SharedPreferences return a positive integer value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(4000)

            rocket.readIntAsFlow(DEFAULT_TEST_KEY)
                .flowOn(testDispatcher)
                .collect {
                    assertEquals(it, 4000)
                }
        }
    }

    @Test
    fun `when SharedPreferences return a negative integer value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getInt(DEFAULT_TEST_KEY, 0)).thenReturn(-1231)

            rocket.readIntAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, -1231)
                }
        }
    }

    @Test
    fun `when SharedPreferences return 0f so does rocket`() {
        `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(0f)

        val valueReturned = rocket.readFloat(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, 0f)
    }

    @Test
    fun `when SharedPreferences return a positive float value so does rocket`() {
        `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(4000f)

        val valueReturned = rocket.readFloat(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, 4000f)
    }

    @Test
    fun `when SharedPreferences return a negative float value so does rocket`() {
        `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(-1231f)

        val valueReturned = rocket.readFloat(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, -1231f)
    }

    @Test
    fun `when SharedPreferences return 0f so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(0f)

            rocket.readFloatAsFLow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, 0f)
                }
        }
    }

    @Test
    fun `when SharedPreferences return a positive float value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(4000f)

            rocket.readFloatAsFLow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, 4000f)
                }
        }
    }

    @Test
    fun `when SharedPreferences return a negative float value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getFloat(DEFAULT_TEST_KEY, 0f)).thenReturn(-1231f)
            rocket.readFloatAsFLow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, -1231f)
                }
        }
    }

    @Test
    fun `when SharedPreferences return 0L so does rocket`() {
        `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(0L)

        val valueReturned = rocket.readLong(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, 0L)
    }

    @Test
    fun `when SharedPreferences return a positive long value so does rocket`() {
        `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(4000L)

        val valueReturned = rocket.readLong(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, 4000L)
    }

    @Test
    fun `when SharedPreferences return a negative long value so does rocket`() {
        `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(-1231L)

        val valueReturned = rocket.readLong(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, -1231L)
    }

    @Test
    fun `when SharedPreferences return 0L so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(0L)
            rocket.readLongAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, 0L)
                }
        }
    }

    @Test
    fun `when SharedPreferences return a positive long value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(4000L)
            rocket.readLongAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, 4000L)
                }
        }
    }

    @Test
    fun `when SharedPreferences return a negative long value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getLong(DEFAULT_TEST_KEY, 0L)).thenReturn(-1231L)
            rocket.readLongAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, -1231L)
                }
        }
    }

    @Test
    fun `when SharedPreferences return false value so does rocket`() {
        `when`(sharedPreferences.getBoolean(DEFAULT_TEST_KEY, false)).thenReturn(false)

        val valueReturned = rocket.readBoolean(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, false)
    }

    @Test
    fun `when SharedPreferences return true value so does rocket`() {
        `when`(sharedPreferences.getBoolean(DEFAULT_TEST_KEY, false)).thenReturn(true)

        val valueReturned = rocket.readBoolean(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, true)
    }

    @Test
    fun `when SharedPreferences return false value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getBoolean(DEFAULT_TEST_KEY, false)).thenReturn(false)

            rocket.readBooleanAsFlow(DEFAULT_TEST_KEY)
                .flowOn(testDispatcher).collect {
                    assertEquals(it, false)
                }
        }
    }

    @Test
    fun `when SharedPreferences return true value so does rocket but as flow`() {
        runBlockingTest {
            `when`(sharedPreferences.getBoolean(DEFAULT_TEST_KEY, false)).thenReturn(true)
            rocket.readBooleanAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher)
                .collect {
                    assertEquals(it, true)
                }
        }
    }

    @Test
    fun `when SharedPreferences return emptySet so does rocket`() {
        `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf())).thenReturn(
            mutableSetOf()
        )

        val valueReturned = rocket.readSet(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, mutableSetOf<String>())
    }

    @Test
    fun `when SharedPreferences return filled set so does rocket`() {
        `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf())).thenReturn(
            mutableSetOf(
                "Beni",
                "Moza"
            )
        )

        val valueReturned = rocket.readSet(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, mutableSetOf("Beni", "Moza"))
    }

    @Test
    fun `when SharedPreferences return emptySet so does rocket return flow of strings`() {
        runBlockingTest {
            `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf())).thenReturn(
                mutableSetOf()
            )

            rocket.readSetAsFlow(DEFAULT_TEST_KEY)
                .flowOn(testDispatcher)
                .collect {
                    assertEquals(it, mutableSetOf<String>())
                }
        }
    }

    @Test
    fun `when SharedPreferences return filled set so does rocket return flow of string`() {
        runBlockingTest {
            `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf())).thenReturn(
                mutableSetOf(
                    "Beni",
                    "Moza"
                )
            )

            val listOfData = mutableListOf<String>()

            rocket.readSetAsFlow(DEFAULT_TEST_KEY)
                .flowOn(testDispatcher)
                .onEach {
                    listOfData.add(it)
                }
                .collect()

            assertEquals(listOfData, mutableListOf("Beni", "Moza"))
        }
    }

    @Test(expected = java.lang.ClassCastException::class)
    fun `when SharedPreferences throw ClassCastException so does the readSetAsFlow method`() {
        runBlockingTest {
            `when`(sharedPreferences.getStringSet(DEFAULT_TEST_KEY, mutableSetOf()))
                .thenThrow(java.lang.ClassCastException::class.java)

            rocket.readSetAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher).collect {
                assertEquals(it, "The key exists, but its' value is not of type StringSet")
            }
        }
    }

    @Test
    fun `when SharedPreferences return empty String, so does rocket`() {
        `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenReturn("")

        val valueReturned = rocket.readString(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, "")
    }

    @Test
    fun `when SharedPreferences returns a non empty String, so does rocket`() {
        `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenReturn("BENI")

        val valueReturned = rocket.readString(DEFAULT_TEST_KEY)

        assertEquals(valueReturned, "BENI")
    }

    @Test(expected = ClassCastException::class)
    fun `when value exists in the SharedPreferences, but it is not a String`() {
        `when`(
            sharedPreferences.getString(
                DEFAULT_TEST_KEY,
                ""
            )
        ).thenThrow(ClassCastException::class.java)

        rocket.readString(DEFAULT_TEST_KEY)
    }

    @Test
    fun `when SharedPreferences return empty String, rocket returns the same but as flow of String`() {
        runBlockingTest {
            `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenReturn("")

            rocket.readStringAsFlow(DEFAULT_TEST_KEY)
                .flowOn(testDispatcher)
                .collect {
                    assertEquals(it, "")
                }
        }
    }

    @Test
    fun `when SharedPreferences returns a non empty String, rocket returns the same non empty flow of String`() {
        runBlockingTest {
            `when`(sharedPreferences.getString(DEFAULT_TEST_KEY, "")).thenReturn("BENI")

            rocket.readStringAsFlow(DEFAULT_TEST_KEY)
                .flowOn(testDispatcher)
                .collect {
                    assertEquals(it, "BENI")
                }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun `when value exists in the SharedPreferences, but it is not a String, than the Flow should throw exception`() {
        runBlockingTest {
            `when`(
                sharedPreferences.getString(
                    DEFAULT_TEST_KEY,
                    ""
                )
            ).thenThrow(ClassCastException::class.java)

            rocket.readStringAsFlow(DEFAULT_TEST_KEY).flowOn(testDispatcher).collect()
        }
    }

    @Test(expected = ClassCastException::class)
    fun `when key is not a StringSet readSet method should throw ClassCastException`() {
        `when`(
            sharedPreferences.getStringSet(
                DEFAULT_TEST_KEY,
                mutableSetOf()
            )
        ).thenThrow(ClassCastException::class.java)

        rocket.readSet(DEFAULT_TEST_KEY)
    }

    @Test
    fun `when writing String, rocket SharedPrefsEditor should execute`() {
        `when`(sharedPreferencesEditor.putString(DEFAULT_TEST_KEY, "Beni")).thenReturn(
            sharedPreferencesEditor
        )

        rocket.writeString(DEFAULT_TEST_KEY, "Beni")

        verify(sharedPreferencesEditor.putString(DEFAULT_TEST_KEY, "Beni")).apply()
    }

    @Test
    fun `when writing Int, rocket SharedPrefsEditor should execute`() {
        `when`(sharedPreferencesEditor.putInt(DEFAULT_TEST_KEY, 1)).thenReturn(
            sharedPreferencesEditor
        )

        rocket.writeInt(DEFAULT_TEST_KEY, 1)

        verify(sharedPreferencesEditor.putInt(DEFAULT_TEST_KEY, 1)).apply()
    }

    @Test
    fun `when writing Float, rocket SharedPrefsEditor should execute`() {
        `when`(sharedPreferencesEditor.putFloat(DEFAULT_TEST_KEY, 1f)).thenReturn(
            sharedPreferencesEditor
        )

        rocket.writeFloat(DEFAULT_TEST_KEY, 1f)

        verify(sharedPreferencesEditor.putFloat(DEFAULT_TEST_KEY, 1f)).apply()
    }

    @Test
    fun `when writing Long, rocket SharedPrefsEditor should execute`() {
        `when`(sharedPreferencesEditor.putLong(DEFAULT_TEST_KEY, 1L)).thenReturn(
            sharedPreferencesEditor
        )

        rocket.writeLong(DEFAULT_TEST_KEY, 1L)

        verify(sharedPreferencesEditor.putLong(DEFAULT_TEST_KEY, 1L)).apply()
    }

    @Test
    fun `when writing Boolean, rocket SharedPrefsEditor should execute`() {
        `when`(sharedPreferencesEditor.putBoolean(DEFAULT_TEST_KEY, true)).thenReturn(
            sharedPreferencesEditor
        )

        rocket.writeBoolean(DEFAULT_TEST_KEY, true)

        verify(sharedPreferencesEditor.putBoolean(DEFAULT_TEST_KEY, true)).apply()
    }

    @Test
    fun `when writing StringSet, rocket SharedPrefsEditor should execute`() {
        `when`(
            sharedPreferencesEditor.putStringSet(
                DEFAULT_TEST_KEY,
                mutableSetOf("Beni", "Moza")
            )
        ).thenReturn(
            sharedPreferencesEditor
        )

        rocket.writeSet(DEFAULT_TEST_KEY, mutableSetOf("Beni", "Moza"))

        verify(
            sharedPreferencesEditor.putStringSet(
                DEFAULT_TEST_KEY,
                mutableSetOf("Beni", "Moza")
            )
        ).apply()
    }

    @Test
    fun crashTest() {
        `when`(sharedPreferencesEditor.clear()).thenReturn(sharedPreferencesEditor)
        rocket.crash()
        verify(sharedPreferencesEditor.clear()).apply()
    }

    @Test
    fun dropSingleString() {
        `when`(sharedPreferencesEditor.remove(DEFAULT_TEST_KEY)).thenReturn(sharedPreferencesEditor)
        rocket.drop(DEFAULT_TEST_KEY)
        verify(sharedPreferencesEditor.remove(DEFAULT_TEST_KEY), times(1)).apply()
    }

    @Test
    fun dropMultipleStrings() {
        `when`(sharedPreferencesEditor.remove(DEFAULT_TEST_KEY)).thenReturn(sharedPreferencesEditor)
        rocket.drop(
            DEFAULT_TEST_KEY,
            DEFAULT_TEST_KEY,
            DEFAULT_TEST_KEY,
            DEFAULT_TEST_KEY,
            DEFAULT_TEST_KEY
        )
        verify(sharedPreferencesEditor.remove(DEFAULT_TEST_KEY), times(5)).apply()
    }
}
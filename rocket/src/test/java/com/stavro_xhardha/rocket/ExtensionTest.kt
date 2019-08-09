package com.stavro_xhardha.rocket

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class ExtensionTest {

    private lateinit var mockedRocket: Rocket
    private val TEST_STRING_KEY = "TEST"

    @Before
    fun setUp() {
        mockedRocket = mock()
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `isDefaultString extension should return true when value is empty`() {
        runBlocking {
            `when`(mockedRocket.readString(any())).thenReturn("")

            val isDefault = TEST_STRING_KEY.isDefaultString(mockedRocket)

            assertEquals(true, isDefault)
        }
    }

    @Test
    fun `isDefaultString extension should return false when value is not empty`() {
        runBlocking {
            `when`(mockedRocket.readString(any())).thenReturn("SOME_VALUE")

            val isDefault = TEST_STRING_KEY.isDefaultString(mockedRocket)

            assertEquals(false, isDefault)
        }
    }

    @Test
    fun `isDefaultInt extensions should return true when value is 0`() {
        runBlocking {
            `when`(mockedRocket.readInt(any())).thenReturn(0)

            val isDefault = TEST_STRING_KEY.isDefaultInt(mockedRocket)

            assertEquals(true, isDefault)
        }
    }

    @Test
    fun `isDefaultIt extension should return false when value is not 0`() {
        runBlocking {
            `when`(mockedRocket.readInt(any())).thenReturn(20)

            val isDefault = TEST_STRING_KEY.isDefaultInt(mockedRocket)

            assertEquals(false, isDefault)
        }
    }

    @Test
    fun `isDefaultFloat should return true when value is zero`() {
        runBlocking {
            `when`(mockedRocket.readFloat(any())).thenReturn(0f)

            val sDefault = TEST_STRING_KEY.isDefaultFloat(mockedRocket)

            assertEquals(true, sDefault)
        }
    }

    @Test
    fun `isDefaultFloat should return false when value is not 0`() {
        runBlocking {
            `when`(mockedRocket.readFloat(any())).thenReturn(10.121123f)

            val isDefault = TEST_STRING_KEY.isDefaultFloat(mockedRocket)

            assertEquals(false, isDefault)
        }
    }

    @Test
    fun `isDefaultBoolean should return false when value doesn not exist`() {
        runBlocking {
            `when`(mockedRocket.readBoolean(any())).thenReturn(false)

            val isDefault = TEST_STRING_KEY.isDefaultBoolean(mockedRocket)

            assertEquals(false, isDefault)
        }
    }

    @Test
    fun `isDefaultBoolean should return true when value exists`() {
        runBlocking {
            `when`(mockedRocket.readBoolean(any())).thenReturn(true)

            val isDefault = TEST_STRING_KEY.isDefaultBoolean(mockedRocket)

            assertEquals(true, isDefault)
        }
    }

    @Test
    fun `isDefaultLong should return true when value is 0`() {
        runBlocking {
            `when`(mockedRocket.readLong(any())).thenReturn(0L)

            val isDefault = TEST_STRING_KEY.isDefaultLong(mockedRocket)

            assertEquals(true, isDefault)
        }
    }

    @Test
    fun `isDefaultLong should return false when value is not 0`() {
        runBlocking {
            `when`(mockedRocket.readLong(any())).thenReturn(10101001L)

            val isDefault = TEST_STRING_KEY.isDefaultLong(mockedRocket)

            assertEquals(false, isDefault)
        }
    }
}
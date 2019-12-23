package com.stavro_xhardha.rocketdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.stavro_xhardha.rocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val rocket by lazy {
        Rocket.launch(this, SHARED_PREFERENCES_FILE_NAME)
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch(Dispatchers.IO) {
            rocket.writeBoolean(BOOLEAN_KEY, true)
            rocket.writeString(STRING_KEY, "Rocket is awesome")
            rocket.writeFloat(FLOAT_KEY, 1.0f)
            rocket.writeInt(INT_KEY, 10)
            rocket.writeLong(LONG_KEY, 20000010L)

            rocket.readStringAsFlow(STRING_KEY).collect {
                Log.d("ROCKET", "$it from Flow")
            }
            rocket.readBooleanAsFlow(BOOLEAN_KEY).collect {
                Log.d("ROCKET", "$it from Flow")
            }
            rocket.readLongAsFlow(LONG_KEY).collect {
                Log.d("ROCKET", "$it from Flow")
            }
            rocket.readFloatAsFLow(FLOAT_KEY).collect {
                Log.d("ROCKET", "$it from Flow")
            }
            rocket.readIntAsFlow(INT_KEY).collect {
                Log.d("ROCKET", "$it from Flow")
            }

            withContext(Dispatchers.Main) {
                Log.d("ROCKET", rocket.readString(STRING_KEY))
                Log.d("ROCKET", rocket.readBoolean(BOOLEAN_KEY).toString())
                Log.d("ROCKET", rocket.readFloat(FLOAT_KEY).toString())
                Log.d("ROCKET", rocket.readInt(INT_KEY).toString())
                Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

                rocket.drop(LONG_KEY)
                Log.d("ROCKET", rocket.readString(STRING_KEY))
                Log.d("ROCKET", rocket.readBoolean(BOOLEAN_KEY).toString())
                Log.d("ROCKET", rocket.readFloatAsFLow(FLOAT_KEY).toString())
                Log.d("ROCKET", rocket.readInt(INT_KEY).toString())
                Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

                rocket.crash()
                Log.d("ROCKET", rocket.readString(STRING_KEY))
                Log.d("ROCKET", rocket.readBoolean(BOOLEAN_KEY).toString())
                Log.d("ROCKET", rocket.readFloat(FLOAT_KEY).toString())
                Log.d("ROCKET", rocket.readInt(INT_KEY).toString())
                Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

                Log.d("ROCKET", STRING_KEY.isDefaultString(rocket).toString())
                Log.d("ROCKET", BOOLEAN_KEY.isDefaultBoolean(rocket).toString())
                Log.d("ROCKET", FLOAT_KEY.isDefaultFloat(rocket).toString())
                Log.d("ROCKET", INT_KEY.isDefaultInt(rocket).toString())
                Log.d("ROCKET", LONG_KEY.isDefaultLong(rocket).toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        completableJob.cancel()
    }
}

const val SHARED_PREFERENCES_FILE_NAME = "SharedPrefsFileName"
const val BOOLEAN_KEY = "boolean_key"
const val INT_KEY = "int_key"
const val FLOAT_KEY = "float_key"
const val STRING_KEY = "string_key"
const val LONG_KEY = "long_key"
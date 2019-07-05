package com.stavro_xhardha.rocketdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.stavro_xhardha.rocket.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val rocket by lazy {
        Rocket.launch(this, SHARED_PREFERENCES_FILE_NAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineScope.launch {
            rocket.writeBoolean(BOOLEAN_KEY, true)
            rocket.writeString(STRING_KEY, "Rocket is awesome")
            rocket.writeFloat(FLOAT_KEY, 1.0f)
            rocket.writeInt(INT_KEY, 10)
            rocket.writeLong(LONG_KEY, 20000010L)

            Log.d("ROCKET", rocket.readString(STRING_KEY))
            Log.d("ROCKET", rocket.readBoolean(BOOLEAN_KEY).toString())
            Log.d("ROCKET", rocket.readFloat(FLOAT_KEY).toString())
            Log.d("ROCKET", rocket.readInt(INT_KEY).toString())
            Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

            rocket.drop(LONG_KEY)
            Log.d("ROCKET", rocket.readString(STRING_KEY))
            Log.d("ROCKET", rocket.readBoolean(BOOLEAN_KEY).toString())
            Log.d("ROCKET", rocket.readFloat(FLOAT_KEY).toString())
            Log.d("ROCKET", rocket.readInt(INT_KEY).toString())
            Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

            rocket.crash()
            Log.d("ROCKET", rocket.readString(STRING_KEY))
            Log.d("ROCKET", rocket.readBoolean(BOOLEAN_KEY).toString())
            Log.d("ROCKET", rocket.readFloat(FLOAT_KEY).toString())
            Log.d("ROCKET", rocket.readInt(INT_KEY).toString())
            Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())
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
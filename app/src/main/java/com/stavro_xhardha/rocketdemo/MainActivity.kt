package com.stavro_xhardha.rocketdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.stavro_xhardha.rocket.*
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
            rocket.writeBooleanSuspended(BOOLEAN_KEY, true)
            rocket.writeStringSuspended(STRING_KEY, "Rocket is awesome")
            rocket.writeFloat(FLOAT_KEY, 1.0f)
            rocket.writeIntSuspended(INT_KEY, 10)
            rocket.writeLong(LONG_KEY, 20000010L)

            Log.d("ROCKET", rocket.readStringSuspended(STRING_KEY))
            Log.d("ROCKET", rocket.readBooleanSuspended(BOOLEAN_KEY).toString())
            Log.d("ROCKET", rocket.readFloatSuspended(FLOAT_KEY).toString())
            Log.d("ROCKET", rocket.readIntSuspended(INT_KEY).toString())
            Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

            rocket.drop(LONG_KEY)
            Log.d("ROCKET", rocket.readStringSuspended(STRING_KEY))
            Log.d("ROCKET", rocket.readBooleanSuspended(BOOLEAN_KEY).toString())
            Log.d("ROCKET", rocket.readFloatSuspended(FLOAT_KEY).toString())
            Log.d("ROCKET", rocket.readIntSuspended(INT_KEY).toString())
            Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

            rocket.crashSuspended()
            Log.d("ROCKET", rocket.readStringSuspended(STRING_KEY))
            Log.d("ROCKET", rocket.readBooleanSuspended(BOOLEAN_KEY).toString())
            Log.d("ROCKET", rocket.readFloatSuspended(FLOAT_KEY).toString())
            Log.d("ROCKET", rocket.readIntSuspended(INT_KEY).toString())
            Log.d("ROCKET", rocket.readLong(LONG_KEY).toString())

            Log.d("ROCKET", STRING_KEY.isDefaultString(rocket).toString())
            Log.d("ROCKET", BOOLEAN_KEY.isDefaultBoolean(rocket).toString())
            Log.d("ROCKET", FLOAT_KEY.isDefaultFloat(rocket).toString())
            Log.d("ROCKET", INT_KEY.isDefaultInt(rocket).toString())
            Log.d("ROCKET", LONG_KEY.isDefaultLong(rocket).toString())
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
package com.schulz.erica.weather

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : CoroutineScopeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var forecastButton = findViewById<Button>(R.id.forecast_button)


        forecastButton.setOnClickListener {


            var showForecastResults = Intent(applicationContext, ResultActivity::class.java)
            val editText = findViewById<EditText>(R.id.editText)

            showForecastResults.putExtra("zip_code",editText.text.toString())

            startActivity(showForecastResults)

        }

    }

    }


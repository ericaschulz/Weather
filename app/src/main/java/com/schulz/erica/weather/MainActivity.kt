package com.schulz.erica.weather

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var forecastButton = findViewById<Button>(R.id.forecast_button)


        forecastButton.setOnClickListener {
            var showForecastResults = Intent(applicationContext, ResultActivity::class.java)
            startActivity(showForecastResults)

        }

    }

    }


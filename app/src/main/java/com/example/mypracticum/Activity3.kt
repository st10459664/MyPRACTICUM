package za.ac.iie.practicum

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mypracticum.R

class Detailed : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val day = findViewById<TextView>(R.id.daytext)
        val minTemp = findViewById<TextView>(R.id.minTemptext)
        val maxTemp = findViewById<TextView>(R.id.maxTemptext)
        val weatherCondition = findViewById<TextView>(R.id.weatherConditiontext)
        val backButton = findViewById<Button>(R.id.backtext)

        val days = intent.getStringArrayListExtra("days")
        val minTemps = intent.getIntegerArrayListExtra("minTemps")
        val maxTemps = intent.getIntegerArrayListExtra("maxTemps")
        val weatherConditions = intent.getStringArrayListExtra("weatherConditions")
        val position = intent.getIntExtra("position", -1)

        if (days != null) {
            if (position != -1 && position < days.size) {
                day.text = " Week Day: ${days[position]}"
                minTemp.text = "Minimum Temperature: ${minTemps?.get(position)}°C"
                maxTemp.text = "Maximum Temperature: ${maxTemps?.get(position)}°C"
                weatherCondition.text = "Weather Condition: ${weatherConditions?.get(position)}"
            }
        }

        backButton.setOnClickListener {
            finish()
        }

        // Calculate weather data
        if (minTemps != null && maxTemps != null && weatherConditions != null) {
            calculateWeatherData(minTemps.toIntArray(), maxTemps.toIntArray(), weatherConditions.toTypedArray())
        }
    }

    private fun calculateWeatherData(minTemps: IntArray, maxTemps: IntArray, weatherConditions: Array<String>) {
        // Calculate average temperature
        val averageTemperature = calculateAverageTemperature(minTemps, maxTemps)

        // Calculate number of rainy days and sunny days
        val (rainyDays, sunnyDays) = calculateRainyAndSunnyDays(weatherConditions)

        // Display results
        println("Average Temperature: $averageTemperature")
        println("Number of Rainy Days: $rainyDays")
        println("Number of Sunny Days: $sunnyDays")
    }

    private fun calculateAverageTemperature(minTemps: IntArray, maxTemps: IntArray): Double {
        val totalMinTemp = minTemps.sum()
        val totalMaxTemp = maxTemps.sum()
        val averageMinTemp = totalMinTemp.toDouble() / minTemps.size
        val averageMaxTemp = totalMaxTemp.toDouble() / maxTemps.size
        return (averageMinTemp + averageMaxTemp) / 2
    }

    private fun calculateRainyAndSunnyDays(weatherConditions: Array<String>): Pair<Int, Int> {
        var rainyDays = 0
        var sunnyDays = 0
        for (condition in weatherConditions) {
            if (condition.equals("rainy", ignoreCase = true)) {
                rainyDays++
            } else if (condition.equals("sunny", ignoreCase = true)) {
                sunnyDays++
            }
        }
        return Pair(rainyDays, sunnyDays)
    }
}
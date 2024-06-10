package za.ac.iie.practicum

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mypracticum.R


class Weather : AppCompatActivity() {
    val days = mutableListOf<String>()
    val minTempsList = mutableListOf<Int>()
    val maxTempsList = mutableListOf<Int>()
    val weatherConditionsList = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextText4 = findViewById<EditText>(R.id.editTextText4)
        val editTextText = findViewById<EditText>(R.id.editTextText)
        val editTextText2 = findViewById<EditText>(R.id.editTextText2)
        val editTextText3 = findViewById<EditText>(R.id.editTextText3)
        val textView = findViewById<TextView>(R.id.textView)

        val button4 = findViewById<Button>(R.id.button4)
        val button3 = findViewById<Button>(R.id.button3)

        button4.setOnClickListener {
            val day = editTextText4.text.toString()
            val minimumTemp = editTextText.text.toString().toIntOrNull() ?: 0
            val maximumTemp = editTextText2.text.toString().toIntOrNull() ?: 0
            val weatherCondition = editTextText3.text.toString()

            days.add(day)
            minTempsList.add(minimumTemp)
            maxTempsList.add(maximumTemp)
            weatherConditionsList.add(weatherCondition)

            val intent = Intent(this, Activity::class.java).apply {
                putStringArrayListExtra("days", ArrayList(days))
                putIntegerArrayListExtra("minTemps", ArrayList(minTempsList))
                putIntegerArrayListExtra("maxTemps", ArrayList(maxTempsList))
                putStringArrayListExtra("weatherConditions", ArrayList(weatherConditionsList))
            }
            startActivity(intent)

            editTextText4.text.clear()
            editTextText.text.clear()
            editTextText2.text.clear()
            editTextText3.text.clear()

            calculateAverageTemperature(textView)
        }

        button4.setOnClickListener {
            finishAffinity() // This will close the app
        }
    }

    private fun calculateAverageTemperature(averageTempTextView: TextView) {
        if (minTempsList.isNotEmpty() && maxTempsList.isNotEmpty()) {
            val totalMinTemp = minTempsList.sum()
            val totalMaxTemp = maxTempsList.sum()
            val averageMinTemp = totalMinTemp / minTempsList.size
            val averageMaxTemp = totalMaxTemp / maxTempsList.size
            val averageTemp = (averageMinTemp + averageMaxTemp) / 2
            averageTempTextView.text = "Average Temperature: $averageTemp°C"
        }
    }
}
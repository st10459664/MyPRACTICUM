package za.ac.iie.practicum

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mypracticum.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView1 = findViewById<TextView>(R.id.textView1)
        val button2 = findViewById<Button>(R.id.button2)

        button1.setOnClickListener {
            startActivity(Intent(this, Activity::class.java))
        }

        button2.setOnClickListener {
            finishAffinity() // This will close the app
        }
    }
}
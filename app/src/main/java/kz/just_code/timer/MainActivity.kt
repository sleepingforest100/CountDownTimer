package kz.just_code.timer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kz.just_code.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputSeconds = findViewById<EditText>(R.id.edit_text_seconds)
        val startButton = findViewById<Button>(R.id.start_btn)

        startButton.setOnClickListener {
            val secondsStr = inputSeconds.text.toString()
            if (secondsStr.isNotEmpty()) {
                val seconds = secondsStr.toInt()
                val intent = Intent(this, CountdownActivity::class.java)
                intent.putExtra("seconds", seconds)
                startActivity(intent)
            } else {
                Toast.makeText(this, "You need to input seconds", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

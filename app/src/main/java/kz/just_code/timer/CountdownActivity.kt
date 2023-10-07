package kz.just_code.timer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CountdownActivity : AppCompatActivity() {

    private lateinit var countdownTimer: CountDownTimer
    private lateinit var textViewCountdown: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resetButton: Button

    private var secondsRemaining: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)

        textViewCountdown = findViewById(R.id.text_view_countdown)
        startButton = findViewById(R.id.start_countdown_btn)
        pauseButton = findViewById(R.id.pause_countdown_btn)
        resetButton = findViewById(R.id.reset_countdown_btn)

        val intent = intent
        if (intent != null && intent.hasExtra("seconds")) {
            secondsRemaining = intent.getIntExtra("seconds", 0)
        }

        countdownTimer = createCountdownTimer(secondsRemaining)

        startButton.setOnClickListener {
            countdownTimer.start()
            toggleButtons(true)
        }

        pauseButton.setOnClickListener {
            countdownTimer.cancel()
            toggleButtons(false)
        }

        resetButton.setOnClickListener {
            countdownTimer.cancel()
            secondsRemaining = 0
            updateCountdownText()
            toggleButtons(false)
        }

        // Handle incoming implicit intent (ACTION_SEND)
        if (intent?.action == Intent.ACTION_SEND) {
            val receivedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            // Handle the received text here
        }
    }

    private fun createCountdownTimer(seconds: Int): CountDownTimer {
        return object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = (millisUntilFinished / 1000).toInt()
                updateCountdownText()
            }

            override fun onFinish() {
                toggleButtons(false)
            }
        }
    }

    private fun updateCountdownText() {
        val minutes = secondsRemaining / 60
        val seconds = secondsRemaining % 60
        val timeText = String.format("%02d:%02d", minutes, seconds)
        textViewCountdown.text = timeText
    }

    private fun toggleButtons(running: Boolean) {
        startButton.isEnabled = !running
        pauseButton.isEnabled = running
        resetButton.isEnabled = !running
    }
}

package com.example.exercise1

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SimonColor { GREEN, RED, YELLOW, BLUE }

class MainActivity : AppCompatActivity() {

    private lateinit var levelView: TextView
    private lateinit var topScoreView: TextView
    private lateinit var green: Button
    private lateinit var red: Button
    private lateinit var yellow: Button
    private lateinit var blue: Button
    private lateinit var buttons: Array<Button>

    private var level = 1
    private var curIndex = 0

    private lateinit var sounds: Array<MediaPlayer>
    private val levels = mutableListOf((0..3).random())
    private val answers = mutableListOf<Int>()

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val key = "topScore"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        topScoreView.text = pref.getString(key, "1")

        lifecycleScope.launch { playSequence() }

        green.setOnClickListener {
            buttonPress(SimonColor.GREEN)
        }

        red.setOnClickListener {
            buttonPress(SimonColor.RED)
        }

        yellow.setOnClickListener {
            buttonPress(SimonColor.YELLOW)
        }

        blue.setOnClickListener {
            buttonPress(SimonColor.BLUE)
        }
    }

    private fun init() {
        levelView = findViewById(R.id.level)
        topScoreView = findViewById(R.id.topScoreValue)
        green = findViewById(R.id.greenButton)
        red = findViewById(R.id.redButton)
        yellow = findViewById(R.id.yellowButton)
        blue = findViewById(R.id.blueButton)
        sounds = arrayOf(
            MediaPlayer.create(this, R.raw.uh),
            MediaPlayer.create(this, R.raw.punch),
            MediaPlayer.create(this, R.raw.thud),
            MediaPlayer.create(this, R.raw.vibe)
        )
        buttons = arrayOf(green, red, yellow, blue)
        pref = getSharedPreferences("Storage", MODE_PRIVATE)
        editor = pref.edit()
    }

    private fun buttonPress(color: SimonColor) {
        var flag = true
        answers.add(color.ordinal)
        sounds[color.ordinal].start()
        if (!check(curIndex++)) {
            disableButtons()
            flag = false
            showScoreDialog(this, level - 1)
        }
        if (flag && curIndex == level) levelComplete()
    }


    private fun showScoreDialog(context: Context, score: Int) {
        val builder = AlertDialog.Builder(context)
            .setTitle("You Died")
            .setMessage("Your score is $score.")
            .setPositiveButton("Restart") { _, _ ->
                recreate()
            }
            .setCancelable(false)

        val dialog = builder.create()
        dialog.show()
    }

    private fun check(curIndex: Int) = levels[curIndex] == answers[curIndex]

    private fun levelComplete() {
        curIndex = 0
        answers.clear()
        levelUp()
        lifecycleScope.launch { playSequence() }
    }

    private fun levelUp() {
        if (level > pref.getString(key, "1")!!.toInt()) {
            editor.putString(key, level.toString())
            editor.apply()
        }
        topScoreView.text = pref.getString("topScore", "1")
        level++
        levelView.text = level.toString()
        levels.add((0..3).random())
    }

    private suspend fun playSequence() {
        disableButtons()
        delay(1000)
        for (l in levels) {
            highlightButton(l)
            delay(1000)
            resetColors()
        }
        enableButtons()
    }

    private fun disableButtons() {
        green.isEnabled = false
        red.isEnabled = false
        yellow.isEnabled = false
        blue.isEnabled = false
    }

    private fun enableButtons() {
        green.isEnabled = true
        red.isEnabled = true
        yellow.isEnabled = true
        blue.isEnabled = true
    }

    private fun highlightButton(index: Int) {
        when (index) {
            0 -> green.setBackgroundResource(R.color.hiGreen)
            1 -> red.setBackgroundResource(R.color.hiRed)
            2 -> yellow.setBackgroundResource(R.color.hiYellow)
            3 -> blue.setBackgroundResource((R.color.hiBlue))
        }
        sounds[index].start()
    }

    private fun resetColors() {
        green.setBackgroundResource(R.drawable.green_button)
        red.setBackgroundResource(R.drawable.red_button)
        yellow.setBackgroundResource(R.drawable.yellow_button)
        blue.setBackgroundResource((R.drawable.blue_button))
    }

    override fun onDestroy() {
        super.onDestroy()
        for (mp in sounds) mp.release()
    }
}
package com.example.exercise2

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.exercise2.databinding.FragmentGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class SimonColor { GREEN, RED, YELLOW, BLUE }

class GameFragment : Fragment() {
    private lateinit var buttons: Array<Button>

    private var level = 1
    private var curIndex = 0

    private lateinit var sounds: Array<MediaPlayer>
    private val levels = mutableListOf((0..3).random())
    private val answers = mutableListOf<Int>()

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val key = "topScore"

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        init()
        binding.topScoreValue.text = pref.getString(key, "0")

        viewLifecycleOwner.lifecycleScope.launch { playSequence() }

        binding.greenButton.setOnClickListener {
            buttonPress(SimonColor.GREEN)
        }

        binding.redButton.setOnClickListener {
            buttonPress(SimonColor.RED)
        }

        binding.yellowButton.setOnClickListener {
            buttonPress(SimonColor.YELLOW)
        }

        binding.blueButton.setOnClickListener {
            buttonPress(SimonColor.BLUE)
        }
    }

    private fun init() {
        sounds = arrayOf(
            MediaPlayer.create(requireContext(), R.raw.uh),
            MediaPlayer.create(requireContext(), R.raw.punch),
            MediaPlayer.create(requireContext(), R.raw.thud),
            MediaPlayer.create(requireContext(), R.raw.vibe)
        )
        buttons = arrayOf(
            binding.greenButton,
            binding.redButton,
            binding.yellowButton,
            binding.blueButton
        )
        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
    }

    private fun buttonPress(color: SimonColor) {
        var flag = true
        answers.add(color.ordinal)
        sounds[color.ordinal].start()
        if (!check(curIndex++)) {
            disableButtons()
            flag = false
            showScoreDialog(requireContext(), level - 1)
        }
        if (flag && curIndex == level) levelComplete()
    }


    private fun showScoreDialog(context: Context, score: Int) {
        val builder = AlertDialog.Builder(context)
            .setTitle("You Died")
            .setMessage("Your score is $score.")
            .setPositiveButton("Restart") { _, _ ->
                findNavController().navigate(
                    R.id.gameFragment,
                    arguments,
                    NavOptions.Builder().setPopUpTo(R.id.gameFragment, true).build()
                )
            }.setNegativeButton("Menu") { _, _ ->
                findNavController().popBackStack()
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
        viewLifecycleOwner.lifecycleScope.launch { playSequence() }
    }

    private fun levelUp() {
        if (level > pref.getString(key, "0")!!.toInt()) {
            editor.putString(key, level.toString())
            editor.apply()
        }
        binding.topScoreValue.text = pref.getString("topScore", "0")
        level++
        binding.level.text = level.toString()
        levels.add((0..3).random())
    }

    private suspend fun playSequence() {
        val customDelay = 100L
        disableButtons()
        delay(1000)
        for (l in levels) {
            highlightButton(l)
            delay(500 + customDelay)
            resetColors()
        }
        enableButtons()
    }

    private fun disableButtons() {
        binding.greenButton.isEnabled = false
        binding.redButton.isEnabled = false
        binding.yellowButton.isEnabled = false
        binding.blueButton.isEnabled = false
    }

    private fun enableButtons() {
        binding.greenButton.isEnabled = true
        binding.redButton.isEnabled = true
        binding.yellowButton.isEnabled = true
        binding.blueButton.isEnabled = true
    }

    private fun highlightButton(index: Int) {
        when (index) {
            0 -> binding.greenButton.setBackgroundResource(R.color.hiGreen)
            1 -> binding.redButton.setBackgroundResource(R.color.hiRed)
            2 -> binding.yellowButton.setBackgroundResource(R.color.hiYellow)
            3 -> binding.blueButton.setBackgroundResource((R.color.hiBlue))
        }
        sounds[index].start()
    }

    private fun resetColors() {
        binding.greenButton.setBackgroundResource(R.drawable.green_button)
        binding.redButton.setBackgroundResource(R.drawable.red_button)
        binding.yellowButton.setBackgroundResource(R.drawable.yellow_button)
        binding.blueButton.setBackgroundResource((R.drawable.blue_button))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        for (mp in sounds) mp.release()
    }
}
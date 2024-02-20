package com.example.bonusexercise6

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.bonusexercise6.databinding.FragmentGameBinding
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
    private val keyScore = "topScore"
    private val keySound = "mute"
    private val keyDelay = "delay"
    private val keyLight = "light"
    private val keyTheme = "theme"
    private var lightFlag = false

    private lateinit var greenList: ColorStateList
    private lateinit var redList: ColorStateList
    private lateinit var yellowList: ColorStateList
    private lateinit var blueList: ColorStateList

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
        binding.topScoreValue.text = pref.getString(keyScore, "0")

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
        buttons = arrayOf(
            binding.greenButton,
            binding.redButton,
            binding.yellowButton,
            binding.blueButton
        )

        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
        soundInit(Themes.entries[pref.getInt(keyTheme, 0)])
        if (pref.getBoolean(keySound, false))
            sounds.forEach { it.setVolume(0f, 0f) }

        lightFlag = pref.getBoolean(keyLight, false)
        setupStateList()
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
        if (level > pref.getString(keyScore, "0")!!.toInt()) {
            editor.putString(keyScore, level.toString())
            editor.apply()
        }
        binding.topScoreValue.text = pref.getString("topScore", "0")
        level++
        binding.level.text = level.toString()
        levels.add((0..3).random())
    }

    private suspend fun playSequence() {
        val customDelay = pref.getLong(keyDelay, 100)
        disableButtons()
        delay(1000)
        for ((count, l) in levels.withIndex()) {
            activateButton(l)
            delay(800)
            resetColors()
            if (count != levels.size - 1) delay(customDelay)
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

    private fun activateButton(index: Int) {
        if (!lightFlag) {
            when (index) {
                0 -> binding.greenButton.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.hiGreen
                        )
                    )

                1 -> binding.redButton.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.hiRed))

                2 -> binding.yellowButton.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.hiYellow
                        )
                    )

                3 -> binding.blueButton.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.hiBlue))
            }
        }
        sounds[index].start()
    }

    private fun resetColors() {
        binding.greenButton.backgroundTintList = greenList
        binding.redButton.backgroundTintList = redList
        binding.yellowButton.backgroundTintList = yellowList
        binding.blueButton.backgroundTintList = blueList
    }

    private fun setupStateList() {
        if (lightFlag) {
            greenList =
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.green_button_colors_no_light
                )!!
            redList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.red_button_colors_no_light
            )!!
            yellowList =
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.yellow_button_colors_no_light
                )!!
            blueList =
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.blue_button_colors_no_light
                )!!
        } else {
            greenList =
                ContextCompat.getColorStateList(requireContext(), R.color.green_button_colors)!!
            redList = ContextCompat.getColorStateList(requireContext(), R.color.red_button_colors)!!
            yellowList =
                ContextCompat.getColorStateList(requireContext(), R.color.yellow_button_colors)!!
            blueList =
                ContextCompat.getColorStateList(requireContext(), R.color.blue_button_colors)!!
        }
    }

    private fun soundInit(theme: Themes) {
        when (theme) {
            Themes.MEMES -> {
                sounds = arrayOf(
                    MediaPlayer.create(requireContext(), R.raw.uh),
                    MediaPlayer.create(requireContext(), R.raw.punch),
                    MediaPlayer.create(requireContext(), R.raw.thud),
                    MediaPlayer.create(requireContext(), R.raw.vibe)
                )
            }

            Themes.SYNTH -> {
                sounds = arrayOf(
                    MediaPlayer.create(requireContext(), R.raw.synt_one),
                    MediaPlayer.create(requireContext(), R.raw.synt_two),
                    MediaPlayer.create(requireContext(), R.raw.synt_three),
                    MediaPlayer.create(requireContext(), R.raw.synt_four)
                )
            }

            Themes.DRUMS -> {
                sounds = arrayOf(
                    MediaPlayer.create(requireContext(), R.raw.drum_one),
                    MediaPlayer.create(requireContext(), R.raw.drum_two),
                    MediaPlayer.create(requireContext(), R.raw.drum_three),
                    MediaPlayer.create(requireContext(), R.raw.drum_four)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        for (mp in sounds) mp.release()
    }
}
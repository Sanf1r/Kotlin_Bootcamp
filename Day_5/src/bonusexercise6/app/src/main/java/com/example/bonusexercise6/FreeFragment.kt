package com.example.bonusexercise6


import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bonusexercise6.databinding.FragmentFreeBinding

class FreeFragment : Fragment() {
    private lateinit var binding: FragmentFreeBinding

    private lateinit var buttons: Array<Button>
    private lateinit var sounds: Array<MediaPlayer>

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val keySound = "mute"
    private val keyLight = "light"
    private val keyTheme = "theme"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFreeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        init()

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
        if (pref.getBoolean(keyLight, false)) changeStateList()
    }

    private fun changeStateList() {
        binding.greenButton.backgroundTintList = ContextCompat.getColorStateList(
            requireContext(),
            R.color.green_button_colors_no_light
        )
        binding.redButton.backgroundTintList = ContextCompat.getColorStateList(
            requireContext(),
            R.color.red_button_colors_no_light
        )
        binding.yellowButton.backgroundTintList = ContextCompat.getColorStateList(
            requireContext(),
            R.color.yellow_button_colors_no_light
        )
        binding.blueButton.backgroundTintList = ContextCompat.getColorStateList(
            requireContext(),
            R.color.blue_button_colors_no_light
        )
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

    private fun buttonPress(color: SimonColor) {
        sounds[color.ordinal].start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        for (mp in sounds) mp.release()
    }

}
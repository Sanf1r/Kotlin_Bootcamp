package com.example.bonusexercise4


import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.bonusexercise4.databinding.FragmentFreeBinding

class FreeFragment : Fragment() {
    private lateinit var binding: FragmentFreeBinding

    private lateinit var buttons: Array<Button>
    private lateinit var sounds: Array<MediaPlayer>

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val keySound = "mute"

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

        if (pref.getBoolean(keySound, false))
            sounds.forEach { it.setVolume(0f, 0f) }
    }

    private fun buttonPress(color: SimonColor) {
        sounds[color.ordinal].start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        for (mp in sounds) mp.release()
    }

}
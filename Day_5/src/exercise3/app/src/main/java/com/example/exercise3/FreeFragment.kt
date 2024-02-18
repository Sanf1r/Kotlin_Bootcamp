package com.example.exercise3


import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.exercise3.databinding.FragmentFreeBinding

class FreeFragment : Fragment() {
    private lateinit var binding: FragmentFreeBinding

    private lateinit var buttons: Array<Button>

    private lateinit var sounds: Array<MediaPlayer>

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
        sounds.forEach { it.setOnCompletionListener { resetColors() } }
        buttons = arrayOf(
            binding.greenButton,
            binding.redButton,
            binding.yellowButton,
            binding.blueButton
        )
    }

    private fun buttonPress(color: SimonColor) {
        highlightButton(color.ordinal)
        sounds[color.ordinal].start()
    }

    private fun highlightButton(index: Int) {
        when (index) {
            0 -> binding.greenButton.setBackgroundResource(R.color.hiGreen)
            1 -> binding.redButton.setBackgroundResource(R.color.hiRed)
            2 -> binding.yellowButton.setBackgroundResource(R.color.hiYellow)
            3 -> binding.blueButton.setBackgroundResource((R.color.hiBlue))
        }
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
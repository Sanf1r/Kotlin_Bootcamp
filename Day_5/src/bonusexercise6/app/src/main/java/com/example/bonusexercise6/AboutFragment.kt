package com.example.bonusexercise6

import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.bonusexercise6.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    private lateinit var sound: MediaPlayer
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val key = "topScore"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        pref = requireContext().getSharedPreferences("Storage", AppCompatActivity.MODE_PRIVATE)
        editor = pref.edit()
        binding.recordValue.text = pref.getString(key, "0")
        sound = MediaPlayer.create(requireContext(),R.raw.pablo)
        binding.authorValue.setOnClickListener { sound.start() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        sound.release()
    }
}
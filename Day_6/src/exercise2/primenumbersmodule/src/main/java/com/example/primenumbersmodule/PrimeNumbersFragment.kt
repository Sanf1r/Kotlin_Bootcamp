package com.example.primenumbersmodule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.primenumbersmodule.databinding.FragmentPrimeNumbersBinding

class PrimeNumbersFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentPrimeNumbersBinding
    private var mode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrimeNumbersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderSpinner.onItemSelectedListener = this
        binding.calculate.setOnClickListener {
            binding.resultText.text = primeNumbersLogic(
                binding.numberInput.text.toString().toLongOrNull(),
                mode
            )
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mode = position == 0
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
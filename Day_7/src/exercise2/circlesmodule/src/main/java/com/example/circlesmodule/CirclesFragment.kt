package com.example.circlesmodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.circlesmodule.databinding.FragmentCirclesBinding
import com.example.loggermodule.FragmentWithLogger
import com.example.loggermodule.LoggerModule
import com.example.loggermodule.logger
import kotlinx.coroutines.launch

class CirclesFragment : FragmentWithLogger() {
    private lateinit var binding: FragmentCirclesBinding
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCirclesBinding.inflate(layoutInflater, container, false)
        LoggerModule.i(tagName, "Fragment view creation started")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoggerModule.i(tagName, "Fragment view created")

        binding.firstXInput.logger("First circle X")
        binding.firstYInput.logger("First circle Y")
        binding.firstRadiusInput.logger("First circle radius")
        binding.secondXInput.logger("Second circle X")
        binding.secondYInput.logger("Second circle Y")
        binding.secondRadiusInput.logger("Second circle radius")

        binding.calculate.setOnClickListener {
            LoggerModule.i(tagName, "Calculate button clicked ${++count} times")
            viewLifecycleOwner.lifecycleScope.launch {
                LoggerModule.i(tagName, "$this started")
                binding.resultText.text = calculations()
                LoggerModule.i(tagName, "$this ended")
            }
        }
    }

    private fun calculations(): StringBuilder {
        val res = StringBuilder()
        if (inputCheck()) {
            val one = Circle(
                binding.firstXInput.text.toString().toDouble(),
                binding.firstYInput.text.toString().toDouble(),
                binding.firstRadiusInput.text.toString().toDouble()
            )
            val two = Circle(
                binding.secondXInput.text.toString().toDouble(),
                binding.secondYInput.text.toString().toDouble(),
                binding.secondRadiusInput.text.toString().toDouble()
            )

            if (one == two) {
                res.append("The circles coincide.")
            } else if (circlesInside(one, two) || circlesInside(two, one)) {
                res.append("The circles inside one another.")
            } else if (circlesTouch(one, two)) {
                res.append("The circles touch.")
                res.appendLine()
                val point = intersectPoints(one, two)
                res.append("Point of touch: x = ${point.first.x}, y = ${point.first.y}.")
            } else if (circlesIntersect(one, two)) {
                res.append("The circles intersect.")
                val points = intersectPoints(one, two)
                res.appendLine()
                res.append(
                    "Points of intersection: x1 = ${points.first.x}, y1 = ${points.first.y}" +
                            ", x2 = ${points.second.x}, y2 = ${points.second.y}."
                )
            } else {
                res.append("The circles do not intersect.")
            }
        } else {
            res.append("Something wrong! Check input data!")
        }
        return res
    }

    private fun inputCheck(): Boolean {
        val str = "^([1-9](\\d+)?([.]\\d+)?|0[.]\\d+)\$"
        return (binding.firstXInput.text.toString().isNotEmpty() &&
                binding.firstYInput.text.toString().isNotEmpty() &&
                binding.firstRadiusInput.text.toString().matches(str.toRegex()) &&
                binding.secondXInput.text.toString().isNotEmpty() &&
                binding.secondYInput.text.toString().isNotEmpty() &&
                binding.secondRadiusInput.text.toString().matches(str.toRegex()))
    }
}
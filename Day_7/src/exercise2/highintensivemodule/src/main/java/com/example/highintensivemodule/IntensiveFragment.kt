package com.example.highintensivemodule

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.highintensivemodule.databinding.FragmentIntensiveBinding
import com.example.loggermodule.FragmentWithLogger
import com.example.loggermodule.LoggerModule
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.math.BigDecimal
import kotlin.coroutines.coroutineContext
import kotlin.math.cbrt
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

enum class Jobs { FACTORIAL, ROOTS, LOGS, EXP, SIMPLE }

class IntensiveFragment : FragmentWithLogger() {
    private lateinit var binding: FragmentIntensiveBinding
    private lateinit var buttonList: ArrayList<MaterialButton>
    private var factJob: CompletableJob? = null
    private var rootsJob: CompletableJob? = null
    private var logsJob: CompletableJob? = null
    private var expJob: CompletableJob? = null
    private var simpleJob: CompletableJob? = null
    private var allInJob: CompletableJob? = null
    private val listJobs = arrayListOf(factJob, rootsJob, logsJob, expJob, simpleJob)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentIntensiveBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonList =
            arrayListOf(binding.factorial, binding.roots, binding.logs, binding.exp, binding.simple)

        binding.factorial.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.FACTORIAL)
            }
        }

        binding.roots.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.ROOTS)
            }
        }

        binding.logs.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.LOGS)
            }
        }

        binding.exp.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.EXP)
            }
        }

        binding.simple.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.SIMPLE)
            }
        }

        binding.allIn.setOnClickListener {
            it.hideKeyboard()
            if (listJobs.any { job -> job?.isActive == true }) {
                Toast.makeText(requireContext(), "First cancel all jobs", Toast.LENGTH_SHORT).show()
            } else {
                if (!blankInput()) {
                    startOrCancelBigJob()
                }
            }
        }
    }

    private fun runFactorialJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("FactorialCorTag", job)
            val res = calculateFactorial(binding.firstXInput.text.toString().toIntOrNull())
            uiResultUpdate(res)
            job.complete()
        }.invokeOnCompletion {
            uiButtonTextUpdate(binding.factorial, getString(R.string.factorial))
            logOnFinish(it, "FactorialCorTag", job)
        }
    }

    private fun runRootsJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("RootsCorTag", job)
            val res = async { calculateSquareRoot(binding.firstXInput.text.toString().toDouble()) }
            val res2 = async { calculateCubeRoot(binding.firstXInput.text.toString().toDouble()) }
            val out = res.await() + "\n" + res2.await()
            uiResultUpdate(out)
            job.complete()
        }.invokeOnCompletion {
            uiButtonTextUpdate(binding.roots, getString(R.string.square_and_cube_root))
            logOnFinish(it, "RootsCorTag", job)
        }
    }

    private fun runLogsJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("LogsCorTag", job)
            val res = async { calculateLog(binding.firstXInput.text.toString().toDouble()) }
            val res2 = async { calculateLn(binding.firstXInput.text.toString().toDouble()) }
            val out = res.await() + "\n" + res2.await()
            uiResultUpdate(out)
            job.complete()
        }.invokeOnCompletion {
            uiButtonTextUpdate(binding.logs, getString(R.string.logs))
            logOnFinish(it, "LogsCorTag", job)
        }
    }

    private fun runExpJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("ExpCorTag", job)
            val res = async { calculateExpTwo(binding.firstXInput.text.toString().toDouble()) }
            val res2 = async { calculateExpThree(binding.firstXInput.text.toString().toDouble()) }
            val out = res.await() + "\n" + res2.await()
            uiResultUpdate(out)
            job.complete()
        }.invokeOnCompletion {
            uiButtonTextUpdate(binding.exp, getString(R.string.exp))
            logOnFinish(it, "ExpCorTag", job)
        }
    }

    private fun runSimpleJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("SimpleCorTag", job)
            var res = withTimeoutOrNull(1000L) {
                delay(2000)
                calculateSimple(binding.firstXInput.text.toString().toInt())
            }
            if (res == null) {
                res = "Timeout!"
                showAlertDialog()
            }
            uiResultUpdate(res)
            job.complete()
        }.invokeOnCompletion {
            uiButtonTextUpdate(binding.simple, getString(R.string.simple))
            logOnFinish(it, "SimpleCorTag", job)
        }
    }

    private fun runAllInJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("AllCorTag", job)
            val intNumber = binding.firstXInput.text.toString().toIntOrNull()
            val doubleNumber = binding.firstXInput.text.toString().toDouble()
            val res1 = async { calculateFactorial(intNumber) }
            val res2 = async {
                val tmp1 = async { calculateSquareRoot(doubleNumber) }.await()
                val tmp2 = async { calculateCubeRoot(doubleNumber) }.await()
                tmp1 + "\n" + tmp2
            }
//            awaitAll(
//                async {
//                    withContext(Dispatchers.Main) { binding.roots.performClick() }
//                },
//                async {
//                    withContext(Dispatchers.Main) { binding.logs.performClick() }
//                },
//                async {
//                    withContext(Dispatchers.Main) { binding.exp.performClick() }
//                },
//                async {
//                    withContext(Dispatchers.Main) { binding.simple.performClick() }
//                }
//            )
            val out = res2.await()
            uiResultUpdate(out)
            job.complete()
        }.invokeOnCompletion {
            uiButtonTextUpdate(binding.allIn, getString(R.string.all_in))
            logOnFinish(it, "AllCorTag", job)
            enableButtons()
        }
    }

    private fun startOrCancelSmallJobs(work: Jobs) {
        val mode = work.ordinal
        if (listJobs[mode]?.isActive == true) {
            listJobs[mode]?.cancel()
        } else {
            listJobs[mode] = Job()
            buttonList[mode].text = getString(R.string.cancel)
            when (work) {
                Jobs.FACTORIAL -> runFactorialJob(listJobs[mode]!!)
                Jobs.ROOTS -> runRootsJob(listJobs[mode]!!)
                Jobs.LOGS -> runLogsJob(listJobs[mode]!!)
                Jobs.EXP -> runExpJob(listJobs[mode]!!)
                Jobs.SIMPLE -> runSimpleJob(listJobs[mode]!!)
            }
        }
    }

    private fun startOrCancelBigJob() {
        if (allInJob?.isActive == true) {
            allInJob?.cancel()
            binding.allIn.text = getString(R.string.all_in)
        } else {
            allInJob = Job()
            binding.allIn.text = getString(R.string.cancel)
            disableButtons()
            runAllInJob(allInJob!!)
        }
    }

    private fun blankInput(): Boolean {
        val str = binding.firstXInput.text.toString()
        if (str.isBlank() || str == "." || str == "-" || str == "-.") {
            binding.resultText.text = getString(R.string.wrong_input)
            return true
        }
        return false
    }

    private fun uiResultUpdate(res: String) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.resultText.text = res
        }
    }

    private fun uiButtonTextUpdate(btn: MaterialButton, text: String) {
        CoroutineScope(Dispatchers.Main).launch {
            btn.text = text
        }
    }

    private suspend fun showAlertDialog() {
        withContext(Dispatchers.Main) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("An error has occurred. Please try again.")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }
    }

    private fun logOnStart(tag: String, job: CompletableJob) {
        LoggerModule.i(tag, "$job started.")
    }

    private fun logOnFinish(it: Throwable?, tag: String, job: CompletableJob) {
        if (it != null) {
            LoggerModule.i(tag, "$job canceled.")
        } else {
            LoggerModule.i(tag, "$job finished normally.")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        factJob?.cancel()
    }

    private suspend fun calculateFactorial(number: Int?): String {
        if (number == null || number < 0) return "Wrong input!"
        var factorial = BigDecimal.ONE
        for (i in 1..number) {
            coroutineContext.ensureActive()
            factorial = factorial.times(BigDecimal.valueOf(i.toLong()))
        }
        return "Factorial = $factorial"
    }

    private suspend fun calculateSimple(number: Int): String {
        var res = true
        if (number <= 1) res = false
        var i = 2
        while (i * i <= number) {
            coroutineContext.ensureActive()
            if (number % i == 0) {
                res = false
                break
            }
            ++i
        }
        return if (res) "Number is simple" else "Number is not simple"
    }

    private fun calculateSquareRoot(number: Double): String =
        "Square root = " + sqrt(number).toString()

    private fun calculateCubeRoot(number: Double): String =
        "Cube root = " + cbrt(number).toString()

    private fun calculateLog(number: Double): String =
        "Log10 = " + log10(number).toString()

    private fun calculateLn(number: Double): String =
        "Ln = " + ln(number).toString()

    private fun calculateExpTwo(number: Double): String =
        "Square number = " + number.pow(2.0).toString()

    private fun calculateExpThree(number: Double): String =
        "Cube number = " + number.pow(3.0).toString()

    private fun disableButtons() {
        CoroutineScope(Dispatchers.Main).launch {
            buttonList.forEach { it.isEnabled = false }
        }
    }

    private fun enableButtons() {
        CoroutineScope(Dispatchers.Main).launch {
            buttonList.forEach { it.isEnabled = true }
        }
    }
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}



package com.example.highintensivemodule

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
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
            arrayListOf(
                binding.runFactorial,
                binding.runRoots,
                binding.runLogs,
                binding.runExp,
                binding.runSimple
            )

        binding.runFactorial.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.FACTORIAL)
            }
        }

        binding.runRoots.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.ROOTS)
            }
        }

        binding.runLogs.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.LOGS)
            }
        }

        binding.runExp.setOnClickListener {
            it.hideKeyboard()
            if (!blankInput()) {
                startOrCancelSmallJobs(Jobs.EXP)
            }
        }

        binding.runSimple.setOnClickListener {
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
            val res = calculateFactorial(binding.numberInput.text.toString().toIntOrNull())
            uiResultUpdate(binding.factorialResult, res)
            job.complete()
        }.invokeOnCompletion {
            if (it == null) uiButtonTextUpdate(binding.runFactorial, getString(R.string.run))
            logOnFinish(it, "FactorialCorTag", job)
        }
    }

    private fun runRootsJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("RootsCorTag", job)
            val res = async { calculateSquareRoot(binding.numberInput.text.toString().toDouble()) }
            val res2 = async { calculateCubeRoot(binding.numberInput.text.toString().toDouble()) }
            uiResultUpdate(binding.squareRootResult, res.await())
            uiResultUpdate(binding.cubeRootResult, res2.await())
            job.complete()
        }.invokeOnCompletion {
            if (it == null) uiButtonTextUpdate(binding.runRoots, getString(R.string.run))
            logOnFinish(it, "RootsCorTag", job)
        }
    }

    private fun runLogsJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("LogsCorTag", job)
            val res = async { calculateLog(binding.numberInput.text.toString().toDouble()) }
            val res2 = async { calculateLn(binding.numberInput.text.toString().toDouble()) }
            uiResultUpdate(binding.lgResult, res.await())
            uiResultUpdate(binding.lnResult, res2.await())
            job.complete()
        }.invokeOnCompletion {
            if (it == null) uiButtonTextUpdate(binding.runLogs, getString(R.string.run))
            logOnFinish(it, "LogsCorTag", job)
        }
    }

    private fun runExpJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("ExpCorTag", job)
            val res = async { calculateExpTwo(binding.numberInput.text.toString().toDouble()) }
            val res2 = async { calculateExpThree(binding.numberInput.text.toString().toDouble()) }
            uiResultUpdate(binding.squareResult, res.await())
            uiResultUpdate(binding.cubeResult, res2.await())
            job.complete()
        }.invokeOnCompletion {
            if (it == null) uiButtonTextUpdate(binding.runExp, getString(R.string.run))
            logOnFinish(it, "ExpCorTag", job)
        }
    }

    private fun runSimpleJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("SimpleCorTag", job)
            var res = withTimeoutOrNull(1000L) {
                if (binding.numberInput.text.toString().toInt() > 100) delay(2000)
                calculateSimple(binding.numberInput.text.toString().toInt())
            }
            if (res == null) {
                res = "Timeout!"
                showAlertDialog()
            }
            uiResultUpdate(binding.simpleResult, res)
            job.complete()
        }.invokeOnCompletion {
            if (it == null) uiButtonTextUpdate(binding.runSimple, getString(R.string.run))
            logOnFinish(it, "SimpleCorTag", job)
        }
    }

    private fun runAllInJob(job: CompletableJob) {
        CoroutineScope(Dispatchers.Default + job).launch {
            logOnStart("AllCorTag", job)
            val intNumber = binding.numberInput.text.toString().toIntOrNull()
            val doubleNumber = binding.numberInput.text.toString().toDouble()
            val res1 = async { calculateFactorial(intNumber) }
            val res2 = async { calculateSquareRoot(doubleNumber) }
            val res3 = async { calculateCubeRoot(doubleNumber) }
            val res4 = async { calculateLog(doubleNumber) }
            val res5 = async { calculateLn(doubleNumber) }
            val res6 = async { calculateExpTwo(doubleNumber) }
            val res7 = async { calculateExpThree(doubleNumber) }
            runSimpleJob(job)
            uiResultUpdate(binding.squareRootResult, res2.await())
            uiResultUpdate(binding.cubeRootResult, res3.await())
            uiResultUpdate(binding.lgResult, res4.await())
            uiResultUpdate(binding.lnResult, res5.await())
            uiResultUpdate(binding.squareResult, res6.await())
            uiResultUpdate(binding.cubeResult, res7.await())
            uiResultUpdate(binding.factorialResult, res1.await())
            job.complete()
        }.invokeOnCompletion {
            if (it == null) {
                uiButtonTextUpdate(binding.allIn, getString(R.string.all_in))
                enableButtons()
            }
            logOnFinish(it, "AllCorTag", job)
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
        val str = binding.numberInput.text.toString()
        if (str.isBlank() || str == "." || str == "-" || str == "-.") {
            Toast.makeText(requireContext(), "Wrong input!", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun uiResultUpdate(view: TextView, res: String) {
        CoroutineScope(Dispatchers.Main).launch {
            view.text = res
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

    private suspend fun calculateFactorial(number: Int?): String {
        if (number == null || number < 0) return "Number too big"
        var factorial = BigDecimal.ONE
        for (i in 1..number) {
            coroutineContext.ensureActive()
            factorial = factorial.times(BigDecimal.valueOf(i.toLong()))
        }
        return factorial.toString()
    }

    private suspend fun calculateSimple(number: Int?): String {
        if (number == null || number < 0) return "Number too big"
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
        return if (res) "Yes" else "No"
    }

    private fun calculateSquareRoot(number: Double): String =
        sqrt(number).toString()

    private fun calculateCubeRoot(number: Double): String =
        cbrt(number).toString()

    private fun calculateLog(number: Double): String =
        log10(number).toString()

    private fun calculateLn(number: Double): String =
        ln(number).toString()

    private fun calculateExpTwo(number: Double): String =
        number.pow(2.0).toString()

    private fun calculateExpThree(number: Double): String =
        number.pow(3.0).toString()

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

    override fun onDestroy() {
        super.onDestroy()
        allInJob?.cancel()
        listJobs.forEach { it?.cancel() }
    }
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}



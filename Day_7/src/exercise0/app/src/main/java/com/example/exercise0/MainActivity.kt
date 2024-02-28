package com.example.exercise0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.example.exercise0.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val jobTime = 4000
    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.test1Button.setOnClickListener {
            Log.i("TestOneTag", "UI thread will be blocked for 5 sec")
            testOne()
            Log.i("TestOneTag", "UI thread back up")
        }

        binding.test2Button.setOnClickListener {
            if (!::job.isInitialized) {
                initJob()
            }
            binding.progressBar.startJobOrCancel(job)
        }
    }

    private fun ProgressBar.startJobOrCancel(job: CompletableJob) {
        if (this.progress > 0) {
            if (this.progress == 100) {
                job.completeExceptionally(CancellationException("Download reseted"))
            } else {
                job.cancel(CancellationException("Download canceled"))
            }
            initJob()
        } else {
            binding.test2Button.text = context.getString(R.string.cancel)
            binding.downloadStatus.text = context.getString(R.string.status)
            CoroutineScope(Dispatchers.IO + job).launch {
                for (i in 0..100) {
                    delay(jobTime / 100L)
                    this@startJobOrCancel.progress = i
                }
                updateUIButtonText()
                updateUIStatusText()
            }
        }
    }

    private fun updateUIStatusText() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.downloadStatus.text = getString(R.string.download_complete)
        }
    }

    private fun updateUIButtonText() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.test2Button.text = getString(R.string.reset_download)
        }
    }


    private fun initJob() {
        binding.test2Button.text = getString(R.string.start)
        binding.downloadStatus.text = ""
        job = Job()
        job.invokeOnCompletion { err ->
            err?.message.let {
                var msg = it
                if (msg.isNullOrBlank()) {
                    msg = "Unknown error occurred"
                }
                showInfo(msg)
            }
        }
        binding.progressBar.progress = 0
    }

    private fun showInfo(text: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}

fun testOne() {
    runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            delay(5000L) // non-blocking delay for 5 second (default time unit is ms)
        }
    }
}


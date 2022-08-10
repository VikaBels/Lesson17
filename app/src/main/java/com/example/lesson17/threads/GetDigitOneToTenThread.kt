package com.example.lesson17.threads

import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.lesson17.R
import com.example.lesson17.databinding.ActivityMainBinding
import com.example.lesson17.models.GeneralArea

class GetDigitOneToTenThread(
    private val bindingMain: ActivityMainBinding?,
    private val threadWriter: WriteMessageThread,
    private val objGeneralArea: GeneralArea
) : Runnable {
    companion object {
        const val FINAL_NUMBER = 10
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun run() {
        while (!objGeneralArea.shouldExit) {
            counterOneTen()
        }
    }

    private fun counterOneTen() {
        for (i in 1..FINAL_NUMBER) {
            sendDigit(i)

            if (i == FINAL_NUMBER) {
                Thread.sleep(100)
                handler.post {
                    setButtonEnable()
                }
                objGeneralArea.stopAllThread()
            }

            try {
                Thread.sleep(5000)
            } catch (ex: InterruptedException) {
                println(ex)
            }
        }
    }

    private fun sendDigit(number: Int) {
        threadWriter.appendMessage(number.toString())
    }

    private fun setButtonEnable() {
        bindingMain?.btnStartThreads?.apply {
            isEnabled = true
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200))
        }
    }
}
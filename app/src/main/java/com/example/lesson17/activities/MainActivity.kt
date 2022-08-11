package com.example.lesson17.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lesson17.R
import com.example.lesson17.databinding.ActivityMainBinding
import com.example.lesson17.models.GeneralArea
import com.example.lesson17.threads.CounterThread
import com.example.lesson17.threads.FindSimpleDigitThread
import com.example.lesson17.threads.SendCustomMessageThread
import com.example.lesson17.threads.WriteMessageThread

class MainActivity : AppCompatActivity() {
    private var bindingMain: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain?.root)

        setScrollingTextView()

        clickListenerBtnStart()
    }

    private fun clickListenerBtnStart() {
        bindingMain?.btnStartThreads?.setOnClickListener {
            setBtnStartDisable()
            startAllThreads()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMain = null
    }

    private fun setScrollingTextView() {
        bindingMain?.container?.movementMethod = ScrollingMovementMethod()
    }

    private fun startAllThreads() {
        val objectGeneralArea = GeneralArea()

        val objectThreadWriter = WriteMessageThread(bindingMain, objectGeneralArea)
        val runnableSimpleDigit = FindSimpleDigitThread(objectThreadWriter, objectGeneralArea)
        val runnableDigit =
            CounterThread(bindingMain, objectThreadWriter, objectGeneralArea)
        val runnableString =
            SendCustomMessageThread(
                objectThreadWriter,
                objectGeneralArea,
                resources.getString(R.string.string_for_thread)
            )

        Thread(objectThreadWriter).start()

        Thread(runnableString).start()

        Thread(runnableSimpleDigit).start()

        Thread(runnableDigit).start()
    }

    private fun setBtnStartDisable() {
        bindingMain?.btnStartThreads?.apply {
            isEnabled = false
            setTextColor(ContextCompat.getColor(context, R.color.grey))
            setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
        }
    }
}
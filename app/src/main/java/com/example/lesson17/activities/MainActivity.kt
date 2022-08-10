package com.example.lesson17.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lesson17.R
import com.example.lesson17.databinding.ActivityMainBinding
import com.example.lesson17.models.GeneralArea
import com.example.lesson17.threads.GetDigitOneToTenThread
import com.example.lesson17.threads.GetSimpleDigitThread
import com.example.lesson17.threads.GetStringThread
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
            startAllThread()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMain = null
    }

    private fun setScrollingTextView() {
        bindingMain?.container?.movementMethod = ScrollingMovementMethod()
    }

    private fun startAllThread() {
        val objectGeneralArea = GeneralArea()

        val objectThreadWriter = WriteMessageThread(bindingMain, objectGeneralArea)
        val runnableSimpleDigit = GetSimpleDigitThread(objectThreadWriter, objectGeneralArea)
        val runnableDigit =
            GetDigitOneToTenThread(bindingMain, objectThreadWriter, objectGeneralArea)
        val runnableString =
            GetStringThread(
                objectThreadWriter,
                objectGeneralArea,
                resources.getString(R.string.string_for_thread)
            )

        Thread(objectThreadWriter).start()

        Thread(runnableSimpleDigit).start()

        Thread(runnableDigit).start()

        Thread(runnableString).start()
    }

    private fun setBtnStartDisable() {
        bindingMain?.btnStartThreads?.apply {
            isEnabled = false
            setTextColor(ContextCompat.getColor(context, R.color.grey))
            setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
        }
    }
}
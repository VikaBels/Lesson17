package com.example.lesson17.threads

import android.os.Handler
import android.os.Looper
import com.example.lesson17.databinding.ActivityMainBinding
import com.example.lesson17.models.GeneralArea
import kotlin.collections.ArrayList

class WriteMessageThread(
    private val bindingMain: ActivityMainBinding?,
    private val objGeneralArea: GeneralArea
) : Runnable {
    private val listMessage = ArrayList<String>()
    private val handler = Handler(Looper.getMainLooper())

    override fun run() {
        while (!objGeneralArea.shouldExit) {
            writeMessage()

            try {
                Thread.sleep(100)
            } catch (ex: InterruptedException) {
                println(ex)
            }
        }
    }

    private fun writeMessage() {
        val stringListMessage: String
        val finalText = StringBuilder()

        synchronized(this) {
            stringListMessage = listMessage.joinToString(
                separator = " "
            )

            listMessage.clear()
        }

        handler.post {
            finalText.apply {
                append(bindingMain?.container?.text)
                append(stringListMessage)
            }

            bindingMain?.container?.text = finalText
        }
    }

    fun appendMessage(text: String) {
        synchronized(this) {
            listMessage.add("$text,")
        }
    }
}
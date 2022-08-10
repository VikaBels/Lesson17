package com.example.lesson17.threads

import com.example.lesson17.models.GeneralArea

class GetStringThread(
    private val threadWriter: WriteMessageThread,
    private val objGeneralArea: GeneralArea,
    private val text: String
) : Runnable {

    override fun run() {
        while (!objGeneralArea.shouldExit) {
            putSleepThread()
            sendString()
        }
    }

    private fun putSleepThread() {
        objGeneralArea.waitThread()
    }

    private fun sendString() {
        threadWriter.appendMessage(text)
    }
}
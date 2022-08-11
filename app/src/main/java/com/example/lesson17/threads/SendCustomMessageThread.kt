package com.example.lesson17.threads

import com.example.lesson17.models.GeneralArea

class SendCustomMessageThread(
    private val threadWriter: WriteMessageThread,
    private val objGeneralArea: GeneralArea,
    private val message: String
) : Runnable {

    override fun run() {
        while (!objGeneralArea.shouldExit) {
            putSleepThread()
            sendMessage()
        }
    }

    private fun putSleepThread() {
        objGeneralArea.waitThread()
    }

    private fun sendMessage() {
        threadWriter.appendMessage(message)
    }
}
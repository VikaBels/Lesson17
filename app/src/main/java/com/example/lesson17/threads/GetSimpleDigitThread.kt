package com.example.lesson17.threads

import com.example.lesson17.models.GeneralArea

class GetSimpleDigitThread(
    private val threadWriter: WriteMessageThread,
    private val objGeneralArea: GeneralArea
) : Runnable {
    companion object {
        const val MIN_NUMBER = 1
    }

    override fun run() {
        while (!objGeneralArea.shouldExit) {
            findSimpleDigit()
        }
    }

    private fun findSimpleDigit() {
        for (i in MIN_NUMBER..Int.MAX_VALUE) {
            var isSimple = true

            for (j in 2 until i) {
                if (i.toDouble() % j.toDouble() == 0.0) {
                    isSimple = false
                    break
                }
            }

            if (isSimple) {
                sendSimpleDigit(i)
                callStringThread()
            }

            try {
                Thread.sleep(1000)
            } catch (ex: InterruptedException) {
                println(ex)
            }
        }
    }

    private fun sendSimpleDigit(number: Int) {
        threadWriter.appendMessage(number.toString())
    }

    private fun callStringThread() {
        objGeneralArea.notifyThread()
    }
}
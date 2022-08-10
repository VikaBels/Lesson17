package com.example.lesson17.models

class GeneralArea {
    private val obj = Object()

    @Volatile
    var shouldExit = false

    fun stopAllThread() {
        shouldExit = true
    }

    fun waitThread() {
        try {
            synchronized(obj) {
                obj.wait()
            }
        } catch (e: InterruptedException) {
            println(e)
        }
    }

    fun notifyThread() {
        try {
            synchronized(obj) {
                obj.notify()
            }
        } catch (e: InterruptedException) {
            println(e)
        }
    }
}

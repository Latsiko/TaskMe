package com.latsiko.taskme.Utilities

import android.app.Application

class TaskMe : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TaskMe
            private set
    }
}
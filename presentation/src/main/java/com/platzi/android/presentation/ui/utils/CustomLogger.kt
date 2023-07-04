package com.platzi.android.presentation.ui.utils

import android.util.Log

interface CustomLogger {
    fun log(message: String)
    fun logImmediately(priority: Int = Log.DEBUG, tag: String, message: String)
}

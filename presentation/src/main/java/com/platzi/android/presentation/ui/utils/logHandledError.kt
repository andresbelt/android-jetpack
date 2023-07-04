package com.platzi.android.presentation.ui.utils

import android.util.Log
import com.platzi.android.presentation.ui.utils.text.isNotNullAndBlank
import com.example.domain.commons.HandledError
import com.example.domain.commons.HandledError.UnhandledError
import com.example.domain.commons.HandledErrorMessage

fun CustomLogger.logHandledError(handledError: HandledError) {
    when (handledError) {
        is HandledErrorMessage ->{
            logError(handledError.message, this)
        }
        UnhandledError -> log("$GENERIC_MESSAGE_ERROR: $handledError")
    }
}

private fun logError(errorMessage: String?, customLogger: CustomLogger) {
    errorMessage.isNotNullAndBlank({ message ->
        customLogger.logImmediately(Log.ERROR, "Error", message)
    }, {
        customLogger.logImmediately(Log.ERROR, "Error", GENERIC_MESSAGE_ERROR)
    })
}

private const val GENERIC_MESSAGE_ERROR = "Handled Error"

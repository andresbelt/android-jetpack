package com.example.domain.commons

import com.example.data.error.GenericUserErrorContainer
import com.example.data.error.UserError.Type.EXCEPTION
import com.example.data.error.UserErrorContainer
import com.example.domain.commons.HandledError.ExceptionError
import com.example.domain.commons.HandledError.UnhandledError

//Everytime you add this class as Error, remember add to ErrorManager and CustomLogger.logHandledError
sealed class HandledError {
    data class ExceptionError(override val message: String = "Invalid params") : HandledError(), HandledErrorMessage
    object UnhandledError : HandledError()
}

sealed interface HandledErrorMessage{
    val message: String
}
object DomainErrorFactory {
    fun resolveError(userErrorContainer: UserErrorContainer?): HandledError {
        return when (userErrorContainer) {
            is GenericUserErrorContainer -> when (userErrorContainer.errorType) {
                EXCEPTION -> ExceptionError(userErrorContainer.message ?: "")
                else -> UnhandledError
            }
            else -> UnhandledError
        }
    }
}

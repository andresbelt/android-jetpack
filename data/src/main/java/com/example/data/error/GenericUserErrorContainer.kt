package com.example.data.error

import com.example.data.error.UserError.Type
import java.util.Objects

class GenericUserErrorContainer(errorType: Type, message: String?) : UserErrorContainer {
    var errorType: Type = errorType
        private set
    var message: String? = message
        private set

    override fun getUserError(): UserError {
        return UserError(errorType, message)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as GenericUserErrorContainer
        return errorType === that.errorType && message == that.message
    }

    override fun hashCode(): Int {
        return Objects.hash(errorType, message)
    }
}

package com.example.data.error

import com.example.data.error.UserError.Type.EXCEPTION
import com.example.data.error.UserError.Type.UNHANDLED
import java.util.Objects

class UserError {
    enum class Type {
        EXCEPTION, UNHANDLED
    }

    var type: Type
        private set
    var message: String? = null
        private set
    private var resourceId: Int? = null
    var title: String? = null
        private set

    constructor(type: Type, message: String?) {
        this.type = type
        this.message = message
    }

    constructor(type: Type, title: String?, message: String?) {
        this.type = type
        this.title = title
        this.message = message
    }

    /**
     * This method returns a message ready for being presented to the user.
     *
     * @param messageParser contains the implementation to get messages from context
     * @return A message for presenting to the user. `null` if message doesn't exists.
     */
    fun getMessage(messageParser: MessageParser): String? {
        if (message != null) {
            return message
        }
        if (resourceId != null) {
            return messageParser.getString(resourceId!!)
        }
        return when (type) {
            UNHANDLED -> messageParser.unhandledErrorMessage
            EXCEPTION -> messageParser.unhandledErrorMessage
        }
    }

    interface MessageParser {
        fun getString(resourceId: Int): String?
        val unhandledErrorMessage: String?
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val userError = other as UserError
        return type == userError.type && message == userError.message && resourceId == userError.resourceId
    }

    override fun hashCode(): Int {
        return Objects.hash(type, message, resourceId)
    }
}

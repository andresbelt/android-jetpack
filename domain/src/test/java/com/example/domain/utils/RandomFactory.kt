package com.example.domain.utils

import java.math.RoundingMode
import java.security.SecureRandom
import java.util.Date
import java.util.UUID

object RandomFactory {
    fun generateString(): String = UUID.randomUUID().toString()
    fun generateBoolean(): Boolean = SecureRandom().nextDouble() < 0.5
}

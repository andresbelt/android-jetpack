package com.example.commons

interface SuspendableResultUseCase<out Result, in Params> {
    suspend fun execute(params: Params? = null): Result
}

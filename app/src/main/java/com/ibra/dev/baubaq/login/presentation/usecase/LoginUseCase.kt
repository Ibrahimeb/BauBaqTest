package com.ibra.dev.baubaq.login.presentation.usecase

interface LoginUseCase {

    fun isAlreadyLogin(): Boolean

    fun verifyCredentials(user: String, password: String)
}
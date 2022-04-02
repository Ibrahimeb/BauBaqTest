package com.ibra.dev.baubaq.login.domain

import com.ibra.dev.baubaq.login.domain.repository.LoginRepository
import com.ibra.dev.baubaq.login.presentation.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUseCase {
    override fun isAlreadyLogin(): Boolean {
        return repository.isLoginAlReady()
    }

    override fun verifyCredentials(user: String, password: String) {

    }
}
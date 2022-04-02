package com.ibra.dev.baubaq.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.ibra.dev.baubaq.login.presentation.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun isAlreadyLogin(): Boolean {
        return loginUseCase.isAlreadyLogin()
    }

    fun verifyCredentials(user: String, password: String) {
        loginUseCase.verifyCredentials(user, password)
    }
}
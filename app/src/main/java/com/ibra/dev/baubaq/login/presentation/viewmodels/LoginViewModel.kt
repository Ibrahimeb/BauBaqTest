package com.ibra.dev.baubaq.login.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibra.dev.baubaq.R
import com.ibra.dev.baubaq.login.presentation.contract.FragmentLoginEvents
import com.ibra.dev.baubaq.login.presentation.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<FragmentLoginEvents>()
    val eventsLiveDataLiveData: LiveData<FragmentLoginEvents> get() = _eventsLiveData

    var userTextFieldValid: Boolean = false
    var passwordTextFieldValid: Boolean = false

    fun isAlreadyLogin(): Boolean {
        return loginUseCase.isAlreadyLogin()
    }

    fun verifyCredentials(user: String, password: String) {
        if (loginUseCase.verifyCredentials(user, password)) {
            _eventsLiveData.postValue(FragmentLoginEvents.GoToHome)
        } else {
            _eventsLiveData.postValue(FragmentLoginEvents.SendErrorMessage(R.string.error_message_credentials))
        }
    }

    fun canVerifyCredentials() {
        when {
            userTextFieldValid.not() -> _eventsLiveData.postValue(
                FragmentLoginEvents.SendErrorMessage(
                    R.string.error_message_empty_user
                )
            )
            passwordTextFieldValid.not() -> _eventsLiveData.postValue(
                FragmentLoginEvents.SendErrorMessage(
                    R.string.error_message_empty_password
                )
            )
            else -> _eventsLiveData.postValue(FragmentLoginEvents.LaunchVerifyCredentials)
        }
    }
}
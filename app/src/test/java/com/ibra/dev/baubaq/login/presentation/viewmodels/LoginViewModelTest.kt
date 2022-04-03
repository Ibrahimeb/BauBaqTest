package com.ibra.dev.baubaq.login.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibra.dev.baubaq.login.presentation.contract.FragmentLoginEvents
import com.ibra.dev.baubaq.login.presentation.usecase.LoginUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var loginUseCase: LoginUseCase

    lateinit var viewModel: LoginViewModel

    private val observer by lazy {
        viewModel.eventsLiveDataLiveData.test()
    }


    @Before
    fun setUp() {
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun verifyCredentialsReturnErrorCredentials() {
        `when`(loginUseCase.verifyCredentials(anyString(), anyString()))
            .thenReturn(false)
        viewModel.verifyCredentials(anyString(), anyString())

        observer.assertValue {
            it is FragmentLoginEvents.SendErrorMessage
        }
    }

    @Test
    fun verifyCredentialsReturnRightCredentials() {
        `when`(loginUseCase.verifyCredentials(anyString(), anyString()))
            .thenReturn(true)
        viewModel.verifyCredentials(anyString(), anyString())

        observer.assertValue {
            it is FragmentLoginEvents.GoToHome
        }
    }

    @Test
    fun `can Verify Credentials Should Return User Name Empty`() {
        viewModel.userTextFieldValid = false
        viewModel.canVerifyCredentials()
        observer.assertValue {
            it is FragmentLoginEvents.SendErrorMessage
        }
    }

    @Test
    fun `can Verify Credentials Should Return password Empty`() {
        viewModel.userTextFieldValid = true
        viewModel.passwordTextFieldValid = false
        viewModel.canVerifyCredentials()
        observer.assertValue {
            it is FragmentLoginEvents.SendErrorMessage
        }
    }

    @Test
    fun `can Verify Credentials Should Return launch credential verify`() {
        viewModel.userTextFieldValid = true
        viewModel.passwordTextFieldValid = true
        viewModel.canVerifyCredentials()
        observer.assertValue {
            it is FragmentLoginEvents.LaunchVerifyCredentials
        }
    }

    @Test
    fun `isAlreadyLogin should return false when preference return false`() {
        `when`(loginUseCase.isAlreadyLogin()).thenReturn(false)
        assert(viewModel.isAlreadyLogin().not())
    }

    @Test
    fun `isAlreadyLogin should return true when preference return true`() {
        `when`(loginUseCase.isAlreadyLogin()).thenReturn(true)
        assert(viewModel.isAlreadyLogin())
    }


}
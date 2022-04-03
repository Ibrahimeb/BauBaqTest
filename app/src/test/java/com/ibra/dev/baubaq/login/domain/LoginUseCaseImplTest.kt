package com.ibra.dev.baubaq.login.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibra.dev.baubaq.login.domain.repository.LoginRepository
import com.ibra.dev.baubaq.login.presentation.usecase.LoginUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyBoolean
import org.mockito.Mockito.anyString
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.eq
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: LoginRepository

    lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        loginUseCase = LoginUseCaseImpl(repository)
    }

    @Test
    fun `isAlreadyLogin should return false when preference return false`() {
        `when`(loginUseCase.isAlreadyLogin()).thenReturn(false)
        assert(loginUseCase.isAlreadyLogin().not())
    }

    @Test
    fun `isAlreadyLogin should return true when preference return true`() {
        `when`(loginUseCase.isAlreadyLogin()).thenReturn(true)
        assert(loginUseCase.isAlreadyLogin())
    }

    @Test
    fun `verifyCredentials should call preference repo functions`() {
        `when`(repository.userExist("")).thenReturn(true)
        `when`(repository.verifyPassWord("")).thenReturn(true)
        loginUseCase.verifyCredentials("", "")
        verify(repository).setIfLoginAlReady(true)
    }

    @Test
    fun `verifyCredentials should return false when user not exist`() {
        `when`(repository.userExist("")).thenReturn(false)
        assert(loginUseCase.verifyCredentials("", "").not())
    }

    @Test
    fun `verifyCredentials should return false when user exist and password is wrong`() {
        `when`(repository.userExist("")).thenReturn(true)
        `when`(repository.verifyPassWord("")).thenReturn(false)
        assert(loginUseCase.verifyCredentials("", "").not())
    }

    @Test
    fun `verifyCredentials should return true when user exist and password is correct`() {
        `when`(repository.userExist("")).thenReturn(true)
        `when`(repository.verifyPassWord("")).thenReturn(true)
        assert(loginUseCase.verifyCredentials("", ""))
    }
}
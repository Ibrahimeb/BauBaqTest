package com.ibra.dev.baubaq.login.data.repository

import com.ibra.dev.baubaq.login.data.contract.LoginPreference
import com.ibra.dev.baubaq.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginPreference: LoginPreference
) : LoginRepository {
    override fun isLoginAlReady(): Boolean {
        return loginPreference.isAlreadyLogin()
    }

    override fun setIfLoginAlReady(isAlready: Boolean) {
        loginPreference.setIfAlreadyLogin(isAlready)
    }

    override fun userExist(user: String): Boolean {
        return user == "test"
    }

    override fun verifyPassWord(password: String): Boolean {
        return password == "pass"
    }
}
package com.ibra.dev.baubaq.login.domain.repository

interface LoginRepository {

    fun isLoginAlReady(): Boolean

    fun userExist(user: String): Boolean

    fun verifyPassWord(password: String): Boolean

}
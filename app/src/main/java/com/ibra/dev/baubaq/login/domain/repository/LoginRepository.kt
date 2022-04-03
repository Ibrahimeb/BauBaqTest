package com.ibra.dev.baubaq.login.domain.repository

interface LoginRepository {

    fun isLoginAlReady(): Boolean

    fun setIfLoginAlReady(isAlready: Boolean)

    fun userExist(user: String): Boolean

    fun verifyPassWord(password: String): Boolean

}
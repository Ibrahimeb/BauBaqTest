package com.ibra.dev.baubaq.login.data.contract

interface LoginPreference {

    fun isAlreadyLogin(): Boolean

    fun setIfAlreadyLogin(isAlreadyLogin: Boolean)

    fun reset()
}
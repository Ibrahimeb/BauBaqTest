package com.ibra.dev.baubaq.login.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.ibra.dev.baubaq.login.data.contract.LoginPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginPreferenceImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ) : LoginPreference {

    private val preference: SharedPreferences by lazy {
        context.getSharedPreferences(
            LOGIN_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    override fun isAlreadyLogin(): Boolean {
        return preference.getBoolean(IS_LOGIN, false)
    }

    override fun setIfAlreadyLogin(isAlreadyLogin: Boolean) {
        preference.edit().putBoolean(IS_LOGIN, isAlreadyLogin).apply()
    }

    override fun reset() {
        preference.edit().clear().apply()
    }

    private companion object {
        const val LOGIN_PREFERENCES = "loginPreferences"
        const val IS_LOGIN = "isLogin"
    }
}
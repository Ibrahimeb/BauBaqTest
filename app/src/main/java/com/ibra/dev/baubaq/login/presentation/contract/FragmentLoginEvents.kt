package com.ibra.dev.baubaq.login.presentation.contract

sealed class FragmentLoginEvents {
    class SendErrorMessage(val msg: Int) : FragmentLoginEvents()
    object LaunchVerifyCredentials : FragmentLoginEvents()
    object GoToHome : FragmentLoginEvents()
}

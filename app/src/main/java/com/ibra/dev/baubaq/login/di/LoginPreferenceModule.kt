package com.ibra.dev.baubaq.login.di

import com.ibra.dev.baubaq.login.data.contract.LoginPreference
import com.ibra.dev.baubaq.login.data.preference.LoginPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class LoginPreferenceModule {
    @Binds
    abstract fun bindLoginPreference(preference: LoginPreferenceImpl): LoginPreference
}
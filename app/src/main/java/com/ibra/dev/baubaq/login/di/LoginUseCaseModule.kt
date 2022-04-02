package com.ibra.dev.baubaq.login.di

import com.ibra.dev.baubaq.login.domain.LoginUseCaseImpl
import com.ibra.dev.baubaq.login.presentation.usecase.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class LoginUseCaseModule {
    @Binds
    abstract fun bindLoginUseCase(useCase: LoginUseCaseImpl): LoginUseCase
}
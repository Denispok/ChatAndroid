package com.example.sampleapplication.di

import com.example.sampleapplication.ui.base.BaseViewModel
import com.example.sampleapplication.ui.screen.login.LoginViewModel
import com.example.sampleapplication.ui.screen.profile.edit.ProfileEditViewModel
import com.example.sampleapplication.ui.screen.registration.RegistrationViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ResourceModule::class, NetworkModule::class, AuthModule::class, RegistrationModule::class, ProfileModule::class, PreferencesModule::class])
interface AppComponent {

    fun inject(baseViewModel: BaseViewModel)

    fun inject(loginViewModel: LoginViewModel)
    fun inject(registrationViewModel: RegistrationViewModel)
    fun inject(profileEditViewModel: ProfileEditViewModel)
}

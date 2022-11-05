package com.test.boobluk.di.component

import com.test.boobluk.di.modules.AppModule
import com.test.boobluk.screens.activities.main.MainActivity
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordFragment
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment
import com.test.boobluk.screens.fragments.main.MainFragment
import com.test.boobluk.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(registerFragment: RegisterFragment)
    fun inject(forgotPasswordFragment: ForgotPasswordFragment)
}
package com.test.boobluk.di.modules

import com.test.boobluk.firebase.login.LoginFirebaseHelper
import com.test.boobluk.firebase.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.firebase.register.RegisterFirebaseHelper
import com.test.boobluk.screens.fragments.authentication.login.LoginViewModel
import com.test.boobluk.screens.fragments.authentication.login.LoginViewModelFactory
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordViewModelFactory
import com.test.boobluk.screens.fragments.authentication.register.RegisterViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideRegisterViewModelFactory(
        registerFirebaseHelper: RegisterFirebaseHelper
    ) : RegisterViewModelFactory {
        return RegisterViewModelFactory(
            firebaseHelper = registerFirebaseHelper
        )
    }

    @Provides
    fun provideForgotPasswordViewModelFactory(
        forgotPasswordFirebaseHelper: ForgotPasswordFirebaseHelper
    ) : ForgotPasswordViewModelFactory {
        return ForgotPasswordViewModelFactory(
            forgotPasswordFirebaseHelper = forgotPasswordFirebaseHelper
        )
    }

    @Provides
    fun provideLoginViewModelFactory(
        loginFirebaseHelper: LoginFirebaseHelper
    ) : LoginViewModelFactory {
        return LoginViewModelFactory(
            loginFirebaseHelper = loginFirebaseHelper
        )
    }

}
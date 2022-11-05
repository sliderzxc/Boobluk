package com.test.boobluk.di.modules

import com.test.boobluk.firebase.login.LoginFirebaseHelper
import com.test.boobluk.firebase.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.firebase.register.RegisterFirebaseHelper
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideRegisterFirebaseHelper() = RegisterFirebaseHelper()

    @Provides
    fun provideForgotPasswordFirebaseHelper() = ForgotPasswordFirebaseHelper()

    @Provides
    fun provideLoginFirebaseHelper() = LoginFirebaseHelper()
}
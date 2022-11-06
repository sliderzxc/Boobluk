package com.test.boobluk.di.modules

import com.test.boobluk.firebase.utils.login.LoginFirebaseHelper
import com.test.boobluk.firebase.utils.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.firebase.utils.register.RegisterFirebaseHelper
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
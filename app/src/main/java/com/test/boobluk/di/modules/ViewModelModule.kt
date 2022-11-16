package com.test.boobluk.di.modules

import com.test.boobluk.firebase.utils.login.LoginFirebaseHelper
import com.test.boobluk.firebase.utils.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.firebase.utils.profile.EditProfileFirebaseHelper
import com.test.boobluk.firebase.utils.register.RegisterFirebaseHelper
import com.test.boobluk.screens.fragments.authentication.login.LoginViewModelFactory
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordViewModelFactory
import com.test.boobluk.screens.fragments.authentication.register.RegisterViewModelFactory
import com.test.boobluk.screens.fragments.profile.EditProfileViewModelFactory
import com.test.boobluk.utils.image.ImageHelper
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideRegisterViewModelFactory(
        registerFirebaseHelper: RegisterFirebaseHelper
    ) : RegisterViewModelFactory {
        return RegisterViewModelFactory(
            registerFirebaseHelper = registerFirebaseHelper
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

    @Provides
    fun provideEditProfileViewModelFactory(
        editProfileFirebaseHelper: EditProfileFirebaseHelper,
        imageHelper: ImageHelper
    ) : EditProfileViewModelFactory {
        return EditProfileViewModelFactory(
            editProfileFirebaseHelper =  editProfileFirebaseHelper,
            imageHelper = imageHelper
        )
    }

}
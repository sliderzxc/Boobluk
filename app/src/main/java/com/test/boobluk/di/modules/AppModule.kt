package com.test.boobluk.di.modules

import com.test.boobluk.firebase.add.AddNewChatFirebaseHelper
import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
import com.test.boobluk.firebase.authentication.login.LoginFirebaseHelper
import com.test.boobluk.firebase.authentication.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.firebase.profile.EditProfileFirebaseHelper
import com.test.boobluk.firebase.authentication.register.RegisterFirebaseHelper
import com.test.boobluk.firebase.search.SearchFirebaseHelper
import com.test.boobluk.firebase.settings.SettingsFirebaseHelper
import com.test.boobluk.interfaces.add.AddNewChatFirebaseInterface
import com.test.boobluk.interfaces.authentication.login.LoginFirebaseInterface
import com.test.boobluk.interfaces.authentication.password.ForgotPasswordFirebaseInterface
import com.test.boobluk.interfaces.authentication.register.RegisterFirebaseInterface
import com.test.boobluk.interfaces.chat.ChatFirebaseInterface
import com.test.boobluk.utils.image.ImageHelper
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideRegisterFirebaseHelper() : RegisterFirebaseInterface {
        return RegisterFirebaseHelper()
    }

    @Provides
    fun provideForgotPasswordFirebaseHelper() : ForgotPasswordFirebaseInterface {
        return ForgotPasswordFirebaseHelper()
    }

    @Provides
    fun provideLoginFirebaseHelper() : LoginFirebaseInterface {
        return LoginFirebaseHelper()
    }

    @Provides
    fun provideEditProfileFirebaseHelper() = EditProfileFirebaseHelper()

    @Provides
    fun provideChatFirebaseHelper() : ChatFirebaseInterface {
        return ChatFirebaseHelper()
    }

    @Provides
    fun provideAddNewChatFirebaseHelper() : AddNewChatFirebaseInterface {
        return AddNewChatFirebaseHelper()
    }

    @Provides
    fun provideListOfChatsFirebaseHelper() = ListOfChatsFirebaseHelper()

    @Provides
    fun provideSearchFirebaseHelper() = SearchFirebaseHelper()

    @Provides
    fun provideSettingsFirebaseHelper() = SettingsFirebaseHelper()

    @Provides
    fun provideImageHelper() = ImageHelper()
}
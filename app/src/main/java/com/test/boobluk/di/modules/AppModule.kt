package com.test.boobluk.di.modules

import com.test.boobluk.firebase.add.AddNewChatFirebaseHelper
import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
import com.test.boobluk.firebase.authentication.login.LoginFirebaseHelper
import com.test.boobluk.firebase.authentication.password.ForgotPasswordFirebaseHelper
import com.test.boobluk.firebase.profile.EditProfileFirebaseHelper
import com.test.boobluk.firebase.register.RegisterFirebaseHelper
import com.test.boobluk.firebase.search.SearchFirebaseHelper
import com.test.boobluk.firebase.settings.SettingsFirebaseHelper
import com.test.boobluk.utils.image.ImageHelper
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

    @Provides
    fun provideEditProfileFirebaseHelper() = EditProfileFirebaseHelper()

    @Provides
    fun provideChatFirebaseHelper() = ChatFirebaseHelper()

    @Provides
    fun provideAddNewChatFirebaseHelper() = AddNewChatFirebaseHelper()

    @Provides
    fun provideListOfChatsFirebaseHelper() = ListOfChatsFirebaseHelper()

    @Provides
    fun provideSearchFirebaseHelper() = SearchFirebaseHelper()

    @Provides
    fun provideSettingsFirebaseHelper() = SettingsFirebaseHelper()

    @Provides
    fun provideImageHelper() = ImageHelper()
}
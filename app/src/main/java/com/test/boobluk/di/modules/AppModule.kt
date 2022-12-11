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
import com.test.boobluk.interfaces.chats.ListOfChatsFirebaseInterface
import com.test.boobluk.interfaces.profile.EditProfileFirebaseInterface
import com.test.boobluk.interfaces.search.SearchFirebaseInterface
import com.test.boobluk.interfaces.settings.SettingsFirebaseInterface
import com.test.boobluk.utils.binding.CardStackView
import com.test.boobluk.utils.image.ImageHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRegisterFirebaseHelper() : RegisterFirebaseInterface {
        return RegisterFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideForgotPasswordFirebaseHelper() : ForgotPasswordFirebaseInterface {
        return ForgotPasswordFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideLoginFirebaseHelper() : LoginFirebaseInterface {
        return LoginFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideEditProfileFirebaseHelper() : EditProfileFirebaseInterface {
        return EditProfileFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideChatFirebaseHelper() : ChatFirebaseInterface {
        return ChatFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideAddNewChatFirebaseHelper() : AddNewChatFirebaseInterface {
        return AddNewChatFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideListOfChatsFirebaseHelper() : ListOfChatsFirebaseInterface {
        return ListOfChatsFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideSearchFirebaseHelper(
        cardStackView: CardStackView
    ) : SearchFirebaseInterface {
        return SearchFirebaseHelper(cardStackView)
    }

    @Provides
    @Singleton
    fun provideSettingsFirebaseHelper() : SettingsFirebaseInterface {
        return SettingsFirebaseHelper()
    }

    @Provides
    @Singleton
    fun provideImageHelper() = ImageHelper()

    @Provides
    @Singleton
    fun provideCardStackView() = CardStackView()
}
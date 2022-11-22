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
import com.test.boobluk.screens.fragments.add.AddNewChatViewModelFactory
import com.test.boobluk.screens.fragments.authentication.login.LoginViewModelFactory
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordViewModelFactory
import com.test.boobluk.screens.fragments.authentication.register.RegisterViewModelFactory
import com.test.boobluk.screens.fragments.chat.ChatViewModelFactory
import com.test.boobluk.screens.fragments.chats.ListOfChatsViewModelFactory
import com.test.boobluk.screens.fragments.profile.EditProfileViewModelFactory
import com.test.boobluk.screens.fragments.search.SearchViewModelFactory
import com.test.boobluk.screens.fragments.settings.SettingsViewModelFactory
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

    @Provides
    fun provideChatViewModelFactory(
        chatFirebaseHelper: ChatFirebaseHelper
    ) : ChatViewModelFactory {
        return ChatViewModelFactory(
            chatFirebaseHelper = chatFirebaseHelper
        )
    }

    @Provides
    fun provideAddNewChatViewModelFactory(
        addNewChatFirebaseHelper: AddNewChatFirebaseHelper
    ) : AddNewChatViewModelFactory {
        return AddNewChatViewModelFactory(
            addNewChatFirebaseHelper = addNewChatFirebaseHelper
        )
    }

    @Provides
    fun provideListOfChatsViewModelFactory(
        listOfChatsFirebaseHelper: ListOfChatsFirebaseHelper
    ) : ListOfChatsViewModelFactory {
        return ListOfChatsViewModelFactory(
            listOfChatsFirebaseHelper = listOfChatsFirebaseHelper
        )
    }

    @Provides
    fun provideSearchViewModelFactory(
        searchFirebaseHelper: SearchFirebaseHelper
    ) : SearchViewModelFactory {
        return SearchViewModelFactory(
            searchFirebaseHelper = searchFirebaseHelper
        )
    }

    @Provides
    fun provideSettingsViewModelFactory(
        settingsFirebaseHelper: SettingsFirebaseHelper
    ) : SettingsViewModelFactory {
        return SettingsViewModelFactory(
            settingsFirebaseHelper = settingsFirebaseHelper
        )
    }
}
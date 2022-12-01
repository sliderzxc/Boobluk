package com.test.boobluk.di.modules

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
import com.test.boobluk.network.repository.NotificationRepository
import com.test.boobluk.network.viewmodel.NotificationViewModelFactory
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
        registerFirebaseInterface: RegisterFirebaseInterface
    ) : RegisterViewModelFactory {
        return RegisterViewModelFactory(
            registerFirebaseInterface = registerFirebaseInterface
        )
    }

    @Provides
    fun provideForgotPasswordViewModelFactory(
        forgotPasswordFirebaseInterface: ForgotPasswordFirebaseInterface
    ) : ForgotPasswordViewModelFactory {
        return ForgotPasswordViewModelFactory(
            forgotPasswordFirebaseInterface = forgotPasswordFirebaseInterface
        )
    }

    @Provides
    fun provideLoginViewModelFactory(
        loginFirebaseInterface: LoginFirebaseInterface
    ) : LoginViewModelFactory {
        return LoginViewModelFactory(
            loginFirebaseInterface = loginFirebaseInterface
        )
    }

    @Provides
    fun provideEditProfileViewModelFactory(
        editProfileFirebaseInterface: EditProfileFirebaseInterface,
        imageHelper: ImageHelper
    ) : EditProfileViewModelFactory {
        return EditProfileViewModelFactory(
            editProfileFirebaseInterface =  editProfileFirebaseInterface,
            imageHelper = imageHelper
        )
    }

    @Provides
    fun provideChatViewModelFactory(
        chatFirebaseInterface: ChatFirebaseInterface
    ) : ChatViewModelFactory {
        return ChatViewModelFactory(
            chatFirebaseInterface = chatFirebaseInterface
        )
    }

    @Provides
    fun provideAddNewChatViewModelFactory(
        addNewChatFirebaseInterface: AddNewChatFirebaseInterface
    ) : AddNewChatViewModelFactory {
        return AddNewChatViewModelFactory(
            addNewChatFirebaseInterface = addNewChatFirebaseInterface
        )
    }

    @Provides
    fun provideListOfChatsViewModelFactory(
        listOfChatsFirebaseInterface: ListOfChatsFirebaseInterface
    ) : ListOfChatsViewModelFactory {
        return ListOfChatsViewModelFactory(
            listOfChatsFirebaseInterface = listOfChatsFirebaseInterface
        )
    }

    @Provides
    fun provideSearchViewModelFactory(
        searchFirebaseInterface: SearchFirebaseInterface
    ) : SearchViewModelFactory {
        return SearchViewModelFactory(
            searchFirebaseInterface = searchFirebaseInterface
        )
    }

    @Provides
    fun provideSettingsViewModelFactory(
        settingsFirebaseInterface: SettingsFirebaseInterface
    ) : SettingsViewModelFactory {
        return SettingsViewModelFactory(
            settingsFirebaseInterface = settingsFirebaseInterface
        )
    }

    @Provides
    fun provideNotificationViewModelFactory(
        notificationRepository: NotificationRepository
    ) : NotificationViewModelFactory {
        return NotificationViewModelFactory(
            notificationRepository = notificationRepository
        )
    }
}
package com.test.boobluk.di.modules

import com.test.boobluk.firebase.chat.ChatFirebaseHelper
import com.test.boobluk.firebase.chats.ListOfChatsFirebaseHelper
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

    @Provides
    fun provideNotificationViewModelFactory(
        notificationRepository: NotificationRepository
    ) : NotificationViewModelFactory {
        return NotificationViewModelFactory(
            notificationRepository = notificationRepository
        )
    }
}
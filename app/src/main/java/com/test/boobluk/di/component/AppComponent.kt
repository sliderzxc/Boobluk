package com.test.boobluk.di.component

import com.test.boobluk.di.modules.AppModule
import com.test.boobluk.di.modules.RetrofitModule
import com.test.boobluk.screens.activities.main.MainActivity
import com.test.boobluk.screens.fragments.authentication.login.LoginFragment
import com.test.boobluk.screens.fragments.authentication.password.ForgotPasswordFragment
import com.test.boobluk.screens.fragments.authentication.register.RegisterFragment
import com.test.boobluk.screens.fragments.chats.ListOfChatsFragment
import com.test.boobluk.di.modules.ViewModelModule
import com.test.boobluk.screens.fragments.add.AddNewChatFragment
import com.test.boobluk.screens.fragments.chat.ChatFragment
import com.test.boobluk.screens.fragments.profile.EditProfileFragment
import com.test.boobluk.screens.fragments.search.SearchFragment
import com.test.boobluk.screens.fragments.settings.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, AppModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(listOfChatsFragment: ListOfChatsFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(registerFragment: RegisterFragment)
    fun inject(forgotPasswordFragment: ForgotPasswordFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(editProfileFragment: EditProfileFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(addNewChatFragment: AddNewChatFragment)
    fun inject(chatFragment: ChatFragment)
}
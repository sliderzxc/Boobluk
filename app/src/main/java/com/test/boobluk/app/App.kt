package com.test.boobluk.app

import android.app.Application
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.test.boobluk.data.entities.user.UserInfo
import com.test.boobluk.di.component.AppComponent
import com.test.boobluk.di.component.DaggerAppComponent
import com.test.boobluk.di.modules.AppModule
import com.test.boobluk.di.modules.RetrofitModule
import com.test.boobluk.di.modules.ViewModelModule
import com.test.boobluk.utils.constants.Constants.REFERENCE_USER_INFO

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.token.addOnSuccessListener { token ->
            val uid = Firebase.auth.currentUser?.uid.toString()
            Firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener { documentSnapshot ->
                val currentUser = documentSnapshot.toObject(UserInfo::class.java)
                currentUser?.token = token
                currentUser?.let { user -> Firebase.firestore.collection(REFERENCE_USER_INFO).document(uid).set(user) }
            }
        }
        appComponent = DaggerAppComponent
            .builder()
            .viewModelModule(ViewModelModule())
            .appModule(AppModule())
            .retrofitModule(RetrofitModule())
            .build()
    }
}
package com.test.boobluk.services

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.test.boobluk.data.entities.user.UserInfo
import com.test.boobluk.network.notification.showNotification
import com.test.boobluk.utils.constants.Constants

class FirebaseNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val titleAndUsername = message.notification?.title.toString()
        val content = message.notification?.body.toString()
        val context = this.baseContext

        showNotification(
            context = context,
            titleAndUsername = titleAndUsername,
            content = content
        )
        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val uid = Firebase.auth.currentUser?.uid.toString()
        Firebase.firestore.collection(Constants.REFERENCE_USER_INFO).document(uid).get().addOnSuccessListener { documentSnapshot ->
            val currentUser = documentSnapshot.toObject(UserInfo::class.java)
            currentUser?.token = token
            currentUser?.let { user -> Firebase.firestore.collection(Constants.REFERENCE_USER_INFO).document(uid).set(user) }
        }
    }


}
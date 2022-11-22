package com.test.boobluk.utils.constants

object Constants {
    const val EMAIL_ADDRESS_IS_BUSY = "The email address is already in use by another account."
    const val PASSWORD_IS_INCORRECT = "The password is invalid."
    const val EMAIL_ADDRESS_IS_INCORRECT = "The email address is badly formatted."
    const val EMAIL_WAS_NOT_FOUND = "Email address was not found"

    const val EMAIL_WAS_NOT_FOUND_INFO = "There is no user record corresponding to this identifier. The user may have been deleted."
    const val PASSWORD_IS_INCORRECT_INFO = "The password is invalid or the user does not have a password."

    const val REFERENCE_USER_INFO = "usersInfo"
    const val REFERENCE_USER_CHATS = "usersChats"
    const val REFERENCE_USERS_DATA = "usersData"
    const val REFERENCE_CHATS = "chats"
    const val REFERENCE_USERNAME = "username"
    const val REFERENCE_BIO = "bio"
    const val REFERENCE_INIT_REALTIME_DATABASE = "https://boobluk-default-rtdb.europe-west1.firebasedatabase.app/"
    const val REFERENCE_NEW_USER_AVATAR = "gs://boobluk.appspot.com/userAvatars/no-avatar.png"
    const val REFERENCE_PACKAGE_USER_AVATARS = "gs://boobluk.appspot.com/userAvatars"
    const val REFERENCE_RECEIVED_MESSAGES = "receivedMessages"
    const val REFERENCE_SENT_MESSAGES = "sentMessages"
    const val REFERENCE_DATA = "data"
    const val REFERENCE_LAST_EDIT_MESSAGE = "lastEditMessage"
}
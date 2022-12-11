package com.test.boobluk.utils.binding

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test.boobluk.adapter.SwipeCardAdapter
import com.test.boobluk.data.entities.user.UserInfo
import com.test.boobluk.databinding.FragmentSearchBinding
import com.test.boobluk.utils.constants.Constants
import com.yuyakaido.android.cardstackview.*

class CardStackView  {

    fun initCardStackView(
        firebase: Firebase,
        context: Context,
        binding: FragmentSearchBinding
    ) {
        val uid = firebase.auth.currentUser?.uid.toString()

        val adapter = SwipeCardAdapter()
        val listOfUsers = mutableListOf<UserInfo>()
        firebase.firestore.collection(Constants.REFERENCE_USER_INFO)
            .get()
            .addOnSuccessListener { users ->
                users.forEach { user ->
                    val userInfo = user.toObject(UserInfo::class.java)
                    if (userInfo.uid.toString() != uid) {
                        listOfUsers.add(userInfo)
                    }
                }
                adapter.addNewUsersList(listOfUsers)
                val layoutManager = CardStackLayoutManager(
                    context,
                    object : CardStackListener {
                        override fun onCardSwiped(direction: Direction?) {
                            direction?.let { thisDirection ->
                                when(thisDirection) {
                                    Direction.Right -> {

                                    }
                                    Direction.Left -> {

                                    }
                                    else -> {}
                                }
                            }
                        }

                        override fun onCardDragging(direction: Direction?, ratio: Float) {}
                        override fun onCardCanceled() {}
                        override fun onCardRewound() {}
                        override fun onCardAppeared(view: View?, position: Int) {}
                        override fun onCardDisappeared(view: View?, position: Int) {}
                    }
                )
                layoutManager.setCanScrollVertical(false)
                layoutManager.setStackFrom(StackFrom.Bottom)
                layoutManager.setTranslationInterval(6.0f)
                binding.cardStackView.layoutManager = layoutManager
                binding.cardStackView.adapter = adapter
                binding.floatingActionButton11.setOnClickListener {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(Duration.Slow.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    layoutManager.setSwipeAnimationSetting(setting)
                    binding.cardStackView.swipe()
                }
                binding.floatingActionButton22.setOnClickListener {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(Duration.Slow.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    layoutManager.setSwipeAnimationSetting(setting)
                    binding.cardStackView.swipe()
                }
            }
    }
}
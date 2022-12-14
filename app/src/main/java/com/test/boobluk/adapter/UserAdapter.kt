package com.test.boobluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.test.boobluk.R
import com.test.boobluk.data.entities.user.UserInfo
import com.test.boobluk.databinding.ItemAboutUserBinding

class UserAdapterDiffUtils(
    private val oldList: List<UserInfo>,
    private val newList: List<UserInfo>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.uid == newUser.uid && oldUser.username == newUser.username && oldUser.email == newUser.email &&
               oldUser.isEmailConfirmed == newUser.isEmailConfirmed && oldUser.gender == newUser.gender &&
               oldUser.avatar == newUser.avatar && oldUser.bio == newUser.bio
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser == newUser
    }
}

class UserAdapter(
    private val onBodyUserClick: (String, String) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var listOfUsersInfo = listOf<UserInfo>()

    class UserViewHolder(item: View) : ViewHolder(item) {
        val binding by lazy { ItemAboutUserBinding.bind(item) }
        fun bind(userInfo: UserInfo, onBodyUserClick: (String, String) -> Unit) {
            val uid = userInfo.uid.toString()
            val username = userInfo.username.toString()
            binding.tvUsername.text = userInfo.username
            binding.tvEmail.text = userInfo.email
            Glide.with(itemView).load(userInfo.avatar).into(binding.ivUserIcon)
            binding.bodyItemAboutUser.setOnClickListener { onBodyUserClick(uid, username) }
            binding.ivUserIcon.setOnClickListener { onBodyUserClick(uid, username) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_about_user, parent, false)
        return UserViewHolder(item)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listOfUsersInfo[position], onBodyUserClick = onBodyUserClick)
    }

    override fun getItemCount() = listOfUsersInfo.size

    fun setNewList(newListOfUsersInfo: MutableList<UserInfo>) {
        val diffUtilsCallback = UserAdapterDiffUtils(listOfUsersInfo, newListOfUsersInfo)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        listOfUsersInfo = newListOfUsersInfo
        difResult.dispatchUpdatesTo(this)
    }
}
package com.test.boobluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.test.boobluk.R
import com.test.boobluk.data.entities.UserInfo
import com.test.boobluk.databinding.ItemAboutUserBinding

class UserAdapterDiffUtils(
    private val oldList: List<UserInfo>,
    private val newList: List<UserInfo>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newToDo = newList[newItemPosition]
        return oldToDo.uid == newToDo.uid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newToDo = newList[newItemPosition]
        return oldToDo == newToDo
    }
}

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var listOfUsersInfo = listOf<UserInfo>()

    class UserViewHolder(item: View) : ViewHolder(item) {
        val binding by lazy { ItemAboutUserBinding.bind(item) }
        fun bind(userInfo: UserInfo) {
            binding.tvUsername.text = userInfo.username
            binding.tvEmail.text = userInfo.email
            Glide.with(itemView).load(userInfo.avatar).into(binding.ivUserIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_about_user, parent, false)
        return UserViewHolder(item)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listOfUsersInfo[position])
    }

    override fun getItemCount() = listOfUsersInfo.size

    fun setNewList(newListOfUsersInfo: MutableList<UserInfo>) {
        val diffUtilsCallback = UserAdapterDiffUtils(listOfUsersInfo, newListOfUsersInfo)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        listOfUsersInfo = newListOfUsersInfo
        difResult.dispatchUpdatesTo(this)
    }
}
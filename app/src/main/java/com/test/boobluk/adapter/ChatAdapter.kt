package com.test.boobluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.boobluk.R
import com.test.boobluk.data.entities.AboutChat
import com.test.boobluk.databinding.ItemAboutChatBinding

class ChatAdapterDiffUtils(
    private val oldList: List<AboutChat>,
    private val newList: List<AboutChat>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newToDo = newList[newItemPosition]
        return oldToDo.avatar == newToDo.avatar && oldToDo.lastMessage == newToDo.lastMessage && oldToDo.username == newToDo.avatar
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newToDo = newList[newItemPosition]
        return oldToDo == newToDo
    }
}

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var listOfAboutChats = listOf<AboutChat>()

    class ChatViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding by lazy { ItemAboutChatBinding.bind(item) }
        fun bind(aboutChat: AboutChat) {
            binding.tvUsername.text = aboutChat.username
            binding.tvLastMessage.text = aboutChat.lastMessage
            Glide.with(itemView).load(aboutChat.avatar).into(binding.ivUserIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_about_chat, parent, false)
        return ChatViewHolder(item)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(listOfAboutChats[position])
    }

    override fun getItemCount() = listOfAboutChats.size


    fun setNewList(newListOfAboutChats: MutableList<AboutChat>) {
        val diffUtilsCallback = ChatAdapterDiffUtils(listOfAboutChats, newListOfAboutChats)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        listOfAboutChats = newListOfAboutChats
        difResult.dispatchUpdatesTo(this)
    }
}
package com.test.boobluk.adapter

import android.annotation.SuppressLint
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
        val oldAboutChat = oldList[oldItemPosition]
        val newAboutChat = newList[newItemPosition]
        return oldAboutChat.username == newAboutChat.username && oldAboutChat.avatar == newAboutChat.avatar &&
                oldAboutChat.uid == newAboutChat.uid && oldAboutChat.lastMessage == newAboutChat.lastMessage
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAboutChat = oldList[oldItemPosition]
        val newAboutChat = newList[newItemPosition]
        return oldAboutChat == newAboutChat
    }
}

class ChatAdapter(
    private val onBodyUserClick: (String, String) -> Unit,
    private val onLongBodyUserClick: (String) -> Boolean
) : RecyclerView.Adapter<ChatAdapter.ListOfChatsViewHolder>() {
    private var listOfAboutChats = mutableListOf<AboutChat>()

    class ListOfChatsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding by lazy { ItemAboutChatBinding.bind(item) }

        @SuppressLint("SetTextI18n")
        fun bind(
            aboutChat: AboutChat,
            onBodyUserClick: (String, String) -> Unit,
            onLongBodyUserClick: (String) -> Boolean
        ) {
            binding.tvUsername.text = aboutChat.username
            //if
            if (aboutChat.lastMessage?.sentMessage == true) {
                binding.tvLastMessage.text = aboutChat.lastMessage?.message
            } else if (aboutChat.lastMessage?.receivedMessage == true) {
                binding.tvLastMessage.text = aboutChat.lastMessage?.message
            } else {
                binding.tvLastMessage.text = "You don't have any messages"
            }
            Glide.with(itemView).load(aboutChat.avatar).into(binding.ivUserIcon)
            binding.bodyItemAboutChat.setOnClickListener { onBodyUserClick(aboutChat.uid.toString(), aboutChat.username.toString()) }
            binding.ivUserIcon.setOnClickListener { onBodyUserClick(aboutChat.uid.toString(), aboutChat.username.toString()) }
            binding.bodyItemAboutChat.setOnLongClickListener {
                onLongBodyUserClick(aboutChat.uid.toString())
            }
            binding.ivUserIcon.setOnLongClickListener {
                onLongBodyUserClick(aboutChat.uid.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfChatsViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_about_chat, parent, false)
        return ListOfChatsViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListOfChatsViewHolder, position: Int) {
        holder.bind(listOfAboutChats[position], onBodyUserClick = onBodyUserClick, onLongBodyUserClick = onLongBodyUserClick)
    }

    override fun getItemCount() = listOfAboutChats.size

    @SuppressLint("NotifyDataSetChanged")
    fun addNewItem(aboutChat: AboutChat) {
        listOfAboutChats.add(aboutChat)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(aboutChat: AboutChat) {
        listOfAboutChats.remove(aboutChat)
        notifyDataSetChanged()
    }

    fun clearListOfChats() {
        listOfAboutChats = mutableListOf()
    }
}
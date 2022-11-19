package com.test.boobluk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.boobluk.R
import com.test.boobluk.data.entities.AboutChat
import com.test.boobluk.databinding.ItemAboutChatBinding

class ChatAdapter(
    private val onBodyUserClick: (String, String) -> Unit
) : RecyclerView.Adapter<ChatAdapter.ListOfChatsViewHolder>() {
    private var listOfAboutChats = mutableListOf<AboutChat>()

    class ListOfChatsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding by lazy { ItemAboutChatBinding.bind(item) }
        fun bind(aboutChat: AboutChat, onBodyUserClick: (String, String) -> Unit) {
            binding.tvUsername.text = aboutChat.username
            binding.tvLastMessage.text = aboutChat.lastMessage
            Glide.with(itemView).load(aboutChat.avatar).into(binding.ivUserIcon)
            binding.bodyItemAboutChat.setOnClickListener { onBodyUserClick(aboutChat.uid.toString(), aboutChat.username.toString()) }
            binding.ivUserIcon.setOnClickListener { onBodyUserClick(aboutChat.uid.toString(), aboutChat.username.toString()) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfChatsViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_about_chat, parent, false)
        return ListOfChatsViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListOfChatsViewHolder, position: Int) {
        holder.bind(listOfAboutChats[position], onBodyUserClick = onBodyUserClick)
    }

    override fun getItemCount() = listOfAboutChats.size

    @SuppressLint("NotifyDataSetChanged")
    fun addNewItem(aboutChat: AboutChat) {
        listOfAboutChats.add(aboutChat)
        notifyDataSetChanged()
    }

    fun clearListOfChats() {
        listOfAboutChats = mutableListOf()
    }
}
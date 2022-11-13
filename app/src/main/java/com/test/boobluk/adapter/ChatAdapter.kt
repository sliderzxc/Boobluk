package com.test.boobluk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.boobluk.R
import com.test.boobluk.data.entities.AboutChat
import com.test.boobluk.databinding.ItemAboutChatBinding


class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var listOfAboutChats = listOf(
        AboutChat("vadw", "dsald;s"),
        AboutChat("vfggfgfvadw", "dsaldsdasdsa;s"),
        AboutChat("vadwdsadasd", "dsald;s"),
        AboutChat("adsdsavadw", "dsald;s"),
        AboutChat("vasdsdsadw", "dsahhld;s"),
        AboutChat("vadw", "dsalghjghjd;s"),
        AboutChat("vadsdadsdw", "dsjjgjhjajgjsald;s"),
        AboutChat("vadw", "dsgjghjhald;s"),
        AboutChat("asdasvadaadw", "ddhjhgjhjsaald;s"),
        AboutChat("dasdasdsvadw", "dsaldsdsad;s"),
        AboutChat("dvsdasdsadw", "dsdsdsald;s"),
        AboutChat("dvsdasdsadw", "dsdsdsald;s"),
        AboutChat("dvsdasdsadw", "dsdsdsald;s"),
        AboutChat("dasdsadw", "dfgfgsald;s"),
        AboutChat(";dsl;adasdsadw", "dsdsdsald;s")
    )

    class ChatViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding by lazy { ItemAboutChatBinding.bind(item) }
        fun bind(aboutChat: AboutChat) {
            binding.tvUsername.text = aboutChat.username
            binding.tvLastMessage.text = aboutChat.lastMessage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_about_chat, parent, false)
        return ChatViewHolder(item)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(listOfAboutChats[position])
    }

    override fun getItemCount() = listOfAboutChats.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewList(newListOfAboutChats: MutableList<AboutChat>) {
        listOfAboutChats = newListOfAboutChats
        notifyDataSetChanged()
    }
}
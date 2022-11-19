package com.test.boobluk.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.boobluk.R
import com.test.boobluk.data.entities.MessageForClient
import com.test.boobluk.databinding.ItemReceivedMessageBinding
import com.test.boobluk.databinding.ItemSentMessageBinding
import com.test.boobluk.utils.message.MessageType

class MessageAdapter(
    private val interlocutorUsername: String
) : RecyclerView.Adapter<MessageAdapter.ChatViewHolder>() {
    private var listOfMessages = mutableListOf<MessageForClient>()

    class ChatViewHolder(view: View) : ViewHolder(view) {
        private val receivedMessageBinding by lazy { ItemReceivedMessageBinding.bind(view) }
        private val sentMessageBinding by lazy { ItemSentMessageBinding.bind(view) }
        fun bind(message: MessageForClient, interlocutorUsername: String) {
            if (message.isReceivedMessage == true) {
                receivedMessageBinding.tvMessage.text = message.message
                receivedMessageBinding.tvUsername.text = interlocutorUsername
                receivedMessageBinding.tvMessageTime.text = message.data
            } else {
                sentMessageBinding.tvMessage.text = message.message
                sentMessageBinding.tvMessageTime.text = message.data
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return when (viewType) {
            MessageType.SentMessage.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sent_message, parent, false)
                ChatViewHolder(view)
            }
            MessageType.ReceivedMessage.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_received_message, parent, false)
                ChatViewHolder(view)
            }
            else -> {
                Log.d("MyLog", "Ordinal == other")
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sent_message, parent, false)
                ChatViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(listOfMessages[position], interlocutorUsername = interlocutorUsername)
    }

    override fun getItemCount() = listOfMessages.size

    override fun getItemViewType(position: Int): Int {
        return if (listOfMessages[position].isSentMessage == true) {
            MessageType.SentMessage.ordinal
        } else if (listOfMessages[position].isReceivedMessage == true) {
            MessageType.ReceivedMessage.ordinal
        } else {
            MessageType.Nothing.ordinal
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun  addData(message: MessageForClient) {
        listOfMessages.add(message)
        listOfMessages.sortBy { it.sharedData }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeData(message: MessageForClient) {
        listOfMessages.remove(message)
        notifyDataSetChanged()
    }

}
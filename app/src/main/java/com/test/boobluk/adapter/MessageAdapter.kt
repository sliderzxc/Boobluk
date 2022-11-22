package com.test.boobluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.boobluk.R
import com.test.boobluk.data.entities.Message
import com.test.boobluk.databinding.ItemReceivedMessageBinding
import com.test.boobluk.databinding.ItemSentMessageBinding
import com.test.boobluk.utils.message.MessageType

class MessageAdapterDiffUtils(
    private val oldList: List<Message>,
    private val newList: List<Message>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMessage = oldList[oldItemPosition]
        val newMessage = newList[newItemPosition]
        return oldMessage.data == newMessage.data && oldMessage.message == newMessage.message &&
                oldMessage.sharedData == newMessage.sharedData && oldMessage.sharedDataForSort == newMessage.sharedDataForSort
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMessage = oldList[oldItemPosition]
        val newMessage = newList[newItemPosition]
        return oldMessage == newMessage
    }
}

class MessageAdapter(
    private val interlocutorUsername: String,
    private val showDialogEditMessage: (Message) -> (Unit),
) : RecyclerView.Adapter<MessageAdapter.ChatViewHolder>() {
    private var listOfMessages = mutableListOf<Message>()

    class ChatViewHolder(view: View) : ViewHolder(view) {
        private val receivedMessageBinding by lazy { ItemReceivedMessageBinding.bind(view) }
        private val sentMessageBinding by lazy { ItemSentMessageBinding.bind(view) }

        fun bind(
            message: Message,
            interlocutorUsername: String,
            showDialogEditMessage: (Message) -> (Unit),
        ) {
            if (message.isReceivedMessage == true) {
                receivedMessageBinding.tvMessage.text = message.message
                receivedMessageBinding.tvUsername.text = interlocutorUsername
                receivedMessageBinding.tvMessageTime.text = message.data
            } else {
                sentMessageBinding.tvMessage.text = message.message
                sentMessageBinding.tvMessageTime.text = message.sharedData
                sentMessageBinding.bodyItem.setOnLongClickListener {
                    showDialogEditMessage(message)
                    true
                }
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
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sent_message, parent, false)
                ChatViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(
            message = listOfMessages[position],
            interlocutorUsername = interlocutorUsername,
            showDialogEditMessage = showDialogEditMessage
        )
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

    fun addData(message: Message) {
        val oldList = listOfMessages.toList()
        listOfMessages.add(message)
        listOfMessages.sortBy { it.sharedDataForSort }
        val diffUtilsCallback = MessageAdapterDiffUtils(oldList, listOfMessages)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        difResult.dispatchUpdatesTo(this)
    }

    fun removeData(message: Message) {
        val oldList = listOfMessages.toList()
        listOfMessages.remove(message)
        val diffUtilsCallback = MessageAdapterDiffUtils(oldList, listOfMessages)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        difResult.dispatchUpdatesTo(this)
    }

    fun updateData(oldMessage: Message, newMessage: Message) {
        val oldList = listOfMessages.toList()
        listOfMessages.remove(oldMessage)
        listOfMessages.add(newMessage)
        listOfMessages.sortBy { it.sharedDataForSort }
        val diffUtilsCallback = MessageAdapterDiffUtils(oldList, listOfMessages)
        val difResult = DiffUtil.calculateDiff(diffUtilsCallback, true)
        difResult.dispatchUpdatesTo(this)
    }

    fun clearAllMessages() {
        listOfMessages = mutableListOf()
    }

}
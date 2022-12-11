package com.test.boobluk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.boobluk.R
import com.test.boobluk.data.entities.user.UserInfo
import com.test.boobluk.databinding.ItemUserCardViewBinding

class SwipeCardAdapter : Adapter<SwipeCardAdapter.SwipeCardViewHolder>() {
    private val listOfUsersCard: MutableList<UserInfo> = mutableListOf()

    class SwipeCardViewHolder(view: View): ViewHolder(view) {
        val binding by lazy { ItemUserCardViewBinding.bind(view) }
        fun bind(userInfo: UserInfo) {
            binding.textView9.text = userInfo.username
            Glide.with(itemView)
                .load(userInfo.avatar)
                .apply(RequestOptions().override(1000).centerCrop())
                .into(binding.shapeableImageView9)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_card_view, parent, false)
        return SwipeCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: SwipeCardViewHolder, position: Int) {
        val userInfo = listOfUsersCard.random()
        holder.bind(userInfo)
    }

    override fun getItemCount() = Int.MAX_VALUE

    fun addNewUsersList(listOfUserInfo: List<UserInfo>) {
        listOfUserInfo.forEach { userInfo ->
            listOfUsersCard.add(userInfo)
        }
    }
}
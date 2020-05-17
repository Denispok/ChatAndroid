package com.example.sampleapplication.ui.screen.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.model.common.Chat
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatsAdapter : RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    var items: List<Chat> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(chat: Chat) {
            itemView.tvName.text = chat.name
        }
    }
}

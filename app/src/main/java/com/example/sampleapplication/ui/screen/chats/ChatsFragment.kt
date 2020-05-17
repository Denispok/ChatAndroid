package com.example.sampleapplication.ui.screen.chats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    private val viewModel: ChatsViewModel by viewModels()
    lateinit var chatsAdapter: ChatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatsAdapter = ChatsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivEdit.setOnClickListener { findNavController().navigate(ChatsFragmentDirections.actionChatsFragmentToProfileEditFragment()) }
        rvChats.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvChats.adapter = chatsAdapter
    }

}

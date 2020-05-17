package com.example.sampleapplication.ui.screen.profile.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile_edit.*

class ProfileEditFragment : BaseFragment(R.layout.fragment_profile_edit) {

    override val viewModel: ProfileEditViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        btnEdit.setOnClickListener { viewModel.onEditClick(etUsername.text?.toString(), etPassword.text?.toString()) }
    }

    private fun initObserves() {
        viewModel.transactions.observe(viewLifecycleOwner) {
            if (it is ProfileEditViewModel.Transaction.BackTransaction)
                findNavController().popBackStack()
        }
    }
}

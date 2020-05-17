package com.example.sampleapplication.ui.screen.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    override val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        btnRegister.setOnClickListener { viewModel.onRegisterClick(etUsername.text.toString(), etPassword.text.toString()) }
    }

    private fun initObserves() {
        viewModel.transactions.observe(viewLifecycleOwner) {
            if (it is RegistrationViewModel.Transaction.BackTransaction)
                findNavController().popBackStack()
        }
    }
}

package com.example.sampleapplication.ui.screen.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.sampleapplication.R
import com.example.sampleapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserves()
        btnSignIn.setOnClickListener { viewModel.onSignInClick(etUsername.text.toString(), etPassword.text.toString()) }
        btnRegistration.setOnClickListener { viewModel.onRegisterClick() }
    }

    private fun initObserves() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                LoginViewModel.State.NONE -> {
                    etUsername.isEnabled = true
                    etPassword.isEnabled = true
                    btnSignIn.isEnabled = true
                    btnRegistration.isEnabled = true
                }

                LoginViewModel.State.LOADING -> {
                    etUsername.isEnabled = false
                    etPassword.isEnabled = false
                    btnSignIn.isEnabled = false
                    btnRegistration.isEnabled = false
                }
            }
        }

        viewModel.transactions.observe(viewLifecycleOwner) {
            if (it is LoginViewModel.Transaction.ChatsTransaction)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToChatsFragment())
            else if (it is LoginViewModel.Transaction.RegistrationTransaction)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }
    }
}

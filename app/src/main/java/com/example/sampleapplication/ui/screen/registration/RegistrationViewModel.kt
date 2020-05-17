package com.example.sampleapplication.ui.screen.registration

import com.example.sampleapplication.App
import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.repository.interfaze.RegistrationRepository
import com.example.sampleapplication.ui.base.BaseViewModel
import com.example.sampleapplication.ui.base.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel : BaseViewModel() {

    @Inject
    lateinit var registrationRepository: RegistrationRepository

    init {
        App.appComponent.inject(this)
    }

    val transactions = SingleLiveEvent<Transaction>()

    fun onRegisterClick(login: String, password: String) {
        launch {
            val response = registrationRepository.signUp(login, password)
            when (response) {
                is Value.Success -> {
                    transactions.postValue(Transaction.BackTransaction)
                }

                is Value.Error -> {
                    postError(response.throwable)
                }
            }
        }
    }

    sealed class Transaction {
        object BackTransaction : Transaction()
    }
}

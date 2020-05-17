package com.example.sampleapplication.ui.screen.login

import androidx.lifecycle.MutableLiveData
import com.example.sampleapplication.App
import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.repository.interfaze.AuthRepository
import com.example.sampleapplication.model.exception.NotFoundException
import com.example.sampleapplication.ui.base.BaseViewModel
import com.example.sampleapplication.ui.base.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel : BaseViewModel() {

    @Inject
    lateinit var authRepository: AuthRepository

    init {
        App.appComponent.inject(this)
    }

    val state = MutableLiveData<State>()
    val transactions = SingleLiveEvent<Transaction>()

    fun onSignInClick(username: String, password: String) = launch {
        state.postValue(State.LOADING)
        val response = authRepository.signIn(username, password)
        when (response) {
            is Value.Success -> {
                transactions.postValue(Transaction.ChatsTransaction)
            }

            is Value.Error -> {
                when (response.throwable) {
                    is NotFoundException -> postError(stringManager.getLoginWrongUsernameOrPassword())
                    else -> postError(response.throwable)
                }
            }
        }
        state.postValue(State.NONE)
    }

    fun onRegisterClick() {
        transactions.postValue(Transaction.RegistrationTransaction)
    }

    enum class State {
        NONE,
        LOADING
    }

    sealed class Transaction {
        object ChatsTransaction : Transaction()
        object RegistrationTransaction : Transaction()
    }
}

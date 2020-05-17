package com.example.sampleapplication.ui.screen.profile.edit

import com.example.sampleapplication.App
import com.example.sampleapplication.data.Value
import com.example.sampleapplication.data.repository.interfaze.ProfileRepository
import com.example.sampleapplication.ui.base.BaseViewModel
import com.example.sampleapplication.ui.base.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileEditViewModel : BaseViewModel() {

    @Inject
    lateinit var profileRepository: ProfileRepository

    init {
        App.appComponent.inject(this)
    }

    val transactions = SingleLiveEvent<Transaction>()

    fun onEditClick(login: String?, password: String?) {
        launch {
            val response = profileRepository.edit(convertBlankToNull(login), convertBlankToNull(password))
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

    private fun convertBlankToNull(string: String?): String? {
        return if (string.isNullOrBlank()) null
        else string
    }

    sealed class Transaction {
        object BackTransaction : Transaction()
    }
}

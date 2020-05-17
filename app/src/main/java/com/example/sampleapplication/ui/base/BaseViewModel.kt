package com.example.sampleapplication.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapplication.App
import com.example.sampleapplication.data.manager.StringManager
import com.example.sampleapplication.model.exception.NetworkError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private var viewModelJob = SupervisorJob()

    @Inject
    lateinit var stringManager: StringManager

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> = _error

    init {
        App.appComponent.inject(this)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + viewModelJob

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    protected fun postError(message: String) {
        _error.postValue(message)
    }

    protected fun postError(throwable: Throwable) {
        postError(
            when (throwable) {
                is NetworkError -> stringManager.getNetworkError()
                else -> stringManager.getUnknownError()
            }
        )
    }
}

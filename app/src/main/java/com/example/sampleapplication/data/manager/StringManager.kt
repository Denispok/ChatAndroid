package com.example.sampleapplication.data.manager

import android.content.Context
import androidx.annotation.StringRes
import com.example.sampleapplication.R

class StringManager(private val applicationContext: Context) {

    private fun getString(@StringRes resId: Int): String {
        return applicationContext.getString(resId)
    }

    fun getUnknownError() = getString(R.string.unknown_error)
    fun getNetworkError() = getString(R.string.network_error)

    fun getLoginWrongUsernameOrPassword() = getString(R.string.login_wrong_username_or_password)
}

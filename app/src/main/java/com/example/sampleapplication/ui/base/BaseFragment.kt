package com.example.sampleapplication.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}

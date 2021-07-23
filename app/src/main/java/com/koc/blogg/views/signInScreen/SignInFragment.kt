package com.koc.blogg.views.signInScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.koc.blogg.R
import com.koc.blogg.databinding.FragmentSignInBinding
import com.koc.blogg.util.commons.BaseFragment
import com.koc.blogg.util.events.LoginEvent
import com.koc.blogg.util.events.exhaustive
import com.koc.blogg.viewModel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
Created by kelvin_clark on 7/23/2021 9:21 PM
 */
@AndroidEntryPoint
class SignInFragment: BaseFragment<FragmentSignInBinding>() {

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            etEmail.setText(viewModel.email)
            etPassword.setText(viewModel.password)
            loginBtn.setOnClickListener {
                viewModel.loginUser()
            }
            signUp.setOnClickListener {
                navController.navigate(R.id.signUpFragment)
            }
        }
        saveFragmentState()
        monitorFragmentEvents()
    }

    private fun monitorFragmentEvents() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.loginEvent.collect {loginEvent ->
            when(loginEvent) {
                is LoginEvent.InvalidEmail -> {
                    binding.etEmail.error = getString(R.string.email_invalid)
                }
                is LoginEvent.InvalidPassword -> {
                    binding.etPassword.error = getString(R.string.password_empty)
                }
                is LoginEvent.ProcessingAuthentication -> {
                    binding.apply {
                        etEmail.error = null
                        etPassword.error = null
                    }
                }
                is LoginEvent.ErrorLogin -> {
                    Snackbar.make(binding.root, getString(R.string.server_error), Snackbar.LENGTH_SHORT).show()
                }
                is LoginEvent.LoginSuccessFull -> {
                    // TODO: Navigate to Blog List (Screen Not ready)
                }
            }.exhaustive
        }
    }


    private fun saveFragmentState() {
        binding.apply {
            etEmail.doOnTextChanged { text, _, _, _ ->
                viewModel.email = text.toString()
            }
            etPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.password = text.toString()
            }
        }
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSignInBinding = FragmentSignInBinding.inflate(layoutInflater, viewGroup, false)
}
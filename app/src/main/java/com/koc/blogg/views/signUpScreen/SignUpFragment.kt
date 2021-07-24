package com.koc.blogg.views.signUpScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.koc.blogg.R
import com.koc.blogg.databinding.FragmentSignUpBinding
import com.koc.blogg.util.commons.BaseFragment
import com.koc.blogg.util.events.SignUpEvent
import com.koc.blogg.util.events.exhaustive
import com.koc.blogg.viewModel.SignUpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
Created by kelvin_clark on 7/23/2021 9:59 PM
 */
@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel: SignUpFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            etUserName.setText(viewModel.username)
            etEmail.setText(viewModel.email)
            etPassword.setText(viewModel.password)
            etConfirmPassword.setText(viewModel.confirmPassword)
            signUpBtn.setOnClickListener {
                viewModel.registerUser()
            }
            login.setOnClickListener {
                navController.navigate(R.id.signUp_to_signin)
            }
        }
        saveFragmentState()
        monitorFragmentEvents()
    }

    private fun monitorFragmentEvents() = viewLifecycleOwner.lifecycleScope.launchWhenCreated{
        viewModel.signUpEvent.collect { signUpEvent ->
            when(signUpEvent) {
                is SignUpEvent.EmailInvalid -> {
                    binding.etEmail.error = getString(R.string.email_invalid)
                }
                is SignUpEvent.PasswordInvalid -> {
                    binding.etPassword.error = getString(R.string.password_length_error)
                }
                is SignUpEvent.PasswordNotMatching -> {
                    binding.etConfirmPassword.error = getString(R.string.password_match_error)
                }
                is SignUpEvent.UserNameInvalid -> {
                    binding.etUserName.error = getString(R.string.username_length_error)
                }
                is SignUpEvent.ProcessingSignUp -> {
                    binding.apply {
                        etEmail.error = null
                        etPassword.error = null
                        etUserName.error = null
                        etConfirmPassword.error = null
                    }
                }
                is SignUpEvent.SignUpFailed -> {
                    Snackbar.make(binding.root, getString(R.string.email_username_exits), Snackbar.LENGTH_SHORT).show()
                }
                is SignUpEvent.SignUpSuccessFul -> {
                    navController.navigate(R.id.signUp_to_list)
                }
            }.exhaustive
        }
    }

    private fun saveFragmentState() {
        binding.apply {
            etUserName.doOnTextChanged { text, _, _, _ ->
                viewModel.username = text.toString()
            }
            etEmail.doOnTextChanged { text, _, _, _ ->
                viewModel.email = text.toString()
            }
            etPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.password = text.toString()
            }
            etConfirmPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.confirmPassword = text.toString()
            }
        }
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(layoutInflater, viewGroup, false)
}
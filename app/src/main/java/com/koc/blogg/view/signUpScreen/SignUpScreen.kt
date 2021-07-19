package com.koc.blogg.view.signUpScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.koc.blogg.R
import com.koc.blogg.databinding.SignupScreenBinding
import com.koc.blogg.util.SignUpEvent
import com.koc.blogg.util.exhaustive
import com.koc.blogg.viewModel.SignUpScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
Created by kelvin_clark on 7/17/2021 7:59 PM
 */
@AndroidEntryPoint
class SignUpScreen: Fragment() {
    private var _binding: SignupScreenBinding? = null
    private val binding
        get() = _binding!!

    val viewModel: SignUpScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupScreenBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            etEmail.setText(viewModel.email)
            etPassword.setText(viewModel.password)
            etConfirmPassword.setText(viewModel.confirmPassword)
            etUsername.setText(viewModel.username)

            login.setOnClickListener {
                findNavController().navigate(R.id.signUpScreen_to_loginScreen)
            }
            signUpBtn.setOnClickListener {
                viewModel.registerUser()
            }
        }
        saveFormState()
        monitorSignUpEvents()
    }

    private fun monitorSignUpEvents() = lifecycleScope.launchWhenCreated {
        viewModel.signUpEvent.collect {event ->
            when(event) {
                is SignUpEvent.EmailInvalid -> {
                    binding.etEmail.error = getString(R.string.email_invalid)
                }

                is SignUpEvent.PasswordInvalid -> {
                    binding.etPassword.error = getString(R.string.password_length_error)
                }

                is SignUpEvent.PasswordNotMatching -> {
                    binding.etPassword.error = getString(R.string.password_match_error)
                }

                is SignUpEvent.UserNameInvalid -> {
                    binding.etUsername.error = getString(R.string.username_length_error)
                }
            }.exhaustive
        }
    }

    private fun saveFormState() {
        binding.apply {
            etEmail.doOnTextChanged { text, _, _, _ ->
                viewModel.email = text.toString()
            }
            etPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.password = text.toString()
            }
            etConfirmPassword.doOnTextChanged { text, _, _, _ ->
                viewModel.confirmPassword = text.toString()
            }
            etUsername.doOnTextChanged { text, _, _, _ ->
                viewModel.username = text.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
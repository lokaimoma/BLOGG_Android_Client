package com.koc.blogg.view.loginScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.koc.blogg.databinding.LoginScreenBinding
import com.koc.blogg.util.LoginEvent
import com.koc.blogg.util.exhaustive
import com.koc.blogg.viewModel.LoginScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
Created by kelvin_clark on 7/14/2021 6:36 PM
 */
@AndroidEntryPoint
class LoginScreen: Fragment() {


    val viewModel: LoginScreenViewModel by viewModels()

    private var _binding: LoginScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginScreenBinding.inflate(inflater,container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            etEmail.setText(viewModel.email)
            etPassword.setText(viewModel.password)
            loginBtn.setOnClickListener {
                viewModel.loginUser()
            }
        }

        saveFormState()
        monitorEvents()
    }

    private fun monitorEvents() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.loginEvent.collect { event: LoginEvent ->
            when(event) {
                is LoginEvent.LoginSuccessFull -> {
                    Snackbar.make(requireContext(), binding.root,
                        "Login Successful User Id: ${event.userId}",
                        Snackbar.LENGTH_SHORT).show()
                }

                is LoginEvent.ErrorLogin -> {
                    Snackbar.make(requireContext(), binding.root,
                        event.message,
                        Snackbar.LENGTH_LONG).show()
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
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
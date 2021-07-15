package com.koc.blogg.view.loginScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.koc.blogg.databinding.LoginScreenBinding
import com.koc.blogg.viewModel.LoginScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
        }

        saveFormState()
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
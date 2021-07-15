package com.koc.blogg.view.loginScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koc.blogg.databinding.LoginScreenBinding

/**
Created by kelvin_clark on 7/14/2021 6:36 PM
 */
class LoginScreen: Fragment() {
    private var _binding: LoginScreenBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginScreenBinding.inflate(inflater,container, false)
        return _binding?.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
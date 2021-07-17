package com.koc.blogg.view.signUpScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koc.blogg.databinding.SignupScreenBinding
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/17/2021 7:59 PM
 */
@AndroidEntryPoint
class signUpScreen: Fragment() {
    private var _binding: SignupScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupScreenBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
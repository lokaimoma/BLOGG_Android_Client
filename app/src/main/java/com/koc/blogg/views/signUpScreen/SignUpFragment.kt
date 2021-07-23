package com.koc.blogg.views.signUpScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koc.blogg.databinding.FragmentSignUpBinding
import com.koc.blogg.util.commons.BaseFragment

/**
Created by kelvin_clark on 7/23/2021 9:59 PM
 */
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(layoutInflater, viewGroup, false)
}
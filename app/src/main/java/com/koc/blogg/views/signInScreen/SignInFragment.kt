package com.koc.blogg.views.signInScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koc.blogg.databinding.FragmentSignInBinding
import com.koc.blogg.util.commons.BaseFragment

/**
Created by kelvin_clark on 7/23/2021 9:21 PM
 */
class SignInFragment: BaseFragment<FragmentSignInBinding>() {

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSignInBinding = FragmentSignInBinding.inflate(layoutInflater, viewGroup, false)
}
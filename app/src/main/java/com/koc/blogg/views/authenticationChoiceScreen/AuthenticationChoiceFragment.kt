package com.koc.blogg.views.authenticationChoiceScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koc.blogg.databinding.FragmentAuthenticatinoChoiceBinding
import com.koc.blogg.util.commons.BaseFragment

/**
Created by kelvin_clark on 7/23/2021 10:44 PM
 */
class AuthenticationChoiceFragment: BaseFragment<FragmentAuthenticatinoChoiceBinding>(){
    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentAuthenticatinoChoiceBinding =
        FragmentAuthenticatinoChoiceBinding.inflate(layoutInflater, viewGroup, false)


}
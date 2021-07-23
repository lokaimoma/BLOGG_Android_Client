package com.koc.blogg.views.splashScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koc.blogg.databinding.FragmentSplashScreenBinding
import com.koc.blogg.util.commons.BaseFragment


class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentSplashScreenBinding =
        FragmentSplashScreenBinding.inflate(layoutInflater, viewGroup, false)

}
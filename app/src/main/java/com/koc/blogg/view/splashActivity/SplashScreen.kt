package com.koc.blogg.view.splashActivity

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.koc.blogg.databinding.SplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/16/2021 12:18 AM
 */
@AndroidEntryPoint
class SplashScreen: AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.3f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.3f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(binding.logo, scaleX, scaleY)
        animator.apply {
            duration = 500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

}
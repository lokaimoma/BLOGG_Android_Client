package com.koc.blogg.view.splashActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.koc.blogg.databinding.SplashScreenBinding
import com.koc.blogg.util.PreferenceManager
import com.koc.blogg.view.authenticationActivity.AuthenticationActivity
import com.koc.blogg.view.blogActivity.BlogActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by kelvin_clark on 7/16/2021 12:18 AM
 */
@AndroidEntryPoint
class SplashScreen: AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding
    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        checkIfUserIsRegistered()
    }

    private fun checkIfUserIsRegistered() = lifecycleScope.launch(IO) {
        preferenceManager.userId.first {userId ->
            val intent = if (userId == null) {
                Intent(this@SplashScreen, AuthenticationActivity::class.java)
            }else {
                Intent(this@SplashScreen, BlogActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            true
        }
    }

}
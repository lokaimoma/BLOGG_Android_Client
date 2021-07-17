package com.koc.blogg.view.authenticationActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.koc.blogg.R
import com.koc.blogg.databinding.ActivityAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolbar: Toolbar = binding.toolbar

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        val navController = navHost.navController
        val appBarConfig = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfig)
    }

}
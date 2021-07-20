package com.koc.blogg.view.blogActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.koc.blogg.R
import com.koc.blogg.databinding.BlogActivityBinding
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/20/2021 1:59 PM
 */
@AndroidEntryPoint
class BlogActivity: AppCompatActivity() {

    private lateinit var binding: BlogActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BlogActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        val appbarConfig = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appbarConfig)
    }
}
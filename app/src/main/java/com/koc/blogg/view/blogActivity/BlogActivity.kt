package com.koc.blogg.view.blogActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }
}
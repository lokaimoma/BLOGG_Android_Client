package com.koc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koc.blogg.R
import com.koc.blogg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.ibra.dev.baubaq.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibra.dev.baubaq.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
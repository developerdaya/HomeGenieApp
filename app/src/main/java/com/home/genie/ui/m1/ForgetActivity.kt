package com.home.genie.ui.m1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.home.genie.R
import com.home.genie.databinding.ActivityForgetBinding

class ForgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
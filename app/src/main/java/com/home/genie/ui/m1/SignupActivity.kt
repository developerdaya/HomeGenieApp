package com.home.genie.ui.m1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home.genie.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
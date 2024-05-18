package com.home.genie.ui.m1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.home.genie.R
import com.home.genie.databinding.ActivityLoginBinding
import com.home.genie.databinding.ActivityOtpBinding


class OtpActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
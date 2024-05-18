package com.home.genie.ui.m1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.home.genie.R
import com.home.genie.databinding.ActivityOtpBinding
import com.home.genie.databinding.ActivityResetBinding

class ResetActivity : AppCompatActivity() {
    lateinit var binding: ActivityResetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityResetBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
package com.home.genie.ui.m1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.home.genie.R
import com.home.genie.databinding.ActivitySplashBinding
import com.home.genie.ui.moveActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(1024,1024)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            moveActivity(LoginActivity())
            finish()
        }

    }
}
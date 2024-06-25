package com.home.genie.ui.m1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.home.genie.R
import com.home.genie.databinding.ActivitySplashBinding
import com.home.genie.ui.moveActivity
import com.home.genie.util.SPreferenceUtils
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
            delay(3500)
            if (SPreferenceUtils.getInstance(this@SplashActivity).isLogin)
            {
                moveActivity(HomeActivity())
            }
            else
            {
                moveActivity(InfoActivity())
            }
            finish()
        }

    }

}
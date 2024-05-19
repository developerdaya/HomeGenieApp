package com.home.genie.ui.m1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.home.genie.R
import com.home.genie.databinding.ActivityLoginBinding
import com.home.genie.databinding.ActivityOtpBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.showToast


class OtpActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    companion object{
        var PHONE_NUMBER = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.otpText.text = "Enter 4 Digit OTP sent on $PHONE_NUMBER"
        initControls()

    }

    private fun initControls() {
        binding.btnSubmit.setOnClickListener {
            if(validation())
            {
                moveActivity(HomeActivity())
                finish()
            }
        }



        binding.pinViewOtp.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length == 4)
                {
                    binding.btnSubmit.background = resources.getDrawable(R.drawable.bg_round_green)
                    binding.btnSubmit.setTextColor(resources.getColor(R.color.white))
                }
                else
                {
                    binding.btnSubmit.background = resources.getDrawable(R.drawable.bg_round_button)
                    binding.btnSubmit.setTextColor(resources.getColor(R.color.gray))
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun validation(): Boolean {
        if (binding.pinViewOtp.text.toString().isEmpty())
        {
            showToast("Please enter OTP")
            return false
        }
        if (binding.pinViewOtp.text.toString().length != 4)
        {
            showToast("Invalid OTP")
            return false
        }


        return true


    }
}
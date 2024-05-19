package com.home.genie.ui.m1

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.home.genie.R
import com.home.genie.databinding.ActivityLoginBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.showToast

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initControl()

    }

    private fun initControl() {
        binding.getVerification.setOnClickListener {
            if (validation()) {
                OtpActivity.PHONE_NUMBER = "${binding.ccpMobile.selectedCountryCodeWithPlus} ${binding.mEnterMobile.text}"

                moveActivity(OtpActivity())
            }

        }
        binding.mEnterMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length == 10) {
                    binding.getVerification.background =
                        resources.getDrawable(R.drawable.bg_round_green)
                    binding.getVerification.setTextColor(resources.getColor(R.color.white))
                } else {
                    binding.getVerification.background =
                        resources.getDrawable(R.drawable.bg_round_button)
                    binding.getVerification.setTextColor(resources.getColor(R.color.gray))
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun validation() :Boolean{
        if (binding.mEnterMobile.text.toString().isEmpty()) {
            showToast("Enter mobile number")

            return false
        } else if (binding.mEnterMobile.text.toString().length != 10) {
            showToast("Enter valid mobile number")
            return false
        } else {
            return true
        }
    }
}
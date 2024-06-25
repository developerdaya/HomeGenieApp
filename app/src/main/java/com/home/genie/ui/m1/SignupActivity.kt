package com.home.genie.ui.m1

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.home.genie.R
import com.home.genie.databinding.ActivitySignupBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.moveActivityData
import com.home.genie.ui.showToast
import com.home.genie.util.ErrorUtil
import com.home.genie.util.SPreferenceUtils
import com.home.genie.util.hideProgress
import com.home.genie.util.showProgress
import com.home.genie.viewModel.M1ViewModel


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    var isPasswordVisible = false
    var isPasswordVisible2 = false
    var isCheck = false
    lateinit var m1ViewModel: M1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initControl()
        observer()

    }



    private fun observer() {
        m1ViewModel.mSignupResp.observe(this) {
          OtpActivity.PHONE_NUMBER = it.data.mobileNumber
          OtpActivity.COUNTRY_CODE = it.data.code
            moveActivityData(OtpActivity(), "signup")
        }
        m1ViewModel.mError.observe(this) {
            ErrorUtil.handlerGeneralError(this, it)
        }
        m1ViewModel.isLoading.observe(this) {
         //   if (it) showProgress() else hideProgress()

        }
    }

    private fun initControl() {
        binding.mTermsAndConditions.setOnClickListener {
            if (binding.mTermsAndConditions.isChecked)
            {
                isCheck = true
            } else {
                isCheck = false
            }
        }
    }
    fun hideKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = this.currentFocus
        if (currentFocusView != null) {
            imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }

    }

    private fun initViews() {
        m1ViewModel = ViewModelProvider(this)[M1ViewModel::class.java]
        binding.passEye.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.mEnterPassword.transformationMethod = null
                binding.passEye.setImageResource(R.drawable.view)
                binding.mEnterPassword.setSelection(binding.mEnterPassword.length())
            } else {
                binding.mEnterPassword.transformationMethod = PasswordTransformationMethod()
                binding.passEye.setImageResource(R.drawable.hide)
                binding.mEnterPassword.setSelection(binding.mEnterPassword.length())
            }

        }
        binding.passEye2.setOnClickListener {
            isPasswordVisible2 = !isPasswordVisible2
            if (isPasswordVisible2) {
                binding.mEnterPassword2.transformationMethod = null
                binding.passEye2.setImageResource(R.drawable.view)
                binding.mEnterPassword2.setSelection(binding.mEnterPassword2.length())

            } else {
                binding.mEnterPassword2.transformationMethod = PasswordTransformationMethod()
                binding.passEye2.setImageResource(R.drawable.hide)
                binding.mEnterPassword2.setSelection(binding.mEnterPassword2.length())

            }

        }
        binding.btnSubmit.setOnClickListener {
            hideKeyboard()
            if (validation())
            {
                m1ViewModel.hitSignup(
                    firstName = binding.mFirstName.text.toString(),
                    lastName = binding.mLastName.text.toString(),
                    mobileNumber = binding.mEnterMobile.text.toString(),
                    email =binding.mValidEmail.text.toString(),
                    password = binding.mEnterPassword.text.toString(),
                    deviceToken = "asdf",
                    deviceType = 2,
                    code = "+91")

            }

        }
    }

    private fun validation(): Boolean {
        if(binding.mFirstName.text.toString().isEmpty())
        {
            showToast("Enter First Name")
            return false
        }
        if(binding.mLastName.text.toString().isEmpty())
        {
            showToast("Enter Last Name")
            return false
        }
        if(binding.mValidEmail.text.toString().isEmpty()){
            showToast("Enter Email")
            return false
        }
        if (!binding.mValidEmail.text.toString().matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
            showToast("Enter Valid Email")
            return false
        }

        if(binding.mEnterMobile.text.toString().isEmpty()){
            showToast("Enter Mobile Number")
            return false
        }
        if(binding.mEnterMobile.text.toString().length<10){
            showToast("Enter Valid Mobile Number")
            return false

        }
        if(binding.mEnterPassword.text.toString().isEmpty()){
            showToast("Enter Password")
            return false
        }

        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{6,20}$"
        if (!binding.mEnterPassword.text.toString().matches(passwordPattern.toRegex())) {
            showToast("Pass: 6-20 chars, number, uppercase, lowercase, special symbol")
            return false
        }
        if(binding.mEnterPassword2.text.toString().isEmpty()){
            showToast("Enter Confirm Password")
            return false
        }
        if (binding.mEnterPassword2.text.toString()!=(binding.mEnterPassword.text.toString()))
        {
            showToast("Password Not Match")
            return false
        }
        if (!isCheck)
        {
            showToast("Please Accept Terms and Conditions")
            return false
        }


        return true

    }
}
package com.home.genie.ui.m1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.home.genie.R
import com.home.genie.databinding.ActivityLoginBinding
import com.home.genie.databinding.ErrorDialogBinding
import com.home.genie.databinding.ProfileCreatedDialogBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.showToast
import com.home.genie.util.ErrorUtil
import com.home.genie.util.SPreferenceUtils
import com.home.genie.util.hideProgress
import com.home.genie.util.showProgress
import com.home.genie.viewModel.M1ViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var isPasswordVisible = false
    lateinit var m1ViewModel: M1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initControl()
        observer()


    }



    private fun observer() {
        m1ViewModel.mLoginResp.observe(this) {
            SPreferenceUtils.getInstance(this).accessToken =it.token
            SPreferenceUtils.getInstance(this).isLogin =true
            moveActivity(HomeActivity())
            finishAffinity()

        }
        m1ViewModel.mError.observe(this) {
            ErrorUtil.handlerGeneralError(this, it)

        }
        m1ViewModel.isLoading.observe(this) {
          //  if (it) showProgress() else hideProgress()

        }
    }

    private fun initViews() {
        m1ViewModel = ViewModelProvider(this)[M1ViewModel::class.java]
    }
    fun hideKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = this.currentFocus
        if (currentFocusView != null) {
            imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }

    }
    private fun initControl() {
        binding.mForgetPassword.setOnClickListener {
            moveActivity(ForgetActivity())

        }
        binding.mSignup.setOnClickListener {
            moveActivity(SignupActivity())
        }
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
        binding.mLogin.setOnClickListener {
            hideKeyboard()
            if (validation()) {
                m1ViewModel.hitLogin(
                    mobile_number = binding.mEnterMobile.text.toString(),
                    password = binding.mEnterPassword.text.toString(),
                    device_token = "asdf",
                    device_type = 2,
                    code = "+91"
                )
            }
        }
    }

    private fun validation(): Boolean {
        if (binding.mEnterMobile.text.toString().isEmpty()) {
            showToast("Enter mobile number")
            return false
        } else if (binding.mEnterMobile.text.toString().length != 10) {
            showToast("Enter valid mobile number")
            return false
        } else if (binding.mEnterPassword.text.toString().isEmpty()) {
            showToast("Enter password")
            return false
        }

        return true
    }
}
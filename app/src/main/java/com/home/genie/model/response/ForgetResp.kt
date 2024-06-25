package com.home.genie.model.response


import com.google.gson.annotations.SerializedName

data class ForgetResp(
    @SerializedName("message")
    val message: String
)
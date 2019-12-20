package com.tiki.challenge.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseBase : Serializable {
    @SerializedName("statusCode")
    @Expose
    val statusCode: Int? = null

    @SerializedName("statusName")
    @Expose
    val statusName: String? = null
}
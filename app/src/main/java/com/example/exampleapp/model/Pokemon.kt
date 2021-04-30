package com.example.exampleapp.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null
)
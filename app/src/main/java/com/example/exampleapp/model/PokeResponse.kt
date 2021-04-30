package com.example.exampleapp.model

import com.google.gson.annotations.SerializedName

data class PokeResponse<T> (
    @field:SerializedName("count")
    var count: Int? = null,
    @field:SerializedName("next")
    var next: String? = null,
    @field:SerializedName("previous")
    var previous: String? = null,
    @field:SerializedName("results")
    var results: T? = null
)
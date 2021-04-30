package com.example.exampleapp.model

data class ResponseResult<T>(val status: Status, val code: Int?, val data: T?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(code: Int?, data: T?): ResponseResult<T> = ResponseResult(Status.SUCCESS, code, data)
        fun <T> error(code: Int?, data: T?): ResponseResult<T> = ResponseResult(Status.ERROR, code, data)
        fun <T> loading(): ResponseResult<T> = ResponseResult(Status.LOADING, null, null)
    }
}
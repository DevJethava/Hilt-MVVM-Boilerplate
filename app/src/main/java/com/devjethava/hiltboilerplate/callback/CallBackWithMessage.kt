package com.devjethava.hiltboilerplate.callback

interface CallBackWithMessage<T> {
    fun onSuccess(item: T?)
    fun onFailure(message: String?)
}
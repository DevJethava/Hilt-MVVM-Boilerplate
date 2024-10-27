package com.devjethava.hiltboilerplate.callback

interface CallBack<T> {
    fun onSuccess(item: T?)
    fun onFailure()
}
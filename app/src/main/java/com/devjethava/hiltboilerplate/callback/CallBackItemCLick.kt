package com.devjethava.hiltboilerplate.callback

interface CallBackItemCLick<T> {
    fun onItemClick(item: T?, position: Int, message: String)
}
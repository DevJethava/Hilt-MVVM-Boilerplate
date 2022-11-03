package com.devjethava.hiltboilerplate.model.repository

import com.devjethava.hiltboilerplate.model.data.response.DashboardResponse
import com.devjethava.hiltboilerplate.model.remote.RestApiService
import io.reactivex.Single
import javax.inject.Singleton

interface ApiRepository {
    fun getDashboardData(): Single<DashboardResponse>
}

class ApiRepositoryImpl constructor(
    private val remote: RestApiService,
) : ApiRepository {
    override fun getDashboardData(): Single<DashboardResponse> = remote.getDashboardData()
}


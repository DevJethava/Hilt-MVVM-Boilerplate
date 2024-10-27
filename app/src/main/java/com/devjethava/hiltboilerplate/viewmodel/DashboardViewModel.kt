package com.devjethava.hiltboilerplate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devjethava.hiltboilerplate.helper.Preference
import com.devjethava.hiltboilerplate.helper.async
import com.devjethava.hiltboilerplate.model.data.response.DashboardResponse
import com.devjethava.hiltboilerplate.model.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: ApiRepository,
    preference: Preference
) : BaseViewModel(preference) {
    fun getDashboardData(): Single<DashboardResponse> {
        return repository.getDashboardData().async(0)
            .doOnSuccess { stopLoad() }
            .doOnSubscribe {
                startLoad()
            }
            .doAfterTerminate {
                stopLoad()
            }
    }

    private val _getDashboardData = MutableLiveData<DashboardResponse>()
    val getDashboardResponse: LiveData<DashboardResponse> = _getDashboardData

    fun getDashboardData2() {
        repository.getDashboardData().async(0)
            .doOnSubscribe { startLoad() }
            .doAfterTerminate { stopLoad() }
            .subscribe(
                {
                    _getDashboardData.postValue(it)
                },
                {
                    throwError.postValue(it)
                    it.printStackTrace()
                }
            ).addTo(compositeDisposable)
    }
}
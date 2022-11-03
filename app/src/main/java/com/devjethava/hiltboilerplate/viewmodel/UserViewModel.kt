package com.devjethava.hiltboilerplate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devjethava.hiltboilerplate.database.entity.UserEntity
import com.devjethava.hiltboilerplate.database.repository.UserRepository
import com.devjethava.hiltboilerplate.helper.Preference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    preference: Preference
) : BaseViewModel(preference) {
    private lateinit var userData: MutableLiveData<List<UserEntity>>

    fun addUserData(userEntity: UserEntity) {
        ioScope.launch { userRepository.insertDataAsync(userEntity) }
    }

    fun getAllUserList(): LiveData<List<UserEntity>> {
        userData = MutableLiveData()
        ioScope.launch { userData.postValue(userRepository.getListAsync()) }
        return userData
    }
}
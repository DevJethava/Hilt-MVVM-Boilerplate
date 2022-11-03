package com.devjethava.hiltboilerplate.database.repository

import com.devjethava.hiltboilerplate.database.dao.UserDao
import com.devjethava.hiltboilerplate.database.entity.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertDataAsync(userEntity: UserEntity) = userDao.insertUserData(userEntity)

    suspend fun getListAsync() = userDao.fetchUsersData()
}
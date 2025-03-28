package com.example.myapplication.data.repository

// UserRepositoryImpl.kt

import com.example.myapplication.data.local.UserDao
import com.example.myapplication.data.local.UserEntity
import com.example.myapplication.data.reomote.UserApi
import com.example.myapplication.data.reomote.modelDTO.UserDto
import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun sendUserText() {
        TODO("Not yet implemented")
    }

    override suspend fun refreshUsers() {
        try {
            val response =  userApi.getUsers()
            val userEntities = response.users.map { it.toEntity() }
            userDao.deleteAllUsers()
            userDao.insertUsers(userEntities)
        } catch (e: Exception) {
            // Handle error
            e.printStackTrace()
        }
    }

    private fun UserEntity.toDomainModel(): User {
        return User(
            id = id,
            name = name
        )
    }

    private fun UserDto.toEntity(): UserEntity {
        return UserEntity(
            id = id,
            name = name
        )
    }
}

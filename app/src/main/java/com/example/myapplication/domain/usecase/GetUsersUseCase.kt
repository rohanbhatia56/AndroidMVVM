package com.example.myapplication.domain.usecase


import com.example.myapplication.domain.model.User
import com.example.myapplication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return userRepository.getUsers()
    }

    suspend fun refresh() {
        userRepository.refreshUsers()
    }
}
package com.jca.myapplication.data.remote

import com.jca.myapplication.model.User

interface RemoteRepository {
    suspend fun getUsers(): List<User>?
    suspend fun getUser(id: Int): User?
    suspend fun postUser(user: User): Boolean
    suspend fun putUser(user: User): Boolean
    suspend fun deleteUser(id: Int): Boolean
}
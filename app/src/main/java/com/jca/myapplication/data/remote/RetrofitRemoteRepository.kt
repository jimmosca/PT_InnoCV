package com.jca.myapplication.data.remote

import com.jca.myapplication.model.User

class RetrofitRemoteRepository(private val usersAPI: UsersAPI): RemoteRepository{
    override suspend fun getUsers(): List<User>? {
        val getUsersResponse = usersAPI.getUsers()
        return if (getUsersResponse.isSuccessful){
            getUsersResponse.body()!!
        }else
            null
    }
    override suspend fun getUser(id: Int): User? {
        val getUserResponse = usersAPI.getUser(id)
        return if (getUserResponse.isSuccessful){
            getUserResponse.body()!!
        }else
            null
    }
    override suspend fun postUser(user: User): Boolean {
        val postUserResponse = usersAPI.postUser()
        return postUserResponse.isSuccessful

    }
    override suspend fun putUser(user: User): Boolean {
        val putUserResponse = usersAPI.putUser()
        return putUserResponse.isSuccessful
    }
    override suspend fun deleteUser(id: Int): Boolean {
        val deleteUserResponse = usersAPI.deleteUser(id)
        return deleteUserResponse.isSuccessful
    }
}
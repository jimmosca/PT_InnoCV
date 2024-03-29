package com.jca.myapplication.data.remote

import android.util.Log
import com.jca.myapplication.model.User

class RetrofitRemoteRepository(private val usersAPI: UsersAPI): RemoteRepository{
    override suspend fun getUsers(): List<User>? {
        val getUsersResponse = usersAPI.getUsers()
        return if (getUsersResponse.isSuccessful){
            Log.e("RemoteRepo", "La peticion fue bien")
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
        val postUserResponse = usersAPI.postUser(user)
        return postUserResponse.isSuccessful

    }
    override suspend fun putUser(user: User): Boolean {
        val putUserResponse = usersAPI.putUser(user)
        return putUserResponse.isSuccessful
    }
    override suspend fun deleteUser(id: Int): Boolean {
        val deleteUserResponse = usersAPI.deleteUser(id)
        return deleteUserResponse.isSuccessful
    }
}
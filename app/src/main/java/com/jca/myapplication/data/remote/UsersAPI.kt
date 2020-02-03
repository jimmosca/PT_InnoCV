package com.jca.myapplication.data.remote

import com.jca.myapplication.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


interface UsersAPI {
    @GET("User")
    suspend fun getUsers(): Response<List<User>>

    @GET("User/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<User>

    @POST("User")
    suspend fun postUser(): Response<Any>

    @PUT("User")
    suspend fun putUser(): Response<Any>

    @DELETE("User/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Any>
}

object RetrofitFactory {
    private const val BASE_URL = "https://hello-world.innocv.com/api/"

    fun makeRetrofitService(): UsersAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(UsersAPI::class.java)
    }
}
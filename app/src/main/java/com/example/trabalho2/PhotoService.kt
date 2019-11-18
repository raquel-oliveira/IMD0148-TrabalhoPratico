package com.example.trabalho2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoService {

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}
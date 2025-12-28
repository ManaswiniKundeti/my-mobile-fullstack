package com.example.myfullstackapp.network

import com.example.myfullstackapp.data.HomeExperienceDto
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/experience/home")
    suspend fun getHome(): HomeExperienceDto
}

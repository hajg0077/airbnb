package com.example.airbnb

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/8124dfd4-62e0-4e5c-9018-07c1215f428d")
    fun getHouseList(): Call<HouseDto>
}
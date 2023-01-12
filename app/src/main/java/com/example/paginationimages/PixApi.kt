package com.example.paginationimages

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixApi {

    @GET("api/")
    fun getImages(
        @Query("key") key : String = "32156320-040ec4363c2ed4ffeff73ca8f",
        @Query("q") q : String,
        @Query("page") page : Int = 1,
        @Query("per_page") per_page: Int = 20
        ): Call<PixModel>
}
package com.example.paginationimages

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    fun getClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(provideInterceptor())
            .build()

    }

    fun provideInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun getApi(): PixApi = retrofit.create(PixApi::class.java)


}
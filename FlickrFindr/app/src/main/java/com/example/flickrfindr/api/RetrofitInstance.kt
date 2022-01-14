package com.example.flickrfindr.api

import com.example.flickrfindr.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var retrofit: Retrofit? = null

    fun getRetrofitInstance(): Retrofit? {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.RECENTS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .build()
        }
        return retrofit
    }
}

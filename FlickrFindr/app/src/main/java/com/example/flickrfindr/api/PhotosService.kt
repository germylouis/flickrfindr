package com.example.flickrfindr.data.repos

import com.example.flickrfindr.data.entities.BasePhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosRepo {
    @GET
    suspend fun getRecentPhotos(
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callBack: String
    ): BasePhoto
}

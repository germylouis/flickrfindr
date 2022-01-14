package com.example.flickrfindr.api

import com.example.flickrfindr.data.entities.BasePhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosService {
    @GET("?method=flickr.photos.getRecent")
    suspend fun getRecentPhotos(
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callBack: String
    ): BasePhoto?
}

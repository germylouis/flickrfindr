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

    @GET("?method=flickr.photos.search")
    suspend fun searchPhotos(
        @Query("api_key") apiKey: String,
        @Query("text") text: String = "",
        @Query("per_page") numResults: String = "25",
        @Query("format") format: String,
        @Query("nojsoncallback") callBack: String
    ): BasePhoto?
}

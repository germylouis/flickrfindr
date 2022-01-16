package com.example.flickrfindr.data.datasources

import com.example.flickrfindr.api.PhotosService
import com.example.flickrfindr.api.RetrofitInstance
import com.example.flickrfindr.constants.Constants
import com.example.flickrfindr.data.entities.BasePhoto

class BasePhotoDataSource : PhotoDataSource {
    private var photosService: PhotosService? = null

    override suspend fun getRecentPhotos(
        apiKey: String, format: String, callBack: String
    ): BasePhoto? {
        photosService = RetrofitInstance.getRetrofitInstance(Constants.RECENTS_BASE_URL)?.create(PhotosService::class.java)
        return photosService?.getRecentPhotos(apiKey, format, callBack)
    }

    override suspend fun searchPhotos( apiKey: String, text: String, format: String, callBack: String): BasePhoto? {
        photosService = RetrofitInstance.getRetrofitInstance(Constants.SEARCH_BASE_URL)?.create(PhotosService::class.java)
        return photosService?.searchPhotos(apiKey = apiKey, text = text, format = format, callBack = callBack)
    }
}

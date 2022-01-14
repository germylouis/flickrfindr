package com.example.flickrfindr.data.datasources

import com.example.flickrfindr.api.PhotosService
import com.example.flickrfindr.api.RetrofitInstance
import com.example.flickrfindr.data.entities.BasePhoto

class BasePhotoDataSource : PhotoDataSource {
    private var photosService: PhotosService? = null

    override suspend fun getRecentPhotos(
        apiKey: String, format: String, callBack: String
    ): BasePhoto? {
        photosService = RetrofitInstance.getRetrofitInstance()?.create(PhotosService::class.java)
        return photosService?.getRecentPhotos(apiKey, format, callBack)
    }
}

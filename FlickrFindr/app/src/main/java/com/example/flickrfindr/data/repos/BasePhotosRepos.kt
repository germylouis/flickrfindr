package com.example.flickrfindr.data.repos

import com.example.flickrfindr.BuildConfig
import com.example.flickrfindr.constants.Constants
import com.example.flickrfindr.data.datasources.BasePhotoDataSource
import com.example.flickrfindr.data.entities.BasePhoto

class BasePhotosRepos(private val baseDataSource: BasePhotoDataSource) : PhotosRepos {
    val apiKey = BuildConfig.CONSUMER_KEY
    val format = Constants.FORMAT
    val jsonCallBack = Constants.NOJSONCALLBACK

    override suspend fun getRecentPhotos(): BasePhoto? {
        return baseDataSource.getRecentPhotos(apiKey, format, jsonCallBack)
    }
}

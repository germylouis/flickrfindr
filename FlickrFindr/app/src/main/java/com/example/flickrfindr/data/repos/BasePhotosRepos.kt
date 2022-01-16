package com.example.flickrfindr.data.repos

import com.example.flickrfindr.BuildConfig
import com.example.flickrfindr.constants.Constants
import com.example.flickrfindr.data.datasources.BasePhotoDataSource
import com.example.flickrfindr.data.entities.BasePhoto

class BasePhotosRepos(private val baseDataSource: BasePhotoDataSource) : PhotosRepos {
    private val apiKey = BuildConfig.CONSUMER_KEY
    private val format = Constants.FORMAT
    private val jsonCallBack = Constants.NOJSONCALLBACK

    override suspend fun getRecentPhotos(): BasePhoto? {
        return baseDataSource.getRecentPhotos(apiKey, format, jsonCallBack)
    }

    override suspend fun searchPhotos(text: String?): BasePhoto? {
        return baseDataSource.searchPhotos(apiKey, text ?: "", format, jsonCallBack)
    }
}

package com.example.flickrfindr.data.datasources

import com.example.flickrfindr.data.entities.BasePhoto

interface PhotoDataSource {
    suspend fun getRecentPhotos(
        apiKey: String, format: String, callBack: String
    ): BasePhoto?
}

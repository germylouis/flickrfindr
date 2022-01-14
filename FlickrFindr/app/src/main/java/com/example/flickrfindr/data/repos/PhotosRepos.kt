package com.example.flickrfindr.data.repos

import com.example.flickrfindr.data.entities.BasePhoto

interface PhotosRepos {
    suspend fun getRecentPhotos(): BasePhoto?
}

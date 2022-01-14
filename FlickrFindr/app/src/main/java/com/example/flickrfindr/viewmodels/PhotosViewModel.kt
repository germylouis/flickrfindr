package com.example.flickrfindr.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.flickrfindr.data.entities.BasePhoto
import com.example.flickrfindr.data.repos.PhotosRepos
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@InternalCoroutinesApi
class PhotosViewModel(private val photosRepos: PhotosRepos) : ViewModel() {

    suspend fun getPhotos(): Flow<BasePhoto?> = flow {
        emit(photosRepos.getRecentPhotos())
    }

    class Factory(app: Application, private val photosRepos: PhotosRepos) : ViewModelFactory(app) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PhotosViewModel(photosRepos) as T
        }
    }

    companion object {
        val TAG: String = PhotosViewModel::class.java.name
    }

}
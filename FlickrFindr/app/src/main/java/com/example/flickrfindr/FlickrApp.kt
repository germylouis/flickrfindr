package com.example.flickrfindr

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.flickrfindr.data.datasources.BasePhotoDataSource
import com.example.flickrfindr.data.repos.BasePhotosRepos
import com.example.flickrfindr.data.repos.PhotosRepos
import com.example.flickrfindr.viewmodels.PhotosViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
class FlickrApp : AppCompatActivity() {

    private val photosRepos: PhotosRepos
        get() = BasePhotosRepos(BasePhotoDataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory: ViewModelProvider.AndroidViewModelFactory = PhotosViewModel.Factory(application, photosRepos)
        val viewModel: PhotosViewModel by viewModels { factory }
        lifecycleScope.launchWhenCreated {
            viewModel.getPhotos().collect {
                Log.d("germ", "onCreate: ${it?.photos?.total}")
            }
        }

    }
}

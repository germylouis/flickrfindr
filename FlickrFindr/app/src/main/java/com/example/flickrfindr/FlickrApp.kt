package com.example.flickrfindr

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickrfindr.data.datasources.BasePhotoDataSource
import com.example.flickrfindr.data.entities.BasePhoto
import com.example.flickrfindr.data.repos.BasePhotosRepos
import com.example.flickrfindr.data.repos.PhotosRepos
import com.example.flickrfindr.databinding.FlickrActivityMainBinding
import com.example.flickrfindr.ui.adapters.FlickrPhotoAdapter
import com.example.flickrfindr.ui.fragments.PhotoFragment
import com.example.flickrfindr.viewmodels.PhotosViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
class FlickrApp : AppCompatActivity(), FlickrPhotoAdapter.FlickrPhotoClicked {
    private var binding: FlickrActivityMainBinding? = null
    private var photos: BasePhoto? = null
    private var bundle: Bundle? = null
    private val photosRepos: PhotosRepos
        get() = BasePhotosRepos(BasePhotoDataSource())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FlickrActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val factory: ViewModelProvider.AndroidViewModelFactory = PhotosViewModel.Factory(application, photosRepos)
        val viewModel: PhotosViewModel by viewModels { factory }
        val manager = LinearLayoutManager(this)
        val listener: FlickrPhotoAdapter.FlickrPhotoClicked = this

        lifecycleScope.launchWhenCreated {
            viewModel.getPhotos().collect {
                Log.d("germ", "onCreate: ${it?.photos?.total}")
                photos = it
            }
            binding?.flickRv?.apply {
                layoutManager = manager
                adapter = FlickrPhotoAdapter(photos, listener)

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onPhotoClicked(photoUrl: String) {
        val fragment = PhotoFragment()
        bundle = Bundle()
        bundle?.putString("photo_url", photoUrl)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.flickr_app_activity, fragment)
            .addToBackStack(PhotoFragment.TAG).commit()
    }

}


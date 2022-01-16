package com.example.flickrfindr.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrfindr.data.entities.BasePhoto
import com.example.flickrfindr.data.entities.Photo
import com.example.flickrfindr.databinding.FlickrListViewBinding
import loadImage

class FlickrPhotoAdapter(private val basePhoto: BasePhoto?, var flickrPhotoClickedListener: FlickrPhotoClicked) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FlickrListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding, flickrPhotoClickedListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotosViewHolder).bind(basePhoto?.photos?.photo?.get(position))
    }

    override fun getItemCount(): Int = basePhoto?.photos?.photo?.size ?: 0

    class PhotosViewHolder(
        private val flickrListViewBinding: FlickrListViewBinding,
        var flickrPhotoClickedListener: FlickrPhotoClicked
    ) : RecyclerView.ViewHolder(flickrListViewBinding.root) {
        fun bind(photo: Photo?) {
            with(flickrListViewBinding) {
                photo?.apply {
                    "https://live.staticflickr.com/${server}/${id}_${secret}_t.jpg".apply {
                        fldFlickrTitle.text = title
                        flickrPhoto.loadImage(this)
                        flickrPhoto.setOnClickListener { flickrPhotoClickedListener.onPhotoClicked(this) }
                    }
                }
            }
        }
    }

    interface FlickrPhotoClicked {
        fun onPhotoClicked(photoUrl: String)
    }
}

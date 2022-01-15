package com.example.flickrfindr.ui.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.ViewTarget
import com.example.flickrfindr.R
import com.example.flickrfindr.data.entities.BasePhoto
import com.example.flickrfindr.data.entities.Photo
import com.example.flickrfindr.databinding.FlickrListViewBinding

class FlickrPhotoAdapter(private val basePhoto: BasePhoto?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding  = FlickrListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotosViewHolder).bind(basePhoto?.photos?.photo?.get(position))
    }

    override fun getItemCount(): Int = basePhoto?.photos?.photo?.size ?: 0

    class PhotosViewHolder(val flickrListViewBinding: FlickrListViewBinding): RecyclerView.ViewHolder(flickrListViewBinding.root){
        fun bind(photo: Photo?){
            with(flickrListViewBinding){
                photo?.apply {
                    loadImage(flickrPhoto, "https://live.staticflickr.com/${server}/${id}_${secret}_s.jpg")
                    fldFlickrName.text = title
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun loadImage(view: ImageView, url: String): ViewTarget<ImageView, Bitmap> {
            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return Glide.with(view).asBitmap().load(url).centerCrop()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground).into(view)
        }
    }
}

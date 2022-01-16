package com.example.flickrfindr.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flickrfindr.databinding.PhotoFragmentBinding
import loadImage

class PhotoFragment : Fragment() {
    private var binding: PhotoFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PhotoFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        if (arguments != null) {
            binding?.flickrPhoto?.loadImage(arguments?.getString("photo_url").toString())
            binding?.outsideImgView?.setOnClickListener {
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        val TAG: String = PhotoFragment::class.java.name
    }
}

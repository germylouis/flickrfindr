package com.example.flickrfindr.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flickrfindr.api.ImageViewExtension;
import com.example.flickrfindr.databinding.PhotoFragmentBinding;

public class PhotoFragment extends Fragment {
    static String TAG = PhotoFragment.class.getName();
    private PhotoFragmentBinding binding;

    public PhotoFragment() {
        this.binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = PhotoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            ImageViewExtension.loadImage(binding.flickrPhoto, getArguments().getString("photo_url"));
            binding.outsideImgView.setOnClickListener(l -> requireActivity().getSupportFragmentManager().popBackStack());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

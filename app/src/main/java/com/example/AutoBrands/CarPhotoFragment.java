package com.example.AutoBrands;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CarPhotoFragment extends Fragment {

    static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_photo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        if (arguments != null) {
            int index = arguments.getInt(ARG_INDEX);
            ImageView imageAutoBrands = view.findViewById(R.id.car_photo_image_view);
            TypedArray images = getResources().obtainTypedArray(R.array.car_photo_imgs);
            imageAutoBrands.setImageResource(images.getResourceId(index, 0));
            images.recycle();
        }
    }

    public static CarPhotoFragment newInstance(int index) {
        CarPhotoFragment fragment = new CarPhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
}
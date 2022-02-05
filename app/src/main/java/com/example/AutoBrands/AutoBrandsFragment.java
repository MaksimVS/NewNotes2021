package com.example.AutoBrands;

import static com.example.AutoBrands.CarPhotoFragment.ARG_INDEX;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AutoBrandsFragment extends Fragment {

    private static final String MARKED_BRAND = "marked_brand";
    private int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brands, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(MARKED_BRAND, 0);
        }
        initList(view);
        if (isLandscape()) {
            showLandAutoBrands(currentPosition);
        }
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] brands = getResources().getStringArray(R.array.brands);

        for (int i = 0; i < brands.length; i++) {
            String brand = brands[i];
            TextView tv = new TextView(getContext());
            tv.setText(brand);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                currentPosition = position;
                showAutoBrands(position);
            });
        }
    }

    private void showAutoBrands(int index) {
        if (isLandscape()) {
            showLandAutoBrands(index);
        } else {
            showPortAutoBrands(index);
        }
    }

    private void showPortAutoBrands(int index) {
        Activity activity = requireActivity();
        final Intent intent = new Intent(activity, CarPhotoActivity.class);
        intent.putExtra(ARG_INDEX, index);
        activity.startActivity(intent);
    }

    private void showLandAutoBrands(int index) {
        CarPhotoFragment detail = CarPhotoFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.auto_brands_container, detail)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(MARKED_BRAND, currentPosition);
        super.onSaveInstanceState(outState);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
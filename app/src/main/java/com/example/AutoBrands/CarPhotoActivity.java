package com.example.AutoBrands;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.AutoBrands.CarPhotoFragment.ARG_INDEX;

public class CarPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_photo);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.car_photo_fragment_container, CarPhotoFragment.newInstance(getIntent().getExtras().getInt(ARG_INDEX)))
                    .commit();
    }
}
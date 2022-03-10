package com.example.amarpharmacy;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ProductDetailsAdapter extends FragmentStateAdapter {

    private String[] titles = new String[]{"Description","Other Details"};

    public ProductDetailsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ProductDescriptionFragment();
            case 1:
                return new OtherDescripionFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

package com.anjana.luxgan.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import com.anjana.luxgan.R;

public class FavouriteFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite,container, false);
        return root;
    }
}

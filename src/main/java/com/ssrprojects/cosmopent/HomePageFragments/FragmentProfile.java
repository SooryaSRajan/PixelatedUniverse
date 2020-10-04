package com.ssrprojects.cosmopent.HomePageFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.ssrprojects.cosmopent.R;

public class FragmentProfile extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), "Unimplemented Feature", Snackbar.LENGTH_SHORT).show();
        return view;
    }
}

package com.ssrprojects.cosmopent.HomePageFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssrprojects.cosmopent.CustomAdapters.MainAdapter;
import com.ssrprojects.cosmopent.R;
import com.ssrprojects.cosmopent.SpaceTelescopeActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentHome extends Fragment {
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("DATA");

        ArrayList<HashMap> mList = new ArrayList<>();

        HashMap map = new HashMap();
        map.put("NAME", "Contour Mapping");
        map.put("SUB", "Get contour maps");
        mList.add(map);

        map = new HashMap();
        map.put("NAME", "Mapping with coordinates");
        map.put("SUB", "Map coordinates of given data");
        mList.add(map);

        map = new HashMap();
        map.put("NAME", "Convert .fits to other format");
        map.put("SUB", "Converts .fits files to other formats");
        mList.add(map);

        map = new HashMap();
        map.put("NAME", "Noise Cancelling");
        map.put("SUB", "get noise cancelled data of specific frequencies");
        mList.add(map);

        map = new HashMap();
        map.put("NAME", "Multi-Wavelength Analysis");
        map.put("SUB", "get multi-wavelength data");
        mList.add(map);

        ListView listView = view.findViewById(R.id.home_page_list);
        MainAdapter adapter = new MainAdapter(mList, getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getActivity(), ContourMappingActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;

                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        Snackbar.make(view, "Unimplemented Feature", Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;

    }
}

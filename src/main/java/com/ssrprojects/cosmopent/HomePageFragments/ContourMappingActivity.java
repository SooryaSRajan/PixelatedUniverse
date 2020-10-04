package com.ssrprojects.cosmopent.HomePageFragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.ssrprojects.cosmopent.CustomAdapters.MainAdapter;
import com.ssrprojects.cosmopent.HomePageActivity;
import com.ssrprojects.cosmopent.R;
import com.ssrprojects.cosmopent.SpaceTelescopeActivity;
import com.ssrprojects.cosmopent.TestData;

import java.util.ArrayList;
import java.util.HashMap;

public class ContourMappingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contour_mapping);

        getSupportActionBar().setTitle("Contour Mapping");
        final ArrayList<HashMap> mList = new ArrayList<>();

        HashMap map = new HashMap();

        map.put("NAME", "Test Data");
        map.put("SUB", "Get sample and test data");
        mList.add(map);

        map = new HashMap();
        map.put("NAME", "Space Telescope");
        map.put("SUB", "Get live data of processed .fits data from space telescope");
        mList.add(map);

        map = new HashMap();
        map.put("NAME", "Land Telescope");
        map.put("SUB", "Land telescope .fits data from space telescope");
        mList.add(map);

        ListView listView = findViewById(R.id.contour_mapping_list_view);
        final MainAdapter adapter = new MainAdapter(mList, ContourMappingActivity.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(ContourMappingActivity.this, TestData.class);
                    startActivity(intent);
                    finish();
                }

                else if(position == 1){
                    Intent intent = new Intent(ContourMappingActivity.this, SpaceTelescopeActivity.class);
                    startActivity(intent);
                    finish();
                }

                else if(position ==2){
                    Snackbar.make(view, "Unimplemented Feature", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ContourMappingActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home_back){
            Intent intent = new Intent(ContourMappingActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

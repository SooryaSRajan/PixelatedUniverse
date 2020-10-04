package com.ssrprojects.cosmopent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ssrprojects.cosmopent.CustomAdapters.MainAdapter;
import com.ssrprojects.cosmopent.HomePageFragments.ContourMappingActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SpaceTelescopeActivity extends AppCompatActivity {
DatabaseReference mRef;
ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_telescope);
        final ArrayList<HashMap> mList = new ArrayList<>();

        getSupportActionBar().setTitle("Space Telescopes");

        ListView listView = findViewById(R.id.space_telescope_list_view);
        final MainAdapter adapter = new MainAdapter(mList, SpaceTelescopeActivity.this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SpaceTelescopeActivity.this, TelescopeDataActivity.class);
                intent.putExtra("NODE", mList.get(position).get("NAME").toString());
                startActivity(intent);
                finish();
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference().child("DATA").child("Space Based Telescope");
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    HashMap map = new HashMap();
                    map.put("NAME", snap.getKey());
                    map.put("SUB", "satellite");
                    mList.add(map);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mRef.addValueEventListener(listener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mRef.removeEventListener(listener);
        Intent intent = new Intent(SpaceTelescopeActivity.this, ContourMappingActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(listener);
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
            mRef.removeEventListener(listener);
            Intent intent = new Intent(SpaceTelescopeActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

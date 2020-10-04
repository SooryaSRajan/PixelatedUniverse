package com.ssrprojects.cosmopent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ssrprojects.cosmopent.CustomAdapters.ImageAdapter;
import com.ssrprojects.cosmopent.HomePageFragments.ContourMappingActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TestData extends AppCompatActivity {
    DatabaseReference mRef;
    ValueEventListener listener;
    ArrayList<Bitmap> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data);

        getSupportActionBar().setTitle("Test Data");

        ListView listView = findViewById(R.id.test_data_list_view);
        final ImageAdapter adapter = new ImageAdapter(mList, TestData.this);
        listView.setAdapter(adapter);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference mDataRef = storage.getReferenceFromUrl("gs://cosmopent-4c2ff.appspot.com");

        mRef = FirebaseDatabase.getInstance().getReference().child("DATA").child("TestData");
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    Log.e("TestData", "onDataChange: " + snap.getKey());
                    StorageReference mPath = mDataRef.child("M33TAKAHASHI/" + snap.getKey() + ".jpeg");
                    mPath.getBytes(1020*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Log.e("TestData", "onSuccess: " + "successfully downloaded image" );
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0, bytes.length);
                            mList.add(bitmap);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mRef.addValueEventListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(listener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mRef.removeEventListener(listener);
        Intent intent = new Intent(TestData.this, ContourMappingActivity.class);
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
            mRef.removeEventListener(listener);
            Intent intent = new Intent(TestData.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.ssrprojects.cosmopent.CustomAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssrprojects.cosmopent.R;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
ArrayList<Bitmap> mList;
Context context;


public ImageAdapter(ArrayList<Bitmap> mList, Context context){
    this.mList = mList;
    this.context = context;
}

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_image, null);
        ImageView imageView= convertView.findViewById(R.id.image_holder);
        TextView textView = convertView.findViewById(R.id.image_sno);
        String a = String.valueOf(position + 1);
        textView.setText(a);
        imageView.setImageBitmap(mList.get(position));

    return convertView;
    }
}

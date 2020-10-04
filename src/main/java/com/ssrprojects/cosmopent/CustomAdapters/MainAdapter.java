package com.ssrprojects.cosmopent.CustomAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ssrprojects.cosmopent.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdapter extends BaseAdapter {
    ArrayList<HashMap> mList;
    Context context;

    public MainAdapter(ArrayList<HashMap> mList, Context context){
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

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_view_container, null);

        TextView mText = convertView.findViewById(R.id.list_text);
        mText.setText(mList.get(position).get("NAME").toString());

        TextView mSubText = convertView.findViewById(R.id.list_sub_text);
        mSubText.setText(mList.get(position).get("SUB").toString());

        return convertView;
    }
}

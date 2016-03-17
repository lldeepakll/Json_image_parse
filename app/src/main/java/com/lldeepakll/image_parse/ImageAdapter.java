package com.lldeepakll.image_parse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Deepak kumar on 17-03-2016.
 * Image Adapter class
 */
public class ImageAdapter extends ArrayAdapter<Model> {

    private MyViewHolder holder;
    private ArrayList<Model> model;
    private LayoutInflater inflater;
    private int resource;
    private static final String TAG = "ImageAdapter";

    public ImageAdapter(Context context, int resource,ArrayList<Model> model) {
        super(context, resource, model);
        Log.d(TAG, "Image Adapter constructor");
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.model = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            holder = new MyViewHolder();
            view = inflater.inflate(resource,null);
            holder.mImageView = (ImageView)view.findViewById(R.id.image);
            view.setTag(holder);
        }else{
            holder = (MyViewHolder)view.getTag();
        }
        String str = model.get(position).getImageUrl();
        Picasso.with(getContext()).load(str).placeholder(R.mipmap.ic_launcher).into(holder.mImageView);
        return view;
    }

    static class MyViewHolder{
            public ImageView mImageView;
    }
}

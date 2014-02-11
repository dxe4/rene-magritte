package org.renemagritte;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> itemList = new ArrayList<String>();

    private HashMap<Integer, ImageView> views;

    private final String BUNDLE_URI = "uri";
    private final String BUNDLE_POS = "pos";
    private final String BUNDLE_BM = "bm";

    public ImageAdapter(Context c) {

        mContext = c;
        views = new HashMap<Integer, ImageView>();
    }

    void add(String path) {
        itemList.add(path);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }


        Bundle b = new Bundle();
        b.putString(BUNDLE_URI, itemList.get(position));
        b.putInt(BUNDLE_POS, position);
        views.put(position, imageView);
        new LoadImage().execute(b);
        return imageView;
    }
}

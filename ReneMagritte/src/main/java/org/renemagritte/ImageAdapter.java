package org.renemagritte;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> itemList;
    private Map<Integer, ImageView> views;
    private ImageLoadFactory imageLoadFactory;

    private final String BUNDLE_URI = "uri";
    private final String BUNDLE_POS = "pos";
    private final String BUNDLE_BM = "bm";

    public ImageAdapter(Context c) {
        mContext = c;
        views = new ConcurrentHashMap<Integer, ImageView>();
        itemList = new ArrayList<String>();
        final int memClass = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        final int cacheSize = 1024 * 1024 * memClass / 8;
        imageLoadFactory = ImageLoadFactory.getFactory(views, this.getClass(), cacheSize);
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
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_URI, itemList.get(position));
        bundle.putInt(BUNDLE_POS, position);
        views.put(position, imageView);
        imageView.setImageResource(R.drawable.ic_launcher);
        //new LoadImageTask(mContext, views, cache).execute(bundle);
        imageLoadFactory.load(this.getClass(), bundle);
        return imageView;
    }
}

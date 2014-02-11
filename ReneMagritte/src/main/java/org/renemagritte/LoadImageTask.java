package org.renemagritte;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by foobar on 11/02/14.
 */
public class LoadImageTask extends AsyncTask<Bundle, Void, Bundle> {

    private final String BUNDLE_URI = "uri";
    private final String BUNDLE_POS = "pos";
    private final String BUNDLE_BM = "bm";
    private Context mContext;
    private HashMap<Integer, ImageView> views;

    public LoadImageTask(Context c){
        mContext = c;
        views = new HashMap<Integer, ImageView>();
    }

    @Override
    protected Bundle doInBackground(Bundle... b) {
        Bitmap bm = null;
        String path = b[0].getString(BUNDLE_URI);
        bm = decodeSampledBitmapFromUri(path, 50, 50);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_BM, bm);
        bundle.putInt(BUNDLE_POS, b[0].getInt(BUNDLE_POS));
        bundle.putString(BUNDLE_URI, path);
        return bundle;
    }

    @Override
    protected void onPostExecute(Bundle result) {
        super.onPostExecute(result);
        ImageView view = views.get(result.getInt(BUNDLE_POS));
        Bitmap bm = (Bitmap) result.getParcelable(BUNDLE_BM);

        if (bm != null) {
            view.setImageBitmap(bm);
            view.setOnClickListener(new CustomOnClickListener(result.getString(BUNDLE_URI)));
        }

    }

    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

        Bitmap bm = null;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    public int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    private class CustomOnClickListener implements View.OnClickListener {

        private String path;

        public CustomOnClickListener(String path) {
            this.path = path;
        }

        @Override
        public void onClick(View v) {
            new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        Log.d("We are not sorry, nothing went wrong", " --- ");
                    } catch (Exception e) {
                        Log.e("We are sorry, Something went wrong", Log.getStackTraceString(e));
                    }
                    return null;
                }
            }.execute(null, null, null);
        }
    }


}

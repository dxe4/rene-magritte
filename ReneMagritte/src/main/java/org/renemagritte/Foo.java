package org.renemagritte;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;

import java.io.File;


public class Foo extends Activity {

    private ImageAdapter imageAdapter;
    private static final int DEBUG_LIMIT = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foo_layout);
        initImages();
    }

    private File[] getCameraFiles(){
        File rootsd = Environment.getExternalStorageDirectory();
        File dcim = new File(rootsd.getAbsolutePath() + "/DCIM/CAMERA");
        return dcim.listFiles();
    }

    private void initImages() {
        GridView gridview = (GridView) this.findViewById(R.id.gridview);
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);
        File[] files = getCameraFiles();
        int count = 0;
        for (File file : files) {
            if (file.getName().startsWith(".") || file.getName().equals("thumbnails")) {
                continue;
            }
            imageAdapter.add(file.getAbsolutePath());
            count++;
            if (count >= DEBUG_LIMIT) {
                break;
            }
        }
    }

}

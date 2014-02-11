package org.renemagritte;

import android.app.Activity;
import android.os.Bundle;


public class Foo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.foo_layout);

        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

}

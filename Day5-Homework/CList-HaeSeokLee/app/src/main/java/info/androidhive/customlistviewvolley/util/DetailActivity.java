package info.androidhive.customlistviewvolley.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;

/**
 * Created by HaeSeok on 2016-01-23.
 */
public class DetailActivity extends Activity {

    private String mUrl = null;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("image");
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);
        thumbNail.setImageUrl(mUrl,imageLoader);
    }
}

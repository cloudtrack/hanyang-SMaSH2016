package info.androidhive.customlistviewvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;

public class Dialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Intent intent = getIntent();

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//        getActionBar().setTitle("hello");
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail_dialog);
        // thumbnail image
        thumbNail.setImageUrl(intent.getStringExtra("thumnail"), imageLoader);
    }
}

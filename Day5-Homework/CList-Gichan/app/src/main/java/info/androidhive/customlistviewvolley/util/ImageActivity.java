package info.androidhive.customlistviewvolley.util;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        // thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView image = (NetworkImageView) findViewById(R.id.image);

        String image_link = getIntent().getStringExtra("image_url");

        image.setImageUrl(image_link, imageLoader);
    }
}

package info.androidhive.customlistviewvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;

public class SubActivity extends Activity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent i = new Intent();
        String temp = i.getStringExtra("ItemUrl");
        //if(temp == null)
        //    Toast.makeText(getApplicationContext(),"fuck",Toast.LENGTH_LONG).show();
        NetworkImageView network = (NetworkImageView) findViewById(R.id.itemthumbnail);
        network.setImageUrl(temp, imageLoader);
    }
}

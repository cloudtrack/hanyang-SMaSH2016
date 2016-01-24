package info.androidhive.customlistviewvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Band;

public class Listitem extends Activity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);
        Intent i = getIntent();
        Band band = (Band) i.getSerializableExtra("band");

        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.itemthumbnail);
        TextView title = (TextView) findViewById(R.id.itemtitle);
        TextView genre = (TextView) findViewById(R.id.itemgenre);
        TextView year = (TextView) findViewById(R.id.itemreleaseYear);

        // thumbnail image
        thumbNail.setImageUrl(band.getThumbnailUrl(), imageLoader);

        // title
        title.setText(band.getTitle());

        // genre
        String genreStr = "";
        for (String str : band.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        // release year
        year.setText(String.valueOf(band.getYear()));
    }
}
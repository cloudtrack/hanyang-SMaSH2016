package info.androidhive.customlistviewvolley;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import info.androidhive.customlistviewvolley.app.AppController;

public class Main2Activity extends Activity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("movieInfo");

//        TextView mvInfoText = (TextView) findViewById(R.id.mvInfo);
//        mvInfoText.setText(text);

        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail2);

        thumbNail.setImageUrl(text, imageLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package info.androidhive.customlistviewvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Football;


public class AnotherActivity extends Activity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent = getIntent();
        Football football =  (Football)intent.getSerializableExtra("footballclub");
        String url = football.getThumbnailUrl();
        NetworkImageView image = (NetworkImageView)findViewById(R.id.thumbnailBig);
        image.setImageUrl(url, imageLoader);
        TextView teamName = (TextView)findViewById(R.id.teamName);
        String name = football.getTeamName();
        teamName.setText(name);
    }
    public void onBackButtonClicked(View v) {
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_another, menu);
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

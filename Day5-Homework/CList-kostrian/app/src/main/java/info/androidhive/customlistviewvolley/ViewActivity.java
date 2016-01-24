package info.androidhive.customlistviewvolley;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;

public class ViewActivity extends Activity {
    NetworkImageView viewerImg;
    TextView viewerTitle;
    TextView viewerRank;
    TextView viewerLastRank;
    TextView viewerArtist;
    Button viewerBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewerImg = (NetworkImageView)findViewById(R.id.viewer_img);

        viewerArtist = (TextView) findViewById(R.id.viewer_artist);
        viewerBack = (Button) findViewById(R.id.viewer_back);
        viewerTitle = (TextView) findViewById(R.id.viewer_title);
        viewerRank = (TextView)findViewById(R.id.viewer_rank);
        viewerLastRank = (TextView)findViewById(R.id.viewer_lastrank);

        viewerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        viewerImg.setImageUrl(bundle.getString("imgUrl"), AppController.getInstance().getImageLoader());
        viewerTitle.setText(bundle.getString("title"));
        viewerRank.setText(String.valueOf(bundle.getInt("rank")));
        viewerArtist.setText(bundle.getString("artist"));
        if (bundle.getInt("last_rank") < 0) {
            viewerLastRank.setText("New");
            viewerLastRank.setTextColor(Color.BLUE);
        }
        int diff = bundle.getInt("rank") - bundle.getInt("last_rank");
        if (diff == 0) {
            viewerLastRank.setText("(-)");
//            viewerLastRank.setTextColor(Color.argb(1, 0x66, 0x66, 0x66));
            viewerLastRank.setTextColor(Color.GRAY);
        }
        else if (diff > 0) {
            viewerLastRank.setText("(" + diff + ")");
//            viewerLastRank.setTextColor(Color.argb(1, 0, 0xa5, 0xff));
            viewerLastRank.setTextColor(Color.BLUE);
        }
        else {
            viewerLastRank.setText("(" + diff + ")");
//            viewerLastRank.setTextColor(Color.argb(1, 0xff, 0x9b, 0));
            viewerLastRank.setTextColor(Color.RED);
        }
    }

}

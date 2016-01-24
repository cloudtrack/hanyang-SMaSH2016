package info.androidhive.customlistviewvolley;

/**
 * Created by DEV_HUNA on 2016-01-23.
 */

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;

        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.NetworkImageView;

        import java.net.URL;

        import info.androidhive.customlistviewvolley.app.AppController;

public class ImageActivity extends Activity{
    /** Called when the activity is first created. */
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        Intent intent = getIntent();
        String imageurl = intent.getStringExtra("imageURL");
        NetworkImageView well = (NetworkImageView)findViewById(R.id.imageView);
        well.setImageUrl(imageurl, imageLoader);
        Button terminate = (Button)findViewById(R.id.button);
        terminate.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {

// TODO Auto-generated method stub
                finish();
// 액티비티를 종료합니다.
            }

        });

    }
}

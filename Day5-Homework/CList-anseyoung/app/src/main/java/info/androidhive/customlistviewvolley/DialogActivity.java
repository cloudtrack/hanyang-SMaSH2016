package info.androidhive.customlistviewvolley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Supporter;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Intent intent = getIntent();
        Supporter obj = (Supporter) intent.getSerializableExtra("champion");
//        Toast.makeText(getApplicationContext(),obj.getName(),Toast.LENGTH_LONG).show();
        ImageLoader imgloader = AppController.getInstance().getImageLoader();
        if (imgloader == null)
            imgloader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);

        if(obj.getName() == "Sona"){
            thumbNail.setBackgroundResource(R.anim.frame_animation);
        }
        TextView name = (TextView) findViewById(R.id.name);
        TextView pickRate = (TextView) findViewById(R.id.pickRate);
        TextView winRate = (TextView) findViewById(R.id.winRate);
        TextView isCC = (TextView) findViewById(R.id.isCC);
        TextView isHeal = (TextView) findViewById(R.id.isHeal);
        TextView part = (TextView) findViewById(R.id.part);

        thumbNail.setImageUrl(obj.getThumbnailUrl(),imgloader);
        name.setText("<" + obj.getName() + ">");
        pickRate.setText("Pick Rate : " + obj.getPickRate());
        winRate.setText("Win Rate : " + obj.getWinRate());
        String isccStr = "";
        for(String str : obj.getIsCC()){
            isccStr += str + ", ";
        }
        isccStr = isccStr.length() > 0 ? isccStr.substring(0, isccStr.length() - 2) : isccStr;
        isCC.setText("CC : " + isccStr);
        String ishealStr = "";
        for (String str : obj.getIsHeal()) {
            ishealStr += str + ", ";
        }
        ishealStr = ishealStr.length() > 0 ? ishealStr.substring(0, ishealStr.length() - 2) : ishealStr;
        isHeal.setText("Heal or Shield : " + ishealStr);
        part.setText(obj.getPart());

    }
}

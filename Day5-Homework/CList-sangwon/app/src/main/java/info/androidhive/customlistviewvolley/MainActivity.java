package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Music;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

public class MainActivity extends AppCompatActivity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// Movies json url
	private static String url;
	private ProgressDialog pDialog;
	private List<Music> musicList = new ArrayList<Music>();
	private ListViewCompat listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        url = (String)getResources().getString(R.string.server_url);
		setContentView(R.layout.activity_main);

        setTitle("Top 10 Music");
		listView = (ListViewCompat) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, musicList);
		listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music music = musicList.get(position);
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                View v = View.inflate(AppController.getInstance(),R.layout.fullscreen_image,(ViewGroup)findViewById(R.id.list).getParent());
                ((NetworkImageView)v.findViewById(R.id.full_image)).setImageUrl(music.getThumbnailUrl(),imageLoader);
                View.OnClickListener remover = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View screen;
                        if(v.getId() == R.id.full_screen)
                            screen = v;
                        else screen = (View)v.getParent();
                        ViewManager container = (ViewManager)screen.getParent();
                        container.removeView(screen);
                    }
                };
                v.setOnClickListener(remover);
                v.findViewById(R.id.full_screen_exit_btn).setOnClickListener(remover);
                v.findViewById(R.id.full_image).setOnClickListener(remover);
            }
        };
        listView.setOnItemClickListener(listener);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));
        Log.d(TAG, "Json Request to server");
		// Creating volley request obj
		JsonArrayRequest musicReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
                        Log.d(TAG, "Get Json Response");
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Music music = new Music();
								music.setTitle(obj.getString("title"));
								music.setThumbnailUrl(obj.getString("image"));
								music.setRating(((Number) obj.get("rating"))
										.intValue());
								music.setAlbum(obj.getString("album"));

								// Genre is json array
								JSONArray artistArray = obj.getJSONArray("artist");
								ArrayList<String> artist = new ArrayList<String>();
								for (int j = 0; j < artistArray.length(); j++) {
									artist.add((String) artistArray.get(j));
								}
								music.setArtist(artist);

								// adding movie to movies array
								musicList.add(music);

							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(musicReq);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

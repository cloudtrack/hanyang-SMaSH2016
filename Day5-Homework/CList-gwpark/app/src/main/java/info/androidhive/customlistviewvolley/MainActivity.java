package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Band;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// Movies json url
	private static final String url = "http://ec2-52-79-39-99.ap-northeast-2.compute.amazonaws.com/bands.json";
	private ProgressDialog pDialog;
	private List<Band> bandList = new ArrayList<Band>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, bandList);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				clickItem((int)id);
			}
		});
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));

		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);
								Band band = new Band();
								band.setTitle(obj.getString("title"));
								band.setThumbnailUrl(obj.getString("image"));
								band.setYear(obj.getInt("releaseYear"));

								// Genre is json array
								JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								band.setGenre(genre);

								// adding movie to movies array
								bandList.add(band);

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
		AppController.getInstance().addToRequestQueue(movieReq);
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

	private void clickItem(int num) {
		Intent i = new Intent(this, Listitem.class);
        i.putExtra("band", bandList.get(num));
        startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

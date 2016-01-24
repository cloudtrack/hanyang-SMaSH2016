package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.model.Supporter;

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

	// Champions json url
	private static final String url = "http://ec2-52-79-81-37.ap-northeast-2.compute.amazonaws.com/json/champs.json";
	private ProgressDialog pDialog;
	private List<Supporter> champList = new ArrayList<Supporter>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, champList);
		listView.setAdapter(adapter);

		// sending the intent to the DialogActivity
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this,adapter.getName(position) , Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,DialogActivity.class);
				Supporter s = champList.get((int)id);
				intent.putExtra("champion",s);
				startActivity(intent);
			}
		});


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
								Supporter champ = new Supporter();
								champ.setName(obj.getString("name"));
								champ.setThumbnailUrl(obj.getString("image"));
								champ.setPickRate(obj.getString("pickRate"));
								champ.setWinRate(obj.getString("winRate"));


								// isCC is json array
								JSONArray isccArry = obj.getJSONArray("isCC");
								ArrayList<String> isCC = new ArrayList<String>();
								for(int j = 0;j < isccArry.length();j++) {
									isCC.add((String) isccArry.get(j));
								}
								champ.setIsCC(isCC);

								// isHeal is json array
								JSONArray ishealArry = obj.getJSONArray("isHeal");
								ArrayList<String> isHeal = new ArrayList<String>();
								for(int j = 0;j < ishealArry.length();j++) {
									isHeal.add((String) ishealArry.get(j));
								}
								champ.setIsHeal(isHeal);
								champ.setPart(obj.getString("part"));

								// adding champ to champs array
								champList.add(champ);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

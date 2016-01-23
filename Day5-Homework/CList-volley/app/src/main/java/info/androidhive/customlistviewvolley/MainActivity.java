package info.androidhive.customlistviewvolley;

import info.androidhive.customlistviewvolley.adater.CustomListAdapter;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Book;
import info.androidhive.customlistviewvolley.model.Movie;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();
	private String apiKey = "fb254b8a84403ee1002b353874d548d7";
	private String url;

	private Toast toast;

	// Movies json url
//	private static final String url = "http://okdevtv.com/samples/json/movies.json";
//	public static String url;

	private ProgressDialog pDialog;
	private List<Book> bookList = new ArrayList<Book>();
	private ListView listView;
	private CustomListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		adapter = new CustomListAdapter(this, bookList);
		listView.setAdapter(adapter);


		// Showing progress dialog before making http request



		final EditText input_query = (EditText) findViewById(R.id.input_title);

		Button btn_search = (Button) findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				String query = input_query.getText().toString();
				url = "http://apis.daum.net/search/book?apikey=" + apiKey + "&q="+ query +"&output=json";
//				pDialog = new ProgressDialog(getApplicationContext());
//				pDialog.setMessage("Loading...");
//				pDialog.show();

				makeJson();


			}
		});

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Book data = adapter.bookItems.get(position);
				Toast toast = Toast.makeText(getApplicationContext(), data.getTitle(), Toast.LENGTH_LONG);
				toast.show();
				Intent intent = new Intent(getApplicationContext(), Dialog.class);
				intent.putExtra("title", data.getTitle());
				intent.putExtra("thumnail", data.getThumnail());

				startActivity(intent);


			}
		});
	}

	public void makeJson(){
		JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject res) {
				try {
//					bookList = new ArrayList<Book>();
					JSONObject temp_res = res.getJSONObject("channel");
					JSONArray response = temp_res.getJSONArray("item");


					Log.d(TAG, response.toString());
//							Toast toast = Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG);
//							toast.show();
//							toast = Toast.makeText(getApplicationContext(), response.length(), Toast.LENGTH_LONG);
//							toast.show();
					hidePDialog();

					// Parsing json
					for (int i = 0; i < response.length(); i++) {
						try {

							JSONObject obj = response.getJSONObject(i);
							Book book = new Book();
							book.setTitle(obj.getString("title"));
							book.setThumnail(obj.getString("cover_s_url"));
							book.setPrice((obj.getString("sale_price")));
							book.setDate(obj.getString("pub_date"));
							book.setAuthor(obj.getString("author"));
							book.setCategory(obj.getString("category"));

							// adding movie to movies array
							bookList.add(book);

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

					// notifying list adapter about data changes
					// so that it renders the list view with updated data
					adapter.notifyDataSetChanged();

				}
				catch (Exception e){
					Toast toast = Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
					toast.show();
				}

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				hidePDialog();
				Log.d(TAG, error.getMessage());
				Log.d(TAG, "????????@#"+error.toString());
				toast = Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
				toast.show();

			}
		});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
		hidePDialog();
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

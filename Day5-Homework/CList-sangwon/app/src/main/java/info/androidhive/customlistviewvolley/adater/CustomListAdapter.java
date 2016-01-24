package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Music;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Music> musicItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Music> musicItems) {
		this.activity = activity;
		this.musicItems = musicItems;
	}

	@Override
	public int getCount() {
		return musicItems.size();
	}

	@Override
	public Object getItem(int location) {
		return musicItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.music_list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.music_thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.music_title);
		TextView rating = (TextView) convertView.findViewById(R.id.music_rating);
		TextView artist = (TextView) convertView.findViewById(R.id.music_artist);
		TextView album = (TextView) convertView.findViewById(R.id.music_album);

		// getting music data for the row
		Music m = musicItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

		// title
		title.setText(m.getTitle());

		// rating
		rating.setText("순위: " + String.valueOf(m.getRating()));

		// artist
		String artistStr = "";
		for (String str : m.getArtist()) {
			artistStr += str + ", ";
		}
		artistStr = artistStr.length() > 0 ? artistStr.substring(0,
				artistStr.length() - 2) : artistStr;
		artist.setText(artistStr);

		// album name
		album.setText(m.getAlbum());

		return convertView;
	}

}

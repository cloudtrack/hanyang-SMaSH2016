package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Song;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
	private List<Song> songItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Song> songItems) {
		this.activity = activity;
		this.songItems = songItems;
	}

	@Override
	public int getCount() {
		return songItems.size();
	}

	@Override
	public Object getItem(int location) {
		return songItems.get(location);
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
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();

		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rank = (TextView) convertView.findViewById(R.id.rank);
		TextView lastrank = (TextView) convertView.findViewById(R.id.lastrank);
		TextView artist = (TextView) convertView.findViewById(R.id.artist);

		// getting movie data for the row
		Song s = songItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(s.getAlbumImgUrl(), imageLoader);

		// title
		title.setText(s.getTitle());

		// rank
		int rankVal = s.getRank();
		rank.setText(String.valueOf(rankVal));

		// lastrank
		String lastRankStr = "";
		int lastRankInt = s.getLast_rank();
		if (lastRankInt < 0) {
			lastRankStr = "New";
			lastrank.setTextAppearance(android.R.style.TextAppearance_Small);
			lastrank.setTextColor(Color.BLUE);
		}
		else {
			lastRankStr = String.valueOf(lastRankInt);
			int differ = rankVal - lastRankInt;
			if (differ > 0) {
				lastrank.setTextColor(Color.BLUE);
				lastRankStr = String.valueOf(differ);
			}
			else if (differ < 0){
				lastrank.setTextColor(Color.RED);
				lastRankStr = String.valueOf(differ);
			}
			else {
				lastrank.setTextColor(Color.GRAY);
				lastRankStr = "-";
			}
		}
		lastrank.setText(lastRankStr);

		// release year
		artist.setText(s.getArtist());

		return convertView;
	}

}

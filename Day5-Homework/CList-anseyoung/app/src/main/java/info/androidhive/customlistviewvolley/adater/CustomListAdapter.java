package info.androidhive.customlistviewvolley.adater;

import info.androidhive.customlistviewvolley.R;
import info.androidhive.customlistviewvolley.app.AppController;
import info.androidhive.customlistviewvolley.model.Movie;
import info.androidhive.customlistviewvolley.model.Supporter;

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
	private List<Supporter> supportItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Supporter> supportItems) {
		this.activity = activity;
		this.supportItems = supportItems;
	}

	@Override
	public int getCount() {
		return supportItems.size();
	}

	@Override
	public Object getItem(int location) {
		return supportItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public String getName(int position){
		return supportItems.get(position).getName();
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
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView pickRate = (TextView) convertView.findViewById(R.id.pickRate);
		TextView winRate = (TextView) convertView.findViewById(R.id.winRate);
		TextView isCC = (TextView) convertView.findViewById(R.id.isCC);
		TextView isHeal = (TextView) convertView.findViewById(R.id.isHeal);
		TextView part = (TextView) convertView.findViewById(R.id.part);

		// getting champion data for the row
		Supporter m = supportItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

		// name
		name.setText(m.getName());

		// pickRate
		pickRate.setText("Pick Rate : " + m.getPickRate());

		// winRate
		winRate.setText("Win Rate : " + m.getWinRate());

		// isCC
		String isccStr = "";
		for (String str : m.getIsCC()) {
			isccStr += str + ", ";
		}
		isccStr = isccStr.length() > 0 ? isccStr.substring(0, isccStr.length() - 2) : isccStr;
		isCC.setText("CC : " + isccStr);

		// isHeal
		String ishealStr = "";
		for (String str : m.getIsHeal()) {
			ishealStr += str + ", ";
		}
		ishealStr = ishealStr.length() > 0 ? ishealStr.substring(0, ishealStr.length() - 2) : ishealStr;
		isHeal.setText("Heal or Shield : " + ishealStr);

		// part
		part.setText(m.getPart());

		return convertView;
	}

}

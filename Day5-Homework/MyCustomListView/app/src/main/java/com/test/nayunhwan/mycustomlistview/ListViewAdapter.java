package com.test.nayunhwan.mycustomlistview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by nayunhwan on 16. 1. 23..
 */
public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private Context mContext = null;
    private ArrayList<ListData> mListData = new ArrayList<ListData>();
    private LayoutInflater inflater;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ListViewAdapter(Context mContext){
        super();
        this.mContext = mContext;
    }

    public void addItem(Drawable thumbnail, String title){
        ListData data = new ListData(thumbnail, title);
        mListData.add(data);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
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
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Book m = bookItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumnail(), imageLoader);

        // title
        title.setText(m.getTitle());

        // rating
        rating.setText("Rating: " +m.getAuthor());

        // genre

        genre.setText(m.getCategory());

        // release year
        year.setText(String.valueOf(m.getDate()));

        return convertView;

        return null;
    }
}

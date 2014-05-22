package com.area51.adapters;

import java.util.ArrayList;

import com.area51.models.Tweet;
import com.area51.proyecto.R;
import com.area51.utils.BitmapManager;
import com.area51.utils.DateUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends ArrayAdapter<Tweet> {

	ArrayList<Tweet> arrayTweet;
	Context context;

	public TweetAdapter(Context context, int resource,
			ArrayList<Tweet> arrayTweet) {
		super(context, resource, arrayTweet);
		this.arrayTweet = arrayTweet;
		this.context = context;
	}

	static class ViewHolder {
		public ImageView avatar;
		public TextView name;
		public TextView user_name;
		public TextView text;
		public TextView date;
	}

	@Override
	public View getView(int position, View vista, ViewGroup parent) {
		if (vista == null) {
			vista = LayoutInflater.from(context).inflate(R.layout.row_tweet,
					parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.avatar = (ImageView) vista.findViewById(R.id.avatar);
			viewHolder.name = (TextView) vista.findViewById(R.id.name);
			viewHolder.user_name = (TextView) vista
					.findViewById(R.id.user_name);
			viewHolder.text = (TextView) vista.findViewById(R.id.text);
			viewHolder.date = (TextView) vista.findViewById(R.id.date);

			vista.setTag(viewHolder);
		}
		
		ViewHolder holder=(ViewHolder)vista.getTag();
		holder.name.setText(arrayTweet.get(position).getName());
		holder.user_name.setText(arrayTweet.get(position).getScreenName());
		holder.text.setText(arrayTweet.get(position).getText());
		holder.date.setText(DateUtils.setDateFormat(arrayTweet.get(position).getCreatedAt()));
		
		String imageUrl=arrayTweet.get(position).getProfileImageUrl();
		imageUrl=imageUrl.replace("normal", "200x200");
		BitmapManager.getInstance().loadBitmap(imageUrl, holder.avatar);
		
		
		return vista;
	}

}

package com.area51.adapters;

import java.util.ArrayList;

import com.area51.libraries.BitmapManager;
import com.area51.libraries.DateUtils;
import com.area51.models.Tweet;
import com.area51.proyecto.R;
import com.area51.utils.NetworkApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends ArrayAdapter<Tweet> {

	ArrayList<Tweet>arrayTweet;
	Context context;
	
	public TweetAdapter(Context context, 
			int resource, 
			ArrayList<Tweet>arrayTweet) {
		
		super(context, resource, arrayTweet);
		
		this.arrayTweet = arrayTweet;
		this.context = context;
		
	}
	
	static class ViewHolder{
		
		public ImageView avatar;
		public TextView name;
		public TextView user_name;
		public TextView text;
		public TextView date;
		public Integer red;
		
	}
	
	@Override
	public View getView(int position, View vista, ViewGroup parent){
		
		if( vista == null ){
			
			vista = LayoutInflater.from(context)
					.inflate(R.layout.row_tweet, parent, false);
			
			ViewHolder viewholder = new ViewHolder();
			viewholder.avatar = (ImageView)vista.findViewById(R.id.avatar);
			viewholder.name = (TextView)vista.findViewById(R.id.name);
			viewholder.user_name = (TextView)vista.findViewById(R.id.user_name); 
			viewholder.text = (TextView)vista.findViewById(R.id.text);
			viewholder.date = (TextView)vista.findViewById(R.id.date);
			
			if( new NetworkApp(getContext()).getNetwork() ){
				viewholder.red = 1;
			}else{
				viewholder.red = 0;
			}
			
			
			
			vista.setTag(viewholder);
		}
		
		ViewHolder holder = (ViewHolder)vista.getTag();
		holder.name.setText( arrayTweet.get(position).getName() );
		holder.user_name.setText( arrayTweet.get(position).getScreenName() );
		holder.text.setText( arrayTweet.get(position).getText() );
		holder.date.setText( 
				DateUtils.setDateFormat(arrayTweet.get(position).getCreatedAt()) 
				);
		
		if( holder.red == 1 ){

			String imageUrl = arrayTweet.get(position).getProfileImageUrl(); 
			imageUrl = imageUrl.replace("normal", "200x200");
			BitmapManager.getInstance().loadBitmap( imageUrl , holder.avatar);
		}

		
		
		//imageUrl = imageUrl.replace("normal", "bigger");
		//imageUrl = imageUrl.replace("normal", "400x400");
		
		return vista;
	}
	
	
	
	
	

}

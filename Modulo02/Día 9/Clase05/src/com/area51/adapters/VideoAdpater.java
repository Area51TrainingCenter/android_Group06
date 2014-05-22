package com.area51.adapters;

import java.util.ArrayList;

import com.area51.clase05.R;
import com.area51.models.VideoModel;
import com.area51.utils.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoAdpater extends ArrayAdapter<VideoModel> {
	Context context;
	ArrayList<VideoModel> lista;

	public VideoAdpater(Context context, int resource,
			ArrayList<VideoModel> lista) {
		super(context, resource,lista);
		this.context = context;
		this.lista = lista;
	}

	static class ViewHolder {
		public ImageView imageVideo;
		public TextView nameVideo;
		public TextView descriptionVideo;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.fragment_datail,parent,false);
			
			ViewHolder viewHolder=new ViewHolder();
			viewHolder.imageVideo=(ImageView)convertView.findViewById(R.id.imageVideo);
			viewHolder.nameVideo=(TextView)convertView.findViewById(R.id.nameVideo);
			viewHolder.descriptionVideo=(TextView)convertView.findViewById(R.id.descriptionVideo);
			
			convertView.setTag(viewHolder);
		}
		
		ViewHolder holder=(ViewHolder)convertView.getTag();
		ImageLoader loader=new ImageLoader(context);
		loader.DisplayImage(lista.get(position).getPathImageVideo(), holder.imageVideo);
		holder.nameVideo.setText(lista.get(position).getNameVideo());
		holder.descriptionVideo.setText(lista.get(position).getDescriptionVideo());
		
		return convertView;
	}
}

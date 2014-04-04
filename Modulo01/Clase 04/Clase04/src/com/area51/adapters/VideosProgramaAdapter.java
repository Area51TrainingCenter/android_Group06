package com.area51.adapters;

import java.util.ArrayList;


import com.area51.clase04.R;
import com.area51.model.VideosModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideosProgramaAdapter 
	extends ArrayAdapter<VideosModel> {
	
	Context context;
	ArrayList<VideosModel>listaVideo;
	

	public VideosProgramaAdapter(Context context, int resource,
			ArrayList<VideosModel>listaVideo) {
		super(context, resource, listaVideo);
		
		//Referenciamos parametros del constructor
		//hacia variables globales del adapter
		//para poder usarlos
		
		this.context = context;
		this.listaVideo = listaVideo;

	}
	
	static class ViewHolder{
		
		public ImageView imageVideo;
		public TextView tituloVideo;
		public TextView descripcionVideo;
		
	}
	
	@Override
	public View getView(int position, View vistaActual,
			ViewGroup parent) {
		
		if( vistaActual == null ){
			
			vistaActual = LayoutInflater
					.from(context)
					.inflate(R.layout.item_video, parent, false);
			
			ViewHolder viewholder = new ViewHolder();
			
			viewholder.imageVideo = (ImageView)vistaActual
					.findViewById(R.id.imageVideo);
			
			viewholder.tituloVideo = (TextView)vistaActual
					.findViewById(R.id.tituloVideo);
			
			viewholder.descripcionVideo = (TextView)vistaActual
					.findViewById(R.id.descripcionVideo);
			
			//Guardamos la vista estatica en el .tag
			//para referenciarlo en memoria
			vistaActual.setTag(viewholder);
			
		}
		
		//Creamos una instancia del ViewHolder
		//en base al .tag de vistaActual
		ViewHolder holder = (ViewHolder)vistaActual.getTag();
		
		holder.imageVideo
			.setImageResource( listaVideo.get(position)
				.getImagenVideo() );
		
		holder.tituloVideo
			.setText( listaVideo.get(position)
					.getNombreVideo() );
		
		holder.descripcionVideo
			.setText( listaVideo.get(position)
				.getDescripcionVideo() );
		
		
		return vistaActual;
	}
	

}

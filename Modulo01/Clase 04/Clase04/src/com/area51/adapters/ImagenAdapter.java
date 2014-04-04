package com.area51.adapters;

import java.util.ArrayList;

import com.area51.clase04.R;
import com.area51.model.Imagen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagenAdapter extends ArrayAdapter<Imagen> {

	Context contexto;
	ArrayList<Imagen>lista;
	
	
	public ImagenAdapter(Context context, int resource, 
			ArrayList<Imagen> objects) {
		super(context, resource, objects);
		
		this.contexto = context;
		this.lista = objects;		
	}
		
	static class ViewHolder{
		
		public ImageView imagenMatriz;
		public TextView textoMatriz;
		
	}

	
	
	@Override
	public View getView(int position, View vistaActual, ViewGroup parent) {
		
		if( vistaActual == null ){			
			 			
			vistaActual = LayoutInflater
					.from( contexto )
					.inflate( R.layout.item_gridview
							, parent, false  );
			
			ViewHolder viewHolder = new ViewHolder();
			
			viewHolder.imagenMatriz 
				= (ImageView)vistaActual
				.findViewById(R.id.imagenMatriz);
			
			viewHolder.textoMatriz 
				= (TextView)vistaActual
				.findViewById( R.id.textoMatriz );
			
			vistaActual.setTag( viewHolder );
			
		}
		
		ViewHolder holder = (ViewHolder)vistaActual.getTag();
		
		holder.imagenMatriz
		 .setImageResource( lista.get(position).getRutaImagen() );
		
		holder.textoMatriz
		 .setText( lista.get(position).getNombreImagen() );
		
		return vistaActual;
	}
	
	
	
	

	
	
}

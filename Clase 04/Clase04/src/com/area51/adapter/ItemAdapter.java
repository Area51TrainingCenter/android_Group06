package com.area51.adapter;

import java.util.ArrayList;


import com.area51.clase04.R;
import com.area51.model.ItemModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ItemAdapter extends ArrayAdapter<ItemModel> {

	Context context;
	ArrayList<ItemModel> lista;
	
	public ItemAdapter(Context context, int resource, 
			ArrayList<ItemModel> lista) {
		super(context, resource, lista);
		this.context = context;
		this.lista = lista;
		
	}
	
	static class ViewHolder{
		
		public ImageView imagenItem;
		
	}
	
	@Override
	public View getView(int position, View vistaActual, 
			ViewGroup parent) {
		
		if( vistaActual == null ){
			vistaActual = LayoutInflater
					.from(context)
					.inflate( R.layout.item_gridview , parent , false);

			ViewHolder viewholder = new ViewHolder();
			
			viewholder.imagenItem 
				= (ImageView)vistaActual.findViewById(R.id.imagenItem);
			
			vistaActual.setTag(viewholder);
						
		}
		
		ViewHolder holder = (ViewHolder)vistaActual.getTag();
		
		holder.imagenItem
			.setImageResource( lista.get(position).getImageItem() );
		
		
		return vistaActual;
	}
	

}

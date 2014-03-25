package com.area51.adapters;

import java.util.ArrayList;

import com.area51.clase02.R;
import com.area51.models.Item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	Context contexto;
	ArrayList<Item>Arreglo;	
	
	
	public ItemAdapter(Context contexto, ArrayList<Item> arreglo) {
		super();
		this.contexto = contexto;
		Arreglo = arreglo;
	}
	

	@Override
	public int getCount() {
		
		return Arreglo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Arreglo.get( position ).getIdItem();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View vistaActual, ViewGroup parent) {
		
		
		if( vistaActual == null ){
			
			vistaActual = LayoutInflater
					.from(contexto)
					.inflate(R.layout.item_lista , parent, false);
		}
		
		//Item objetoItem = Arreglo.get(position);		
		ImageView imagenItem = (ImageView)vistaActual
				.findViewById( R.id.imagenItem );
		
		//imagenItem.setImageResource( objetoItem.getImagenItem() );		
		imagenItem.setImageResource( Arreglo.get(position).getImagenItem() );
		
		TextView nombreItem = (TextView)vistaActual
				.findViewById( R.id.nombreItem );
		
		nombreItem.setText( Arreglo.get(position).getNombreItem() );
		
		return vistaActual;
	}

}

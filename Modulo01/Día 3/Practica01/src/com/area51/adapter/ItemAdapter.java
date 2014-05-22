package com.area51.adapter;

import com.area51.practica01.R;
import com.area51.util.Constants;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	Activity actividad;
	Context contexto;

	public ItemAdapter(Activity actividad, Context contexto) {
		super();
		this.actividad = actividad;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		return Constants.lista.size();
	}

	@Override
	public Object getItem(int position) {
		return Constants.lista.get(position).getItem();
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	static class ViewHolder {
		public ImageView imageItem;
		public TextView nameItem;
	}

	@Override
	public View getView(int position, View vistaActual, ViewGroup parent) {
		if (vistaActual == null) {
			vistaActual = LayoutInflater.from(contexto).inflate(
					R.layout.item_lista, parent, false);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imageItem = (ImageView) vistaActual
					.findViewById(R.id.imageItem);
			viewHolder.nameItem = (TextView) vistaActual
					.findViewById(R.id.nameItem);
			vistaActual.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) vistaActual.getTag();
		holder.nameItem.setText(Constants.lista.get(position).getNombreItem());

		int imagenTemporal = actividad.getResources().getIdentifier(
				Constants.lista.get(position).getPathImageItem(), null,
				actividad.getPackageName());
		
		holder.imageItem.setImageDrawable(actividad.getResources().getDrawable(imagenTemporal));

		return vistaActual;
	}
}

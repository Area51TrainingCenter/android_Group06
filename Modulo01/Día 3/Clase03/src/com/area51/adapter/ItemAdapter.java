package com.area51.adapter;

import com.area51.clase03.R;
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

	// Se está implementando la clase Holder
	static class ViewHolder {
		public ImageView imageItem;
		public TextView nameItem;
	}

	@Override
	public View getView(int position, View vistaActual, ViewGroup parent) {
		// Se personaliza la lista que se va a mostrar en el listView
		if (vistaActual == null) {
			vistaActual = LayoutInflater.from(contexto).inflate(
					R.layout.item_lista, parent, false);
			// Se instancia la clase de ViewHolder
			ViewHolder viewHolder = new ViewHolder();
			// Se setean los datos para el objeto de ViewHolder
			viewHolder.imageItem = (ImageView) vistaActual
					.findViewById(R.id.imageItem);
			viewHolder.nameItem = (TextView) vistaActual
					.findViewById(R.id.nameItem);
			vistaActual.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) vistaActual.getTag();
		holder.nameItem.setText(Constants.lista.get(position).getNombreItem());

		//Obtenemos una imagen a partir de una cadena
		int imagenTemporal = actividad.getResources().getIdentifier(
				Constants.lista.get(position).getPathImageItem(), null,
				actividad.getPackageName());
		
		//Es enviarle un elemento drawable como recurso
		holder.imageItem.setImageDrawable(actividad.getResources().getDrawable(imagenTemporal));

		return vistaActual;
	}

}

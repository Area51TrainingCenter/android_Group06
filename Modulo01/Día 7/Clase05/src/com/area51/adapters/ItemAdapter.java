package com.area51.adapters;

import java.util.ArrayList;

import com.area51.clase05.R;
import com.area51.models.ItemGridViewModel;
import com.area51.utils.BitmapManager;
import com.area51.utils.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<ItemGridViewModel> {

	Activity activity;
	Context context;
	ArrayList<ItemGridViewModel> lista;

	public ItemAdapter(Activity activity, Context context, int resource,
			ArrayList<ItemGridViewModel> lista) {
		super(context, resource, lista);

		this.activity = activity;
		this.context = context;
		this.lista = lista;
	}

	static class ViewHolder {
		public ImageView imageItem;
		public TextView nameItem;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_gridview, parent, false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.imageItem = (ImageView) convertView
					.findViewById(R.id.imageItem);
			viewHolder.nameItem = (TextView) convertView
					.findViewById(R.id.nameItem);

			convertView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.nameItem.setText(lista.get(position).getNameItem());

		ImageLoader imageLoader = new ImageLoader(context);
		imageLoader.DisplayImage(lista.get(position).getPathImageItem(),
				holder.imageItem);

		/*
		 * BitmapManager.getInstance().loadBitmap(
		 * lista.get(position).getPathImageItem(), holder.imageItem);
		 */

		/*
		 * int imagenTemporal = activity.getResources().getIdentifier(
		 * lista.get(position).getPathImageItem(), null,
		 * activity.getPackageName());
		 * holder.imageItem.setImageDrawable(activity
		 * .getResources().getDrawable( imagenTemporal));
		 */

		return convertView;
	}
}

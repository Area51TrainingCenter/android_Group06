package com.area51.clase05;

import java.util.ArrayList;

import com.area51.adapters.VideoAdpater;
import com.area51.custom.NuevoListView;
import com.area51.models.VideoModel;
import com.area51.utils.Constants;
import com.area51.utils.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DatailActivity extends Activity {
	TextView nameProgram;
	ImageView imageProgram;
	ArrayList<VideoModel> lista;
	VideoAdpater adapter;
	NuevoListView listViewCustom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datail);

		nameProgram = (TextView) findViewById(R.id.nameProgram);
		imageProgram = (ImageView) findViewById(R.id.imageProgam);
		listViewCustom = (NuevoListView) findViewById(R.id.nuevoListView1);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Bundle parameter = getIntent().getExtras();
		int position = parameter.getInt("position");

		ImageLoader imageLoader = new ImageLoader(this);
		imageLoader.DisplayImage(Constants.lista.get(position)
				.getPathImageItem(), imageProgram);
		nameProgram.setText(Constants.lista.get(position).getNameItem());

		// Creamos para mostrar la lista de videos
		if (lista == null) {
			lista = new ArrayList<VideoModel>();
			for (int i = 0; i < 10; i++) {
				Log.d("LOG", "Entro"+i);
				lista.add(new VideoModel(i, "Video " + i,
						"Descripción del Video " + i, Constants.lista.get(
								position).getPathImageItem()));
			}
		}
		adapter = new VideoAdpater(this, R.layout.fragment_datail, lista);
		listViewCustom.setAdapter(adapter);
	}

}

package com.area51.fragments;

import com.area51.clase04.R;
import com.area51.util.Constantes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagenFragment extends Fragment {

	public String FRAGMENT_POSICION = "posicion";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View vistaFragment = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_detalle, container, false);

		ImageView imagenFragment = (ImageView) vistaFragment
				.findViewById(R.id.imagenFragment);
		TextView textoFragment = (TextView) vistaFragment
				.findViewById(R.id.textoFragment);
		
		Bundle bundle=getArguments();
		int parametro=bundle.getInt(FRAGMENT_POSICION);
		
		imagenFragment.setImageResource(Constantes.lista.get(parametro).getImageItem());
		textoFragment.setText(Constantes.lista.get(parametro).getNameItem());
		return vistaFragment;
	}
}

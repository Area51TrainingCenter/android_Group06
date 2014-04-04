package com.area51.clase04;

import java.util.ArrayList;

import com.aea51.custom.NuevoListView;
import com.area51.adapters.VideosProgramaAdapter;
import com.area51.model.VideosModel;
import com.area51.util.Constantes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends Activity {

	ImageView imagePrograma;
	TextView nombrePrograma;
	NuevoListView listaPrograma;
	VideosProgramaAdapter adapter;
	ArrayList<VideosModel>listaVideo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		
		imagePrograma = (ImageView)findViewById(R.id.imagePrograma);
		nombrePrograma = (TextView)findViewById(R.id.nombrePrograma);
		listaPrograma = (NuevoListView)findViewById(R.id.listaPrograma);
		

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		//Recibimos los parametros
		Bundle parametro = getIntent().getExtras(); 
			
		int posicion = parametro.getInt(Constantes.parametro);
		
		//mostramos la imagen del programa
		imagePrograma
			.setImageResource( Constantes.lista
					.get(posicion).getRutaImagen() );
		//Mostramos el nombre del programa
		nombrePrograma
			.setText( Constantes
				.lista.get(posicion).getNombreImagen() );
		
		//Inicializamos el arreglo para el listview(listaVideo)
		
		listaVideo = new ArrayList<VideosModel>();
		//Agregamos elementos al arreglo
		
		for (int i = 1; i < 11; i++) {

			listaVideo.add( new VideosModel(
					i, 
					"Video numero " + i, 
					"Descripcion del numero " + i, 
					Constantes.lista.get(posicion)
						.getRutaImagen()) );
				
		}
		
		//Inicializamos el adapter
		adapter = new VideosProgramaAdapter(
				this, R.layout.item_video, listaVideo);
		
		listaPrograma.setAdapter(adapter);
		
		
		
		
		
	}
	


}

package com.area51.clase04;

import java.util.ArrayList;

import com.aea51.custom.NuevoGridView;
import com.area51.adapters.ImagenAdapter;
import com.area51.model.Imagen;
import com.area51.util.Constantes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	ArrayList<Imagen>lista;
	ImagenAdapter adapter;
	NuevoGridView matriz;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Inicializamos la matriz
		matriz = (NuevoGridView)findViewById(R.id.matriz);
		//Inicializamos el ArrayList (lista)
		lista = new ArrayList<Imagen>();

		lista.add(new Imagen(0, "sample_0",R.drawable.sample_0));
		lista.add(new Imagen(1, "sample_1",R.drawable.sample_1));
		lista.add(new Imagen(2, "sample_2",R.drawable.sample_2));
		lista.add(new Imagen(3, "sample_3",R.drawable.sample_3));
		lista.add(new Imagen(4, "sample_4",R.drawable.sample_4));
		lista.add(new Imagen(5, "sample_5",R.drawable.sample_5));
		lista.add(new Imagen(6, "sample_6",R.drawable.sample_6));
		lista.add(new Imagen(7, "sample_7",R.drawable.sample_7));
		lista.add(new Imagen(8, "sample_8",R.drawable.sample_8));
		lista.add(new Imagen(9, "sample_9",R.drawable.sample_9));		
		//Inicializamos el adapter con sus respectivos parametros
		adapter = new 
				ImagenAdapter(
						this, 
						R.layout.item_gridview  , 
						lista);
		//Enviamos el adapter hacia el gridview(matriz)
		matriz.setAdapter(adapter);
		
		//Guardamos la lista en una variable
		Constantes.lista = new ArrayList<Imagen>();
		Constantes.lista = lista;
		
		
		//Instanciamos el evento clic de cada 
		//elemento del gridview(matriz)
		matriz.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				//Utilizamos el componente
				//intent para cambiar de actividad
				//y al mismo tiempo
				//mandarle parametros
				Intent intent = 
						new Intent( 
								MainActivity.this , 
								DetalleActivity.class );
				
				Bundle bundle = new Bundle();
				bundle.putInt( Constantes.parametro , position);
				
				//Asignamos al intent los parametros a enviar
				intent.putExtras(bundle);
				//Cambiamos de activity
				startActivity(intent);
				
				
			}
		} );
		
	}



}

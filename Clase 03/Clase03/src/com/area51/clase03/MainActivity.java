package com.area51.clase03;

import java.util.ArrayList;

import com.area51.adapter.ItemAdapter;
import com.area51.model.Item;
import com.area51.util.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends Activity {

	ItemAdapter adapter;
	ListView lista;
	Button btningresa;
	EditText txtnombre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		//Inicializamos la vista
		lista = (ListView)findViewById( R.id.lista );
		btningresa = (Button)findViewById(R.id.btningresa);
		txtnombre = (EditText)findViewById(R.id.txtnombre);
		
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		//Inicializamos el ArrayList
		if( Constants.lista == null ){
			Constants.lista = new ArrayList<Item>();	
		}
		
		
		//Llenamos de elementos el ArrayList
		
		/*
		for (int i = 0; i < 5; i++) {

			Constants.lista.add( 
					new Item( Constants.lista.size(), 
							"Alumno " + i, 
							"drawable/avatar") );
		}//fin del for
		*/
		
		//Inicializamos el adapter enviandole los parametros
		//al constructor
		adapter = new ItemAdapter( this, getApplicationContext() );
		
		//Enviamos el adapter hacia el listview
		//para que los muestre
		lista.setAdapter( adapter );
		
		
		btningresa.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				
				if( !txtnombre.getText().toString().equals("") ){
					
					Constants.lista.add( 
							new Item( Constants.lista.size() , 
									txtnombre.getText().toString(), 
									"drawable/avatar"));
					adapter.notifyDataSetChanged();
					txtnombre.setText("");
				}else{
					Toast.makeText( 
							getApplicationContext() ,
							getResources().getString(R.string.textoNombreError) ,
							Toast.LENGTH_SHORT)
							.show();
				}
				
			}
		});
		
		lista.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				
			}
			
		});
		
	}
	

}

package com.area51.clase02;

import java.util.ArrayList;

import com.area51.adapters.ItemAdapter;
import com.area51.models.Item;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	ArrayList<Item>Arreglo;
	ItemAdapter adapter;
	ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lista = (ListView)findViewById( R.id.lista );		

	}
	
	@Override
	protected void onResume() {

		super.onResume();
		
		Arreglo = new ArrayList<Item>();
		
		for (int i = 0; i < 4; i++) {
			
			Arreglo.add( new Item(i, "Avatar 0" + i, R.drawable.avatar ) );
			
		}
		
		adapter = new ItemAdapter(getApplicationContext(), Arreglo);
		
		lista.setAdapter(adapter);
		
	}



}

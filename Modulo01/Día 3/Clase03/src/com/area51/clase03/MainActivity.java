package com.area51.clase03;

import java.util.ArrayList;

import com.area51.adapter.ItemAdapter;
import com.area51.model.Item;
import com.area51.util.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

	Button btnIngresa;
	EditText txtNombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Inicializamos la lista
		lista = (ListView) findViewById(R.id.lista);

		btnIngresa = (Button) findViewById(R.id.btnIngresa);
		txtNombre = (EditText) findViewById(R.id.txtNombre);
	}

	@Override
	protected void onResume() {
		super.onResume();
		/*
		 * // Inicializamos el arraylist Constants.lista = new
		 * ArrayList<Item>(); // Llenamos de elementos el arraylist for (int i =
		 * 0; i < 5; i++) { Constants.lista.add(new Item(Constants.lista.size(),
		 * "Alumno " + i, "drawable/avatar")); }
		 * 
		 * // Inicializamos el adapter enviandoles los parametros al constructor
		 * adapter = new ItemAdapter(this, getApplicationContext());
		 * lista.setAdapter(adapter);
		 */
		Constants.lista = new ArrayList<Item>();
		adapter = new ItemAdapter(this, getApplicationContext());
		lista.setAdapter(adapter);

		if (Constants.lista == null)
			Constants.lista = new ArrayList<Item>();

		btnIngresa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!txtNombre.getText().toString().equals("")) {
					Constants.lista.add(new Item(Constants.lista.size(),
							txtNombre.getText().toString(), "drawable/avatar"));
					adapter.notifyDataSetChanged();
					txtNombre.setText("");
				} else {
					Toast.makeText(
							getApplicationContext(),
							getResources().getString(R.string.textoNombreError),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		/*
		 * TareaHacer una lista la cual se pueda editar
		 */

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});

	}
}

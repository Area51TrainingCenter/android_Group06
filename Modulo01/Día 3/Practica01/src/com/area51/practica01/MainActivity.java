package com.area51.practica01;

import java.util.ArrayList;

import com.area51.adapter.ItemAdapter;
import com.area51.model.Item;
import com.area51.util.Constants;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

	Button btnIngresa;
	EditText txtNombre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lista = (ListView) findViewById(R.id.lista);
		btnIngresa = (Button) findViewById(R.id.btnIngresa);
		txtNombre = (EditText) findViewById(R.id.txtNombre);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Constants.lista == null)
			Constants.lista = new ArrayList<Item>();
		
		adapter = new ItemAdapter(this, getApplicationContext());
		lista.setAdapter(adapter);

		// Inicializamos el boton para que agregue un nuevo elemento
		btnIngresa.setTag(-1);// cero es para ingresar un nuevo elemento

		btnIngresa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int evalua = Integer.parseInt(btnIngresa.getTag().toString());
				if (evalua == -1) {
					if (!txtNombre.getText().toString().equals("")) {
						Constants.lista.add(new Item(Constants.lista.size(),
								txtNombre.getText().toString(),
								"drawable/avatar"));
						adapter.notifyDataSetChanged();
						txtNombre.setText("");
					} else {
						Toast.makeText(
								getApplicationContext(),
								getResources().getString(
										R.string.textoNombreError),
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Constants.lista.get(evalua).setNombreItem(
							txtNombre.getText().toString());
					adapter.notifyDataSetChanged();
					txtNombre.setText("");
					btnIngresa.setTag(-1);
				}
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				AlertDialog.Builder dialogo = new AlertDialog.Builder(
						MainActivity.this);

				dialogo.setTitle("Opción").setItems(R.array.lista_array,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int item) {
								switch (item) {
								case 0:
									txtNombre.setText(Constants.lista.get(
											position).getNombreItem());
									btnIngresa.setTag(Constants.lista.get(
											position).getItem());
									break;
								case 1:
									Constants.lista.remove(position);
									adapter.notifyDataSetChanged();
									break;
								}
							}
						});
				dialogo.show();
			}
		});
	}
	/*
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Constants.lista=null;
	}
	*/
}

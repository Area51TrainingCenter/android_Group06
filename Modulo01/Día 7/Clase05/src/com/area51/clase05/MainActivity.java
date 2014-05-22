package com.area51.clase05;

import java.util.ArrayList;

import com.area51.adapters.ItemAdapter;
import com.area51.custom.NuevoGridView;
import com.area51.models.ItemGridViewModel;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	ItemAdapter adapter;
	ArrayList<ItemGridViewModel> lista;
	NuevoGridView gridViewCustom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gridViewCustom = (NuevoGridView) findViewById(R.id.nuevoGridView1);
	}

	@Override
	protected void onResume() {
		super.onResume();

		lista = new ArrayList<ItemGridViewModel>();
		lista.add(new ItemGridViewModel(1, "Imagen 01", "http://www.segundoacosta.com/static/sample_0.jpg"));
		lista.add(new ItemGridViewModel(2, "Imagen 02", "http://www.segundoacosta.com/static/sample_1.jpg"));
		lista.add(new ItemGridViewModel(3, "Imagen 03", "http://www.segundoacosta.com/static/sample_2.jpg"));
		lista.add(new ItemGridViewModel(4, "Imagen 04", "http://www.segundoacosta.com/static/sample_3.jpg"));
		lista.add(new ItemGridViewModel(5, "Imagen 05", "http://www.segundoacosta.com/static/sample_4.jpg"));
		lista.add(new ItemGridViewModel(6, "Imagen 06", "http://www.segundoacosta.com/static/sample_5.jpg"));
		lista.add(new ItemGridViewModel(7, "Imagen 07", "http://www.segundoacosta.com/static/sample_6.jpg"));
		lista.add(new ItemGridViewModel(8, "Imagen 08", "http://www.segundoacosta.com/static/sample_7.jpg"));
		lista.add(new ItemGridViewModel(9, "Imagen 09", "http://www.segundoacosta.com/static/sample_8.jpg"));
		lista.add(new ItemGridViewModel(10, "Imagen 10", "http://www.segundoacosta.com/static/sample_9.jpg"));

		adapter = new ItemAdapter(this, this, R.layout.item_gridview, lista);
		gridViewCustom.setAdapter(adapter);

	}
}

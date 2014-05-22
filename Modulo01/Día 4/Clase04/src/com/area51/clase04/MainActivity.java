package com.area51.clase04;

import java.util.ArrayList;

import com.area51.adapter.ItemAdapter;
import com.area51.model.ItemModel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {

	ItemAdapter adapter;
	GridView grilla;
	ArrayList<ItemModel> lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		grilla = (GridView) findViewById(R.id.grilla);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (lista == null)
			lista = new ArrayList<ItemModel>();

		lista.add(new ItemModel(1, "sample_0", R.drawable.sample_0));
		lista.add(new ItemModel(2, "sample_1", R.drawable.sample_1));
		lista.add(new ItemModel(3, "sample_2", R.drawable.sample_2));
		lista.add(new ItemModel(4, "sample_3", R.drawable.sample_3));
		lista.add(new ItemModel(5, "sample_4", R.drawable.sample_4));
		lista.add(new ItemModel(6, "sample_5", R.drawable.sample_5));
		lista.add(new ItemModel(7, "sample_6", R.drawable.sample_6));
		lista.add(new ItemModel(8, "sample_7", R.drawable.sample_7));
		lista.add(new ItemModel(9, "sample_8", R.drawable.sample_8));
		lista.add(new ItemModel(10, "sample_9", R.drawable.sample_9));
		lista.add(new ItemModel(11, "sample_10", R.drawable.sample_10));

		adapter = new ItemAdapter(this, R.layout.item_gridview, lista);
		grilla.setAdapter(adapter);
	}
}

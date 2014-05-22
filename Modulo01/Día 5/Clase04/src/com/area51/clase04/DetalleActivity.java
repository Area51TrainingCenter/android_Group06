package com.area51.clase04;

import com.area51.adapter.FragmentAdapter;
import com.area51.util.Constantes;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class DetalleActivity extends FragmentActivity {

	ViewPager viewPager;
	FragmentAdapter pageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);

		viewPager = (ViewPager) findViewById(R.id.viewpager);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Recibimos el parametro enviado de MainActivity
		Bundle bundle = getIntent().getExtras();
		int parametro = bundle.getInt(Constantes.parametro);
		
		pageAdapter=new FragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pageAdapter);
		viewPager.setCurrentItem(parametro);
	}
}

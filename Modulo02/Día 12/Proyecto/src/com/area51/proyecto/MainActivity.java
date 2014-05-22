package com.area51.proyecto;


import com.area51.adapters.FragmentAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	FragmentAdapter fpa;
	
	ViewPager viewpager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewpager = (ViewPager)findViewById(R.id.viewpager);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		fpa = new FragmentAdapter(getSupportFragmentManager());
		viewpager.setAdapter(fpa);
		
	}


}

package com.area51.proyecto;

import com.area51.adapters.FragmentAdapter;
import com.area51.utils.ConstantsApp;
import com.area51.utils.NetworkApp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	FragmentAdapter fpa;
	ViewPager viewpager;
	NetworkApp network;
	String TAG = "MainActivity";
	ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(ConstantsApp.TAG_APP, TAG + " onCreate");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(ConstantsApp.TAG_APP, TAG + " onResume");

		network = new NetworkApp(this);
		if (network.getNetwork()) {
			setContentView(R.layout.activity_main);
			iniciarApp();
		} else {
			setContentView(R.layout.networkdisconnected);
		}
	}

	private void iniciarApp() {
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		fpa = new FragmentAdapter(getSupportFragmentManager());
		viewpager.setAdapter(fpa);
		
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mActionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		// Creamos los tabs para el actionBar
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mActionBar.addTab(mActionBar.newTab().setText("HashTag")
				.setTabListener(this));
		mActionBar.addTab(mActionBar.newTab().setText("Timeline")
				.setTabListener(this));
		mActionBar.addTab(mActionBar.newTab().setText("Search")
				.setTabListener(this));
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}

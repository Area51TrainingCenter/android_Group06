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
	ActionBar mActionBar;

	String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(ConstantsApp.TAG_APP, TAG + " onCreate");
	}

	@Override
	protected void onResume() {
		Log.d(ConstantsApp.TAG_APP, TAG + "  onResume");
		super.onResume();

		network = new NetworkApp(this);

		if (network.getNetwork()) {
			Log.d(ConstantsApp.TAG_APP, TAG + " getNetwork true");
			setContentView(R.layout.activity_main);
			iniciaApp();
		} else {
			Log.d(ConstantsApp.TAG_APP, TAG + " getNetwork false");
			setContentView(R.layout.networkdisconnected);
		}
	}

	public void iniciaApp() {

		Log.d(ConstantsApp.TAG_APP, TAG + "iniciaApp");
		// INICIALIZAMOS LAS VARIABLES
		viewpager = (ViewPager) findViewById(R.id.viewpager);

		// REFERENCIAMOS LAS VARIABLES
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

		// Creamos el ActionBar de navegación
		if (mActionBar == null) {
			mActionBar = getActionBar();
			mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			// AGREGAMOS LOS TABS PARA EL ACTIONBAR
			mActionBar.addTab(mActionBar.newTab().setText("Hashtag")
					.setTabListener(this));

			mActionBar.addTab(mActionBar.newTab().setText("TimeLine")
					.setTabListener(this));

			mActionBar.addTab(mActionBar.newTab().setText("Search")
					.setTabListener(this));
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewpager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

}

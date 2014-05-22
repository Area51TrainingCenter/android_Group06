package com.area51.adapter;

import com.area51.fragments.ImagenFragment;
import com.area51.util.Constantes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int posicion) {
		ImagenFragment fragment = new ImagenFragment();
		Bundle parametro = new Bundle();
		parametro.putInt(fragment.FRAGMENT_POSICION, posicion);
		fragment.setArguments(parametro);
		return fragment;
	}

	@Override
	public int getCount() {
		return Constantes.lista.size();
	}

}

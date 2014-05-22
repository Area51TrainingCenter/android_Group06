package com.area51.adapters;

import com.area51.fragments.HashtagFragment;
import com.area51.fragments.SearchFragment;
import com.area51.fragments.TimeLineFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);		
	}

	@Override
	public Fragment getItem(int position) {
		
		switch (position) {
			case 0:
				HashtagFragment fragmenth = new HashtagFragment();
				return fragmenth;
			case 1:
				TimeLineFragment fragmentt = new TimeLineFragment();
				return fragmentt;
			case 2:
				SearchFragment fragments = new SearchFragment();
				return fragments;
		}		
		
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

}

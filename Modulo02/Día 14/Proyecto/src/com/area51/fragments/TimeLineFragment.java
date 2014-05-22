package com.area51.fragments;

import com.area51.proyecto.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimeLineFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View vistafragment = null;
		
		if( vistafragment == null ){
			vistafragment = inflater.from( getActivity() )
					.inflate(R.layout.timeline_fragment, container, false);
		}
		
		
		return vistafragment;
	}
	
}

package com.area51.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class LauncherActivity extends Activity {

	ImageView imgsplash;
	Integer counter = 0;
	Integer delay   = 1000;
	String TAG      = "Launcher";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		imgsplash = (ImageView)findViewById(R.id.imgsplash);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		counter = 0;
		imgsplash.postDelayed(iniciaApp, delay);		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		imgsplash.removeCallbacks(iniciaApp);
	}
	
	public Runnable iniciaApp = new Runnable() {
		
		@Override
		public void run() {
			
			if( counter < 4 ){
				imgsplash.postDelayed(iniciaApp, delay );
				counter++;
			}else{
				lanzaApp();
			}
			
		}
	};

	public void lanzaApp(){
		
		Intent intent = 
				new Intent( LauncherActivity.this, MainActivity.class );
		startActivity(intent);
		
	}
	


}

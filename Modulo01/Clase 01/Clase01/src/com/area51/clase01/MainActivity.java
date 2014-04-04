package com.area51.clase01;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class MainActivity extends Activity {	
	
	String TAG = "ACTIVIDAD";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        Log.d( TAG ,"onCreate");
        
    }

    
   @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

        Log.d( TAG ,"onStart");
		
	}
   
   @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        Log.d( TAG ,"onResume");
	}
   
   @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
        Log.d( TAG ,"onPause");
	}
   
   @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
        Log.d( TAG ,"onStop");
	}
   
   @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
        Log.d( TAG ,"onDestroy");
	}
   
   
   
   
	    
    
    
    
    

    
}

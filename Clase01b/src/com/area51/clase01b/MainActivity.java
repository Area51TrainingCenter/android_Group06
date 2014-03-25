package com.area51.clase01b;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	EditText txtnombre;
	Button boton;
	TextView txtmostrar;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        
        txtnombre = (EditText)findViewById( R.id.txtnombre );
        boton = (Button)findViewById( R.id.boton );
        txtmostrar = (TextView)findViewById( R.id.txtmostrar );
        
    }

    
}

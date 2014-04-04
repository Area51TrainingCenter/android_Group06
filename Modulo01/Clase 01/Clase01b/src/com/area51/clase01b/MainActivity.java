package com.area51.clase01b;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	EditText txtnombre;
	Button boton;
	TextView txtmostrar;
	static String texto = "";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );        
        
        //Inicializamos variables
        txtnombre = (EditText)findViewById( R.id.txtnombre );
        boton = (Button)findViewById( R.id.boton );
        txtmostrar = (TextView)findViewById( R.id.txtmostrar );
        
        if( savedInstanceState != null ){        	
        	txtmostrar.setText( texto );
        }
        
    }
    
    @Override
    protected void onResume() {
    	
    	super.onResume();
    	    	
    	boton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				evaluaTexto( txtnombre.getText().toString() );
			}
		} );
    	
    	
    	
    	
    	
    }//fin onResume
    
    public void evaluaTexto( String textoEditText ){

		if( !textoEditText.equals("") ){
			
			//Si existe un nombre escrito
			texto = texto + textoEditText + "\n";
			
			txtmostrar.setText( texto );
			
			//Limpiamos el EditText (txtnombre)
			txtnombre.setText("");
			
		}else{
			//No escribio nada
			Toast.makeText( getApplicationContext() , 
					getResources().getString( R.string.textoerror ) , 
					Toast.LENGTH_SHORT)
					.show();
		}
    }
    
    
    
    
    

    
}

package com.area51.asynctask;


import com.area51.clase09.MainActivity;
import com.area51.utils.RESTClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ManageAsyncTask {

	Context contexto;	
	
	public ManageAsyncTask(Context contexto) {
		super();
		this.contexto = contexto;
	}


	public void validaUsuario( String url ){
		
		ValidaUsuario obj = new ValidaUsuario();
		obj.execute(url);
		
	}
	
	
	public class ValidaUsuario extends AsyncTask<String, Void, String>{

		ProgressDialog mensaje;
		
		@Override
		protected void onPreExecute() {			
			super.onPreExecute();
			
			mensaje = new ProgressDialog(contexto);
			mensaje.setMessage( "Validando usuario!!!" );
			mensaje.show();
			
		}
		
		@Override
		protected String doInBackground(String... params) {
			
			String response = "";
			for (String url : params) {
				try {
					response = RESTClient.connectAndReturnResponse(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result){
			
			super.onPostExecute(result);
			
			mensaje.dismiss();
			
			( ( MainActivity ) contexto  ).validaUsuario(result);
			
			
		}
		
		
		
		
	}
	
	
	
	
}

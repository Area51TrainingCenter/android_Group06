package com.area51.async;

import com.area51.lecturajson.MainActivity;
import com.area51.libraries.RESTClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class RequestAsyncTask {

	String TAG = "RequestAsyncTask";
	Context context;

	public RequestAsyncTask(Context context) {
		super();
		this.context = context;
	}

	public void listadoUsuario(String url) {
		Log.d(TAG, TAG + "url:" + url);
		new ListadoUsuario().execute(url);
	}

	class ListadoUsuario extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d(TAG, TAG + " onPreExecute");
		}

		@Override
		protected String doInBackground(String... urlRecibido) {
			Log.d(TAG, TAG + " doInBackground");
			String jsonRecibido = "";
			for (String url : urlRecibido) {
				try {
					jsonRecibido = RESTClient.connectAndReturnResponse(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return jsonRecibido;
		}

		@Override
		protected void onPostExecute(String jsonObtenido) {
			Log.d(TAG, TAG + " onPostExecute");
			((MainActivity) context).listarUsuario(jsonObtenido);
		}

	}
}

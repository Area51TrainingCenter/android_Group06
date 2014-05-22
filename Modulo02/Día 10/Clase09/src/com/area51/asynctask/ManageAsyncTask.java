package com.area51.asynctask;

import com.area51.utils.RESTClient;

import android.content.Context;
import android.os.AsyncTask;

public class ManageAsyncTask {

	Context contexto;

	public ManageAsyncTask(Context contexto) {
		super();
		this.contexto = contexto;
	}

	public void validaUsuario(String url) {

	}

	public class ValidaUsuario extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}
	}

}

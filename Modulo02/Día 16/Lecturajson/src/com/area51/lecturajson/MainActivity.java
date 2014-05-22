package com.area51.lecturajson;

import org.json.JSONArray;
import org.json.JSONObject;

import com.area51.async.RequestAsyncTask;
import com.area51.utils.Contants;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String TAG = "MainActivity";
	LinearLayout container;
	LinearLayout linea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		container = (LinearLayout) findViewById(R.id.container);

		Log.d(Contants.TAG_APP, TAG + " onCreate");

		ScrollView scroll = new ScrollView(this);
		scroll.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		container.addView(scroll);

		linea = new LinearLayout(this);
		linea.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		linea.setOrientation(LinearLayout.VERTICAL);
		scroll.addView(linea);

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(Contants.TAG_APP, TAG + " onResume");

		String url = Contants.API + Contants.API_VERSION + Contants.API_SECTION;
		new RequestAsyncTask(this).listadoUsuario(url);
	}

	public void listarUsuario(String json) {
		Log.d(Contants.TAG_APP, TAG + " listarUsuario:" + json);
		try {
			JSONObject jsonApi = new JSONObject(json);
			if (jsonApi.getString(Contants.API_RESPONSE).equals(
					Contants.API_RESPONSE_VAL)) {
				JSONArray jsonData = jsonApi.getJSONArray(Contants.API_DATA);
				Log.d(Contants.TAG_APP, TAG + " jsonData: " + jsonData);

				int total = jsonData.length();
				for (int i = 0; i < total; i++) {
					JSONObject dato = jsonData.getJSONObject(i);

					String usuario = "";
					usuario = dato.getString("idusuario") + " "
							+ dato.getString("nombre") + " "
							+ dato.getString("registro");

					TextView texto = new TextView(this);
					texto.setLayoutParams(new LayoutParams(
							LayoutParams.MATCH_PARENT,
							LayoutParams.WRAP_CONTENT));
					texto.setPadding(10, 0, 10, 10);
					texto.setText(usuario);
					linea.addView(texto);
				}

			} else {
				Toast.makeText(this, "Sin registros", Toast.LENGTH_SHORT)
						.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

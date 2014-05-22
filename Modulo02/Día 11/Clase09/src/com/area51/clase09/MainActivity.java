package com.area51.clase09;

import org.json.JSONException;
import org.json.JSONObject;

import com.area51.asynctask.ManageAsyncTask;
import com.area51.sqlite.ManageOpenHelper;
import com.area51.utils.Constants;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText txtusuario;
	EditText txtclave;
	Button btningresa;
	Button btncerrarsesion;

	ManageAsyncTask request;

	ManageOpenHelper dbConexion;
	SQLiteDatabase dbProcesos;
	ContentValues registroDb;
	Cursor registro;

	LinearLayout capaLogueo;
	TextView txtLogueo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtusuario = (EditText) findViewById(R.id.txtusuario);
		txtclave = (EditText) findViewById(R.id.txtclave);
		btningresa = (Button) findViewById(R.id.btningresa);

		capaLogueo = (LinearLayout) findViewById(R.id.capaLogueo);
		txtLogueo = (TextView) findViewById(R.id.txtLogueo);

		btncerrarsesion = (Button) findViewById(R.id.btncerrarsesion);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Instanciando conexión a BD
		dbConexion = new ManageOpenHelper(getApplicationContext(),
				Constants.DBname, null, Constants.DBversion);

		dbProcesos = dbConexion.getWritableDatabase();

		request = new ManageAsyncTask(this);
		btningresa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!txtusuario.getText().toString().equals("")
						&& !txtclave.getText().toString().equals("")) {

					String url = Constants.APIurl + Constants.APIversion
							+ Constants.APIsectionValidaUsuario
							+ Constants.APIvarUsuario
							+ txtusuario.getText().toString()
							+ Constants.APIvarClave
							+ txtclave.getText().toString();

					request.validaUsuario(url);
					
				} else {
					Toast.makeText(getApplicationContext(),
							"Ingrese valores correctos", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});

		btncerrarsesion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String sql = "DELETE FROM " + Constants.TableName;
				dbProcesos.execSQL(sql);
				capaLogueo.setVisibility(View.VISIBLE);
				txtLogueo.setVisibility(View.GONE);
				btncerrarsesion.setVisibility(View.GONE);
			}
		});

		verificarUsuario();
	}

	public void validaUsuario(String resultado) {
		try {
			JSONObject jsonResultado = new JSONObject(resultado);
			Log.d("JSON", "" + jsonResultado);
			if (jsonResultado.getString(Constants.APIResponse).equals(
					Constants.APIResponseVal)) {
				JSONObject data = jsonResultado
						.getJSONObject(Constants.APIData);
				String sql = "INSERT INTO " + Constants.TableName + "("
						+ Constants.ColidUsuario + ","
						+ Constants.ColnameUsuario + ","
						+ Constants.ColcorreoUsuario + ") VALUES("
						+ data.getString(Constants.APIDataidusuaruio) + ","
						+ "'" + data.getString(Constants.APIDatanombre) + "'"
						+ "," + "'" + data.getString(Constants.APIDatacorreo)
						+ "'" + ")";

				Log.d("SQL", sql);
				dbProcesos.execSQL(sql);


				capaLogueo.setVisibility(View.GONE);
				txtLogueo.setVisibility(View.VISIBLE);
				txtLogueo.setText("El usuario logueado es "
						+ registro.getString(Constants.ColnameUsuarioIndex));
				btncerrarsesion.setVisibility(View.VISIBLE);

				/*
				 * Toast.makeText(getApplicationContext(), "Ingreso Correcto",
				 * Toast.LENGTH_SHORT).show();
				 */
			} else {
				Toast.makeText(getApplicationContext(),
						"Ingrese una información valida", Toast.LENGTH_SHORT)
						.show();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void verificarUsuario() {
		String sql = "SELECT * FROM " + Constants.TableName;
		Log.d("SQL", sql);
		registro = dbProcesos.rawQuery(sql, null);
		if (registro.moveToFirst()) {
			capaLogueo.setVisibility(View.GONE);
			txtLogueo.setVisibility(View.VISIBLE);
			txtLogueo.setText("El usuario logueado es "
					+ registro.getString(Constants.ColnameUsuarioIndex));
			btncerrarsesion.setVisibility(View.VISIBLE);
		}
	}

}

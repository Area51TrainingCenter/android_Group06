package com.area51.clase09;

import com.area51.asynctask.ManageAsyncTask;
import com.area51.utils.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText txtUsuario;
	EditText txtClave;
	Button btnIngresa;

	ManageAsyncTask request;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtUsuario = (EditText) findViewById(R.id.txtUsuario);
		txtClave = (EditText) findViewById(R.id.txtClave);
		btnIngresa = (Button) findViewById(R.id.btnIngresar);
	}

	@Override
	protected void onResume() {
		super.onResume();

		request = new ManageAsyncTask(getApplicationContext());

		btnIngresa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!txtUsuario.getText().toString().trim().equals("")
						&& !txtClave.getText().toString().trim().equals("")) {
					String url = Constants.APIurl + Constants.APIversion
							+ Constants.APIsectionValidaUsuario
							+ Constants.APIvarUsuario
							+ txtUsuario.getText().toString()
							+ Constants.APIvarClave
							+ txtClave.getText().toString();
					request.validaUsuario(url);
				} else {
					Toast.makeText(getApplicationContext(),
							"Ingrese Texto Correcto!!!", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

}

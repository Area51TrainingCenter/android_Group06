package com.area51.clase04b;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		for (int i = 0; i < 5; i++) {
			// Creamos el menu por codigo
			menu.getItem(0).getSubMenu().add(i, i, i, "Elemento " + i)
					.setIcon(R.drawable.vivoon)
					.setOnMenuItemClickListener(new OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {
							Toast.makeText(getApplicationContext(),
									"boton: " + item.getTitle(),
									Toast.LENGTH_SHORT).show();
							return false;
						}
					});
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * int id = item.getItemId();
		 * 
		 * switch (id) { case R.id.botonA:
		 * Toast.makeText(getApplicationContext(), "boton: " + item.getTitle(),
		 * Toast.LENGTH_SHORT).show(); break; case R.id.botonSubMenuA:
		 * Toast.makeText(getApplicationContext(), "boton: " + item.getTitle(),
		 * Toast.LENGTH_SHORT).show(); break; }
		 */
		return super.onOptionsItemSelected(item);
	}

}

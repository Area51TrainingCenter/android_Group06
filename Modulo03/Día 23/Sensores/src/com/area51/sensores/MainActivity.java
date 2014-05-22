package com.area51.sensores;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends Activity implements SensorEventListener {

	FrameLayout vista;
	ImageView bolita;

	// VistaSensor dibuja los vectores del acelerometro
	VistaSensor vistaSensor;

	// Variables para manejo del sensor
	private SensorManager mSensorManager;
	Sensor accelerometer;
	Sensor magnetometer;

	// Variables de dibujo
	Float azimut;
	float[] mGravity;
	float[] mGeomagnetic;

	int centerx;
	int centery;
	int x; // Posición de la bolita en el eje X
	int y; // Posición de la bolita en el eje Y

	int widthScreen; // Limite de la bolita en el eje X
	int heightScreen; // Limite de la bolita en el eje Y

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Obtenermos las medidas del dispositivo
		getResolution();

		vista = (FrameLayout) findViewById(R.id.vista);
		bolita = (ImageView) findViewById(R.id.bolita);

		vistaSensor = new VistaSensor(this);

		vista.addView(vistaSensor);

		// Obtenemos el tipo de sensor
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magnetometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

		// Implementar iconos en el actionBar
		ActionBar abar = getActionBar();
		abar.setIcon(R.drawable.ic_launcher);
		abar.setTitle("Titulos");
	}

	@Override
	protected void onResume() {

		super.onResume();

		// Registramos las llamadas al sensor
		mSensorManager.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_UI);
		mSensorManager.registerListener(this, magnetometer,
				SensorManager.SENSOR_DELAY_UI);

	}

	@Override
	protected void onPause() {

		super.onPause();
		// Eliminamos el registro del sensor
		mSensorManager.unregisterListener(this);
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		// Eliminamos el registro del sensor
		mSensorManager.unregisterListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		// Detectamos cambios en el sensor

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			mGravity = event.values;
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
			mGeomagnetic = event.values;
		if (mGravity != null && mGeomagnetic != null) {
			float R[] = new float[9];
			float I[] = new float[9];
			boolean success = SensorManager.getRotationMatrix(R, I, mGravity,
					mGeomagnetic);
			if (success) {

				float orientation[] = new float[3];
				SensorManager.getOrientation(R, orientation);
				azimut = orientation[0];

				movimientoBolita(event.values[0], event.values[1]);
			}
		}
		vistaSensor.invalidate();

	}

	public void movimientoBolita(float Xaux, float Yaux) {
		// Validamos los datos recibidos
		if (Xaux < 0) {
			if (x <= widthScreen)
				x = x + (int) Math.pow(Xaux, 2);
		} else {
			x = x - (int) Math.pow(Xaux, 2);
		}
		if (Yaux > 0) {
			if (y >= 0)
				y = y - (int) Math.pow(Yaux, 2);
		} else {
			if (y <= heightScreen)
				y = y + (int) Math.pow(Yaux, 2);
		}

		// Validamos que no sea negativo
		if (x < 0)
			x = -x;
		if (y < 0)
			y = -y;

		// Validamos el movimiento en el eje X e Y
		if (x > 0 && x < widthScreen)
			bolita.setTranslationX(x);
		if (y > 0 && y < heightScreen)
			bolita.setTranslationY(y);
	}

	public void getResolution() {
		DisplayMetrics pantalla = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(pantalla);

		widthScreen = (int) (pantalla.widthPixels / 1.2);
		heightScreen = (int) (pantalla.heightPixels / 1.23);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public class VistaSensor extends View {

		Paint paint = new Paint();

		public VistaSensor(Context context) {
			super(context);
			paint.setColor(0xff00ff00);
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(2);
			paint.setAntiAlias(true);
		}

		protected void onDraw(Canvas canvas) {
			int width = getWidth();
			int height = getHeight();
			centerx = width / 2;
			centery = height / 2;
			canvas.drawLine(centerx, 0, centerx, height, paint);
			canvas.drawLine(0, centery, width, centery, paint);
			// Rotate the canvas with the azimut
			if (azimut != null)
				canvas.rotate(-azimut * 360 / (2 * 3.14159f), centerx, centery);
			paint.setColor(0xff0000ff);
			canvas.drawLine(centerx, -1000, centerx, +1000, paint);
			canvas.drawLine(-1000, centery, 1000, centery, paint);
			canvas.drawText("N", centerx + 5, centery - 10, paint);
			canvas.drawText("S", centerx - 10, centery + 15, paint);
			paint.setColor(0xff00ff00);
		}

	}

}

// package com.area51.sensores;
//
// import android.app.Activity;
// import android.content.Context;
// import android.graphics.Canvas;
// import android.graphics.Paint;
// import android.graphics.Paint.Style;
// import android.hardware.Sensor;
// import android.hardware.SensorEvent;
// import android.hardware.SensorEventListener;
// import android.hardware.SensorManager;
// import android.os.Bundle;
// import android.view.Menu;
// import android.view.MenuItem;
// import android.view.View;
// import android.widget.FrameLayout;
// import android.widget.ImageView;
//
// public class MainActivity extends Activity implements SensorEventListener {
//
// // Vista que dibuja los sensores del acelerometro
// VistaSensor vistaSensor;
//
// FrameLayout vista;
// ImageView bolita;
//
// // Variables para manejo de sensor
// private SensorManager mSensorManager;
// Sensor accelerometro;
// Sensor magnetometer;
//
// // Variable de dibujo
// Float azimut;
// float[] mGravity;
// float[] mGeomagnetic;
//
// int centerx;
// int centery;
// int x;// Posición de la bolita en el eje X
// int y;// Posición de la bolita en el eje Y
//
// int widthScreen;// Límite de la bolita en el eje X
// int heightScreen;// Límite de la bolita en el eje Y
//
// @Override
// protected void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_main);
//
// vista = (FrameLayout) findViewById(R.id.vista);
// bolita = (ImageView) findViewById(R.id.bolita);
//
// vistaSensor = new VistaSensor(this);
// vista.addView(vistaSensor);
// }
//
// @Override
// protected void onResume() {
// super.onResume();
// // Registramos las llamadas al sensor
// mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
// accelerometro = mSensorManager
// .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
// magnetometer = mSensorManager
// .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
// }
//
// @Override
// protected void onDestroy() {
// super.onDestroy();
// mSensorManager.unregisterListener(this);
//
// }
//
// @Override
// protected void onPause() {
// super.onPause();
// mSensorManager.unregisterListener(this);
//
// }
//
// @Override
// public boolean onCreateOptionsMenu(Menu menu) {
// getMenuInflater().inflate(R.menu.main, menu);
// return true;
// }
//
// @Override
// public boolean onOptionsItemSelected(MenuItem item) {
// int id = item.getItemId();
// if (id == R.id.action_settings) {
// return true;
// }
// return super.onOptionsItemSelected(item);
// }
//
// @Override
// public void onSensorChanged(SensorEvent event) {
// // Detectamos cambios en el sensor
// if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
// mGravity = event.values;
//
// if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
// mGeomagnetic = event.values;
//
// if (mGravity != null && mGeomagnetic != null) {
// float R[] = new float[9];
// float I[] = new float[9];
// boolean success = SensorManager.getRotationMatrix(R, I, mGravity,
// mGeomagnetic);
// if (success) {
// // Mostramos los ejes X e Y con movimiento de acuerdo al sensor
// float orientation[] = new float[3];
// SensorManager.getOrientation(R, orientation);
// azimut = orientation[0];
// }
// }
// vistaSensor.invalidate();
// }
//
// @Override
// public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
// }
//
// public class VistaSensor extends View {
//
// Paint paint = new Paint();
//
// public VistaSensor(Context context) {
// super(context);
// paint.setColor(0xff00ff00);
// paint.setStyle(Style.STROKE);
// paint.setStrokeWidth(2);
// paint.setAntiAlias(true);
// }
//
// protected void onDraw(Canvas canvas) {
// int width = getWidth();
// int height = getHeight();
// centerx = width / 2;
// centery = width / 2;
// canvas.drawLine(centerx, 0, centerx, height, paint);
// canvas.drawLine(0, centery, width, centery, paint);
// // Rotate the canvas with the azimut
// if (azimut != null)
// canvas.rotate(-azimut * 360 / (2 * 314159f), centerx, centery);
// paint.setColor(0xff0000ff);
// canvas.drawLine(centerx, -1000, centerx, +1000, paint);
// canvas.drawLine(-1000, centery, 1000, centery, paint);
// canvas.drawText("N", centerx + 5, centery - 10, paint);
// canvas.drawText("S", centerx - 10, centery + 15, paint);
// paint.setColor(0xff00ff00);
// }
// }
//
// }

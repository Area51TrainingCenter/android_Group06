package com.area51.mapasgoogle;

import android.app.Activity;
import android.location.Location;
import android.media.CameraProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements LocationListener,
		ConnectionCallbacks, OnConnectionFailedListener {

	String TAG = "Mapas";

	GoogleMap mapa;
	LocationClient locationClient;

	LocationRequest REQUEST = LocationRequest.create().setInterval(2000)
			.setFastestInterval(16)
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");

		loadMap();
		loadClient();
		locationClient.connect();
	}

	private void loadClient() {
		Log.d(TAG, "loadClient");
		locationClient = new LocationClient(getApplicationContext(), this, this);
	}

	private void loadMap() {
		Log.d(TAG, "loadMap");
		if (mapa == null)
			mapa = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
	}

	public void MyLocation(Location location) {
		Log.d(TAG, "MyLocation");

		Log.d(TAG, "Latitud: " + location.getLatitude() + " Longitud: "
				+ location.getLongitude());
		MarkerOptions mOptions = new MarkerOptions();
		LatLng latIng = new LatLng(location.getLatitude(),
				location.getLongitude());

		// Nos dirigimos a la ubicación actual usando un efecto de zoon con el
		// objeto camera
		CameraPosition cposition = new CameraPosition.Builder().target(latIng)
				.zoom(13).bearing(45).tilt(70).build();

		mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cposition));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(TAG, "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d(TAG, "onOptionsItemSelected");
		switch (item.getItemId()) {
		case R.id.btnNormal:
			mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.btnHibrido:
			mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.btnSatelite:
			mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.btnTerreno:
			mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d(TAG, "onConnectionFailed");

	}

	@Override
	public void onConnected(Bundle arg0) {
		Log.d(TAG, "onConnected");

		locationClient.requestLocationUpdates(REQUEST, this);
		MyLocation(locationClient.getLastLocation());
	}

	@Override
	public void onDisconnected() {
		Log.d(TAG, "onDisconnected");

	}

	@Override
	public void onLocationChanged(Location arg0) {
		Log.d(TAG, "onLocationChanged");

	}

}

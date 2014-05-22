package com.area51.mapasgoogle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends Activity implements LocationListener,
		ConnectionCallbacks, OnConnectionFailedListener {

	String TAG = "Mapas";

	GoogleMap mapa;
	LocationClient locationClient;

	Context context;

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

		context = this;

		loadMap();
		loadClient();
		locationClient.connect();

	}

	public void loadMap() {

		Log.d(TAG, "loadMap");
		if (mapa == null) {

			mapa = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			mapa.setBuildingsEnabled(true);

		}

	}

	public void loadClient() {

		Log.d(TAG, "loadClient");
		locationClient = new LocationClient(getApplicationContext(), this, this);

	}

	public void MyLocation(Location location) {

		Log.d(TAG, "MyLocation");

		Log.d(TAG, "Latitud: " + location.getLatitude() + " Longitud: "
				+ location.getLongitude());

		LatLng latlng = new LatLng(location.getLatitude(),
				location.getLongitude());

		// Para que salga un globito de donde nos ubicamos
		MarkerOptions marker = new MarkerOptions();
		marker.title(getResources().getString(R.string.markerTitulo));
		marker.snippet(getResources().getString(R.string.markerDescripcion));
		marker.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.ic_launcher));
		marker.position(latlng);
		mapa.addMarker(marker);

		// Nos dirigimos a la ubicación actual usando un efecto de zoom
		// con el objeto Camera
		CameraPosition cposition = new CameraPosition.Builder().target(latlng)
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

		// MyLocation(locationClient.getLastLocation());
		new Ruta().execute(Constants.API);

	}

	@Override
	public void onDisconnected() {
		Log.d(TAG, "onDisconnected");

	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(TAG, "onLocationChanged");

		location.getLatitude();
		location.getLongitude();

	}

	private void CargaRuta(String json) {
		Log.d(TAG, "CargarRuta: " + json);
		try {
			JSONObject datoJson = new JSONObject(json);
			if (datoJson.get("response").equals("OK")) {
				JSONArray data = datoJson.getJSONArray("data");
				int total = data.length();
				JSONObject dataItem;

				PolygonOptions linea = new PolygonOptions();
				LatLng latLngPartida = null;

				for (int i = 0; i < total; i++) {
					dataItem = (JSONObject) data.get(i);
					if (i == 0) {
						latLngPartida = new LatLng(dataItem.getDouble("LATITUDE"),
								dataItem.getDouble("LONGITUDE"));

						LatLng latlngInicio = new LatLng(
								dataItem.getDouble("LATITUDE"),
								dataItem.getDouble("LONGITUDE"));
						MarkerOptions markerInicio = new MarkerOptions();
						markerInicio.title("markerInicio");
						markerInicio.snippet("Aquí empiza la ruta");
						markerInicio.position(latlngInicio);
						markerInicio.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.ic_launcher));
						mapa.addMarker(markerInicio);
					} else if (i == (total - 1)) {
						LatLng latlngFin = new LatLng(
								dataItem.getDouble("LATITUDE"),
								dataItem.getDouble("LONGITUDE"));
						MarkerOptions markerFin = new MarkerOptions();
						markerFin.title("markerInicio");
						markerFin.snippet("Aquí empiza la ruta");
						markerFin.position(latlngFin);
						markerFin.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.ic_launcher));
						mapa.addMarker(markerFin);
					}
					// Agregamos las líneas
					linea.add(new LatLng(dataItem.getDouble("LATITUDE"), dataItem
							.getDouble("LONGITUDE")));
				}

				linea.strokeColor(Color.RED);
				mapa.addPolygon(linea);
				
				CameraPosition cposition = new CameraPosition.Builder()
						.target(latLngPartida).zoom(13).bearing(45).tilt(70)
						.build();

				mapa.animateCamera(CameraUpdateFactory
						.newCameraPosition(cposition));

			} else {
				Log.d(TAG, "No hay datos que mostrar ");
			}
		} catch (JSONException e) {
		}
	}

	private class Ruta extends AsyncTask<String, Void, String> {

		ProgressDialog mensaje;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mensaje = new ProgressDialog(context);
			mensaje.setMessage("Conectando al API");
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
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			mensaje.dismiss();
			CargaRuta(result);
		}
	}

}

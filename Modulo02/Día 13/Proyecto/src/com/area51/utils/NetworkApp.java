package com.area51.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkApp {

	Context context;
	NetworkInfo network;
	Boolean wifiConnection = false;
	Boolean mobileConnection = false;
	String TAG = "NetworkApp";

	public NetworkApp(Context context) {
		super();
		this.context = context;
	}

	public Boolean getNetwork() {
		Log.d(ConstantsApp.TAG_APP, TAG + "getNetwork");
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		network = con.getActiveNetworkInfo();
		if (network != null && network.isConnected()) {
			Log.d(ConstantsApp.TAG_APP, TAG + "isConnected");
			wifiConnection = network.getType() == ConnectivityManager.TYPE_WIFI;
			mobileConnection = network.getType() == ConnectivityManager.TYPE_MOBILE;
			if (wifiConnection) {
				Log.d(ConstantsApp.TAG_APP, TAG + "wifiConnected");
				return true;
			} else if (mobileConnection) {
				Log.d(ConstantsApp.TAG_APP, TAG + "mobileConnected");
				return true;
			}
		} else {
			Log.d(ConstantsApp.TAG_APP, TAG + "disconnected");
		}
		return false;
	}

}

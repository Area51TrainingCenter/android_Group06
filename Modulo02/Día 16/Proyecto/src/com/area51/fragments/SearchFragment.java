package com.area51.fragments;

import java.util.ArrayList;

import com.area51.adapters.TweetAdapter;
import com.area51.async.ManageTweet;
import com.area51.fragments.HashtagFragment.HashTagAsync;
import com.area51.models.Tweet;
import com.area51.proyecto.R;
import com.area51.utils.ConstantsApp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchFragment extends Fragment {

	EditText txtBuscar;
	Button btnBuscar;
	ListView listViewSearch;
	String TAG = "SearchFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View vistafragment = null;

		if (vistafragment == null) {
			vistafragment = inflater.from(getActivity()).inflate(
					R.layout.search_fragment, container, false);
		}

		txtBuscar = (EditText) vistafragment.findViewById(R.id.txtBuscar);
		btnBuscar = (Button) vistafragment.findViewById(R.id.btnBuscar);

		btnBuscar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, txtBuscar.getText().toString());
				new SearchAsync().execute();
			}
		});

		listViewSearch = (ListView) vistafragment
				.findViewById(R.id.listViewSearch);

		return vistafragment;
	}

	public void loadTweet(ArrayList<Tweet> arrayTweet) {
		Log.d(ConstantsApp.TAG_APP, TAG + " loadTweet start");
		listViewSearch.setAdapter(new TweetAdapter(getActivity(),
				R.layout.row_tweet, arrayTweet));
		Log.d(ConstantsApp.TAG_APP, TAG + " loadTweet finish");
	}

	public class SearchAsync extends AsyncTask<Object, Void, ArrayList<Tweet>> {

		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d(ConstantsApp.TAG_APP, TAG + " onPreExecute");
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage(getResources().getString(
					R.string.tweets_loading));
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected ArrayList<Tweet> doInBackground(Object... params) {
			Log.d(ConstantsApp.TAG_APP, TAG + " doInBackground");
			Log.d(TAG, txtBuscar.getText().toString());
			return ManageTweet.getHashtag(txtBuscar.getText().toString());
		}

		@Override
		protected void onPostExecute(ArrayList<Tweet> tweets) {
			Log.d(ConstantsApp.TAG_APP, TAG + " onPostExecute");
			super.onPostExecute(tweets);

			progressDialog.dismiss();
			if (tweets.isEmpty()) {
				Toast.makeText(getActivity(),
						getResources().getString(R.string.tweets_empty),
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(),
						getResources().getString(R.string.tweets_dowloading),
						Toast.LENGTH_SHORT).show();
				loadTweet(tweets);
			}
		}

	}

}

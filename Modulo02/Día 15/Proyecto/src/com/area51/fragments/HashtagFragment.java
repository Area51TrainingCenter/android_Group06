package com.area51.fragments;

import java.util.ArrayList;

import com.area51.adapters.TweetAdapter;
import com.area51.async.ManageTweet;
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
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class HashtagFragment extends Fragment {

	ListView listHashTag;
	String TAG = "HashtagFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(ConstantsApp.TAG_APP, TAG + " onCreate");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(ConstantsApp.TAG_APP, TAG + " onResume");
		new HashTagAsync().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(ConstantsApp.TAG_APP, TAG + " onCreateView");

		View vistafragment = null;

		if (vistafragment == null) {
			vistafragment = inflater.from(getActivity()).inflate(
					R.layout.hashtag_fragment, container, false);
		}

		listHashTag = (ListView) vistafragment
				.findViewById(R.id.listViewHashTag);

		return vistafragment;
	}

	public void loadTweet(ArrayList<Tweet> arrayTweet) {
		Log.d(ConstantsApp.TAG_APP, TAG + " loadTweet start");
		listHashTag.setAdapter(new TweetAdapter(getActivity(),
				R.layout.row_tweet, arrayTweet));
		Log.d(ConstantsApp.TAG_APP, TAG + " loadTweet finish");
	}

	public class HashTagAsync extends AsyncTask<Object, Void, ArrayList<Tweet>> {

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
			return ManageTweet.getHashtag(ConstantsApp.TWITTER_TERM);
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

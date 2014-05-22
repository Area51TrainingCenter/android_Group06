package com.area51.fragments;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.area51.adapters.TweetAdapter;
import com.area51.async.ManageTweet;
import com.area51.models.Tweet;
import com.area51.proyecto.R;
import com.area51.sqlite.DBOperations;
import com.area51.utils.ConstantsApp;
import com.area51.utils.NetworkApp;




public class HashtagFragment extends Fragment {

	ListView listHashtag;
	DBOperations dbOperations;
	String TAG = "HashtagFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d( ConstantsApp.TAG_APP , TAG + " onCreate " );
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d( ConstantsApp.TAG_APP , TAG + " onResume " );
		
		dbOperations = new DBOperations(getActivity());
		new HashtagAsync().execute();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		Log.d( ConstantsApp.TAG_APP , TAG + " onCreateView " );
		
		View vistafragment = null;
		
		if( vistafragment == null ){
			vistafragment = inflater.from( getActivity() )
					.inflate(R.layout.hashtag_fragment, container, false);
		}
		
		listHashtag = (ListView)vistafragment.findViewById(R.id.listHashtag);

		Log.d( ConstantsApp.TAG_APP , TAG + " onCreateView finish " );
		
		return vistafragment;
	}
	
	public void loadTweet( ArrayList<Tweet>arrayTweet ){

		Log.d( ConstantsApp.TAG_APP , TAG + " loadTweet start " );
		/*
		 * En un fragmento no existe el término 
		 * "this, getContext(), getApplicationContext()"
		 * para eso se usa "getActivity()"
		 */
		
		listHashtag.setAdapter( 
				new TweetAdapter( getActivity() , R.layout.row_tweet, arrayTweet)  
				);

		Log.d( ConstantsApp.TAG_APP , TAG + " loadTweet finish " );
	}
	
	public class HashtagAsync 
	extends AsyncTask<Object, Void, ArrayList<Tweet>>{


		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Log.d( ConstantsApp.TAG_APP , TAG + " onPreExecute " );
			
			progressDialog = new ProgressDialog( getActivity() );
			progressDialog.setMessage(getResources().getString( R.string.tweets_loading ));
			progressDialog.setCancelable(false);
			progressDialog.show();
			
		}
		
		@Override
		protected ArrayList<Tweet> doInBackground(Object... params) {
			// TODO Auto-generated method stub
			Log.d( ConstantsApp.TAG_APP , TAG + " doInBackground " );
			
			
			
			
			if( new NetworkApp(getActivity()).getNetwork() ){
				return ManageTweet.getHashtag(ConstantsApp.TWITTER_TERM);
				
			}else{
				return dbOperations.getStatusUpdates();
			}
						
		}

		@Override
		protected void onPostExecute(ArrayList<Tweet> tweets) {
			// TODO Auto-generated method stub
			super.onPostExecute(tweets);
			Log.d( ConstantsApp.TAG_APP , TAG + " onPostExecute " );
			
			progressDialog.dismiss();
			
			if (tweets.isEmpty()) {
				
				/*
				Toast.makeText( getActivity() , 
						getResources().getString(R.string.tweets_empty),
						Toast.LENGTH_SHORT).show();				
				*/
				
			} else {		
				
				/*
				Toast.makeText( getActivity() , 
						getResources().getString(R.string.tweets_downloading),
						Toast.LENGTH_SHORT).show();
				*/
				
				loadTweet(tweets);
			}			
			
		}
		
		
		
		
	}

	
	
	
	
	
	
}

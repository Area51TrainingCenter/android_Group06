package com.area51.videoplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	SurfaceView videoSurface;
	SurfaceHolder videoHolder;
	MediaPlayer videoPlayer;

	LinearLayout videoControles;
	ImageView btnPlay;
	ImageView btnPause;
	TextView videoTitulo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		videoSurface = (SurfaceView) findViewById(R.id.videoSurface);
		videoControles = (LinearLayout) findViewById(R.id.videoControles);
		btnPlay = (ImageView) findViewById(R.id.btnPlay);
		btnPause = (ImageView) findViewById(R.id.btnPause);
		videoTitulo = (TextView) findViewById(R.id.videoTitulo);
	}

}

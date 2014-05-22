package com.area51.videoplayer;

import java.io.IOException;

import com.area51.utils.Constants;
import com.area51.utils.SystemUiHider;

import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.TargetApi;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements 
Callback, OnPreparedListener, OnBufferingUpdateListener, 
OnCompletionListener, OnVideoSizeChangedListener, OnInfoListener, 
OnErrorListener, OnSeekBarChangeListener {
	
	SurfaceView videoSurface;
	SurfaceHolder videoHolder;
	MediaPlayer videoPlayer;
	
	LinearLayout videoControles;
	ImageView btnplay;
	ImageView btnpause;
	TextView videoTitulo;
	
	SeekBar videoSeekBar;
	TextView videoTiempo;
	TextView videoTiempoTotal;
	
	String TAG = "VideoPlayer";
	
	FrameLayout frameVideo;
	int tiempoHilo = 1000;

	
	
	
	//Variables de animaci�n
	boolean AUTO_HIDE = true;
	int AUTO_HIDE_DELAY_MILLIS = 5000;
	boolean TOGGLE_ON_CLICK = true;
	int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	SystemUiHider mSystemUiHider;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Inicializamos
		videoSurface = (SurfaceView)findViewById(R.id.videoSurface);
		videoControles = (LinearLayout)findViewById(R.id.videoControles);
		btnplay = (ImageView)findViewById(R.id.btnplay);
		btnpause = (ImageView)findViewById(R.id.btnpause);
		videoTitulo = (TextView)findViewById(R.id.videoTitulo);		
		
		
		videoSeekBar = (SeekBar)findViewById(R.id.videoSeekBar);
		videoTiempo = (TextView)findViewById(R.id.videoTiempo);
		videoTiempoTotal = (TextView)findViewById(R.id.videoTiempoTotal);
		
		frameVideo = (FrameLayout)findViewById(R.id.frameVideo);

		//Inicializamos el tiempo de reproducci�n		
		Constants.tiempoTranscurrido = 0;
		

		Log.d( TAG , "onCreate" );
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		

		videoHandler.removeCallbacks( videoRunnable );
		finish();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		delayedHide(AUTO_HIDE_DELAY_MILLIS);
		
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
		//Personalizar la fuente por c�digo
		Typeface tipoFuente = Typeface.createFromAsset(
				this.getAssets() , "fonts/OpenSans-LightItalic.ttf" );

		videoTitulo.setTypeface( tipoFuente );
		
		
		//Traemos la pantalla del surfaceview
		//y la asignamos el Surfaceholder
		videoHolder = videoSurface.getHolder();
		//Hacemos que la actividad este atenta a los cambios
		//mientras se muestra el video
		videoHolder.addCallback(this);
		
		//Inicializamos el mediaPlayer
		videoPlayer = new MediaPlayer();
		videoPlayer.setOnPreparedListener(this);
		videoPlayer.setOnBufferingUpdateListener(this);
		videoPlayer.setOnCompletionListener(this);
		videoPlayer.setOnErrorListener(this);
		videoPlayer.setOnInfoListener(this);
		videoPlayer.setOnVideoSizeChangedListener(this);
		
		//Para la calidad del audio
		videoPlayer.setAudioStreamType( AudioManager.STREAM_MUSIC );
		
		try {
			
			/*
			//Para streaming de video
			videoPlayer.setDataSource( Constants.ruta );
			*/
			//Para videos dentro de la aplicaci�n
			videoPlayer.setDataSource(
					getApplicationContext(), 
					Uri.parse( Constants.ruta )
					);
			
			
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		//Para el bot�n play
		btnplay.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if( !videoPlayer.isPlaying() ){					
					videoPlayer.start();
					Constants.reproduccion = true;
					btnpause.setVisibility(View.VISIBLE);
					btnplay.setVisibility(View.GONE);
					delayedHide(AUTO_HIDE_DELAY_MILLIS);					
				}
			}
		} );
				
		//Para el bot�n pause
		btnpause.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				if( videoPlayer.isPlaying() ){
					videoPlayer.pause();
					Constants.tiempoTranscurrido = videoPlayer.getCurrentPosition(); 
					Constants.reproduccion = false;
					btnplay.setVisibility(View.VISIBLE);
					btnpause.setVisibility(View.GONE);	
					delayedHide(AUTO_HIDE_DELAY_MILLIS);
					videoHandler.postDelayed( videoRunnable , tiempoHilo );
				}		
				
			}
		} );
		
		

		mSystemUiHider = SystemUiHider.getInstance(this, frameVideo,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeightBottom;
					int mShortAnimTimeBottom;
					int mControlsHeightTop;
					int mShortAnimTimeTop;
					
					
					
					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							//Para barra superior
							if (mControlsHeightTop == 0) {
								mControlsHeightTop = -videoTitulo.getHeight();
							}
							if (mShortAnimTimeTop == 0) {
								mShortAnimTimeTop = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							videoTitulo
									.animate()
									.translationY(visible ? 0 : mControlsHeightTop)
									.setDuration(mShortAnimTimeTop);
							

							//Para barra inferior
							if (mControlsHeightBottom == 0) {
								mControlsHeightBottom = videoControles.getHeight();
							}
							if (mShortAnimTimeBottom == 0) {
								mShortAnimTimeBottom = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							videoControles
									.animate()
									.translationY(visible ? 0 : mControlsHeightBottom)
									.setDuration(mShortAnimTimeBottom);
						} else {
							videoControles.setVisibility(visible ? View.VISIBLE
									: View.GONE);
							videoTitulo.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		// Set up the user interaction to manually show or hide the system UI.
		frameVideo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});
		
		
	}//final del onresume
	
	@Override
	protected void onPause() {
		
		super.onPause();
		//Guardamos en una variable el tiempo 
		//de reproducci�n del video (10s, 15s, 20s, etc)
		Constants.tiempoTranscurrido = videoPlayer.getCurrentPosition();
		videoPlayer.pause();
		
	}
	
	
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//Se crea la pantalla del video :D
		//y podemos ver goku :')
		videoPlayer.setDisplay(holder);
		
		try {
			//Esto es para videos dentro del apk
			videoPlayer.prepare();
			
			//Para streaming
			//videoPlayer.prepareAsync();
			
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		
		try {

			mp.seekTo( Constants.tiempoTranscurrido );
			if(Constants.reproduccion){
				//Iniciamos la reproducci�n
				mp.start();
				videoHandler.postDelayed( videoRunnable , tiempoHilo );
			}
			
			
		} catch (Exception e) {
			
		}

		Log.d( TAG , "onPrepared" );
		
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {

		try {

			if (what == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
			  Log.d(TAG, "Media Error,Muri� el servidor " + extra);
			} else if (what == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
			  Log.d(TAG, "Media Error, Error desconocido " + extra);
			}else{
			  Log.d(TAG, " || " + what + " || " + extra);
			}
			
			finish();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {

		try {

			if (what == MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING) {
			  Log.d(TAG, "Media Info, Media Info Bad Interleaving " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_NOT_SEEKABLE) {
			  Log.d(TAG, "Media Info, Media Info Not Seekable " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_UNKNOWN) {
			  Log.d(TAG, "Media Info, Media Info Unknown " + extra);
			} else if (what == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING) {
			  Log.d(TAG, "MediaInfo, Media Info Video Track Lagging " + extra);
			}else if (what == MediaPlayer.MEDIA_INFO_METADATA_UPDATE) {
			  Log.d(TAG,"MediaInfo, Media Info Metadata Update " + extra);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

		videoSeekBar.setMax( mp.getDuration() );
		videoSeekBar.setProgress( Constants.tiempoTranscurrido );
		videoSeekBar.setOnSeekBarChangeListener(this);
		
		detectaTiempo( videoTiempoTotal , videoPlayer.getDuration(), 2 );
		
	}

	@Override
	public void onCompletion(MediaPlayer mp) {

		videoHandler.removeCallbacks( videoRunnable);
		
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		if( fromUser ){

			Constants.tiempoTranscurrido = videoSeekBar.getProgress();
			detectaTiempo( videoTiempo , Constants.tiempoTranscurrido, 1 );
			delayedHide(AUTO_HIDE_DELAY_MILLIS);
		}
	
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

		videoHandler.removeCallbacks( videoRunnable);	
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

		videoPlayer.seekTo( Constants.tiempoTranscurrido );
		videoHandler.postDelayed( videoRunnable , tiempoHilo );
		
	}


	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};
	
	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};
	
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	
	
	//Para conteo del video

	Handler videoHandler = new Handler();
	Runnable videoRunnable = new Runnable() {
		@Override
		public void run() {
			
			Log.d( TAG , "Runnable" );

			videoHandler.postDelayed( videoRunnable , tiempoHilo );
			
			if( videoPlayer.isPlaying()  ){

				videoSeekBar.setProgress(  videoPlayer.getCurrentPosition()  );

				Constants.tiempoTranscurrido = videoSeekBar.getProgress();
				detectaTiempo( videoTiempo , Constants.tiempoTranscurrido , 1 );
				
			}		
		}
	};
	

	public void detectaTiempo( TextView texto, int tiempoCalculado , int tipo  ){
		
		int psegundos = tiempoCalculado/1000;	
		
		String tiempo;
		//Actualizamos minutos
		int pminutos = psegundos / 60;
		psegundos = psegundos % 60;
	   	//Actualizamos horas
	   	int phoras = pminutos / 60;
	   	pminutos = pminutos % 60;
	   	tiempo = String.format( "%02d : %02d : %02d", phoras , pminutos , psegundos );
	   	if( tipo == 1 ){
		   	texto.setText( tiempo );	   		
	   	}else{
		   	texto.setText( " / " +  tiempo );	   		
	   	}
		
	}

}

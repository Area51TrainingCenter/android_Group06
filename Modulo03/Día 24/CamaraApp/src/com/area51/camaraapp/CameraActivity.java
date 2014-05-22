package com.area51.camaraapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class CameraActivity extends Activity implements OnTouchListener,
		OnDragListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		// ImageView image = (ImageView)findViewById(R.id.image);

		findViewById(R.id.image).setOnTouchListener(this);
		findViewById(R.id.lienzo).setOnDragListener(this);

	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			view.startDrag(null, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean onDrag(View layoutview, DragEvent dragevent) {

		int action = dragevent.getAction();
		View view = (View) dragevent.getLocalState();

		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			break;
		case DragEvent.ACTION_DROP:

			ViewGroup owner = (ViewGroup) view.getParent();
			owner.removeView(view);
			
			FrameLayout container = (FrameLayout) layoutview;
			container.addView(view);
			
			view.setVisibility(View.VISIBLE);
			
			if (container.getId() == R.id.lienzo) {
				view.setOnTouchListener(null);
				owner.setOnDragListener(null);
			}

			break;
		case DragEvent.ACTION_DRAG_ENDED:
			Log.d("LOGCAT", "Drag ended");
			if (dropEventNotHandled(dragevent)) {
				view.setVisibility(View.VISIBLE);
			}
			break;
		default:
			break;
		}

		return true;

	}

	private boolean dropEventNotHandled(DragEvent dragEvent) {
		return !dragEvent.getResult();
	}

}

package com.area51.menulateral;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends BaseActivity {

	public MainActivity() {
		super(R.string.left_and_right);
	}

	SlidingMenu smenu;
	SlidingMenu smenuu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	protected void onResume() {
		super.onResume();

		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.activity_content, new Fragment()).commit();

		getSlidingMenu().setSecondaryMenu(R.layout.menu_right);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_right, new Fragment()).commit();

		/*
		 * smenu = new SlidingMenu(this); smenu.setMode(SlidingMenu.LEFT);
		 * smenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		 * smenu.setShadowWidthRes(R.dimen.shadow_width);
		 * smenu.setShadowDrawable(R.drawable.shadow);
		 * smenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		 * smenu.setFadeDegree(0.35f); smenu.attachToActivity(this,
		 * SlidingMenu.SLIDING_WINDOW); smenu.setMenu(R.layout.menu_lateral);
		 * 
		 * getActionBar().setDisplayHomeAsUpEnabled(true);
		 * getActionBar().setIcon(R.drawable.indicator);
		 * 
		 * smenuu = new SlidingMenu(this); smenuu.setMode(SlidingMenu.RIGHT);
		 * smenuu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		 * smenuu.setShadowWidthRes(R.dimen.shadow_width);
		 * smenuu.setShadowDrawable(R.drawable.shadow);
		 * smenuu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		 * smenuu.setFadeDegree(0.35f); smenuu.attachToActivity(this,
		 * SlidingMenu.SLIDING_WINDOW); smenuu.setMenu(R.layout.menu_lateral);
		 * smenuu.setActivated(true);
		 */
	}

	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { int id =
	 * item.getItemId(); switch (id) { case android.R.id.home: smenu.showMenu();
	 * break; } return super.onOptionsItemSelected(item); }
	 * 
	 * @Override public void onBackPressed() { if (smenu.isMenuShowing()) {
	 * smenu.showContent(); } else if (smenu.isMenuShowing()) {
	 * smenuu.showContent(); } else { super.onBackPressed(); } }
	 */
}

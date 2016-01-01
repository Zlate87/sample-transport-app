package com.zlate87.sample_transport_app.base.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity that every other activity should have it in its hierarchy. It contains comon logic.
 */
public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentViewId());
	}

	/**
	 * @return the layout id to be used in (@code setContentView(int)) when the activity is created.
	 */
	protected abstract int getContentViewId();
}

package com.zlate87.sample_transport_app.base;

import android.app.Application;

import com.zlate87.sample_transport_app.base.injection.BaseComponent;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Application class.
 */
public abstract class App extends Application {

	private static App instance;

	private BaseComponent component;

	public BaseComponent getComponent() {
		if (component == null) {
			component = initializeComponent();
		}
		return component;
	}

	protected abstract BaseComponent initializeComponent();

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		setupJodaTime();
	}

	/**
	 * Method that will setup Android joda-time.
	 */
	protected void setupJodaTime() {
		JodaTimeAndroid.init(this);
	}

	public static App getInstance() {
		return instance;
	}
}

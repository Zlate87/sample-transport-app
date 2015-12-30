package com.zlate87.sample_transport_app.base;

import android.app.Application;

import com.zlate87.sample_transport_app.base.injection.BaseComponent;

/**
 * Created by Zlatko on 12/30/2015.
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
	}

	public static App getInstance() {
		return instance;
	}
}

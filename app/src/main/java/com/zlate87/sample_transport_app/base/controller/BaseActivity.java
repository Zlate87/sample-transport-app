package com.zlate87.sample_transport_app.base.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zlate87.sample_transport_app.R;

/**
 * Base activity that every other activity should have it in its hierarchy. It contains comon logic.
 */
public abstract class BaseActivity extends AppCompatActivity {

	private static final String SHARED_PREFERENCES_FILE = "SHARED_PREFERENCES_FILE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentViewId());
		setupUpButton();
	}

	/**
	 * Setups an Up button.
	 */
	protected void setupUpButton() {
		ActionBar supportActionBar = getSupportActionBar();
		if (supportActionBar != null) {
			supportActionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	/**
	 * @return the layout id to be used in (@code setContentView(int)) when the activity is created.
	 */
	protected abstract int getContentViewId();

	/**
	 * Shows a hint to the user. The user can decide if the hint should not be shown again.
	 * This information will be stored into {@code SharedPreferences}
	 *
	 * @param preferencesKey      the preference key for the storing the user input
	 * @param coordinatorLayoutId the coordinator layout on which the hint should be displayed
	 * @param messageId the id of the hint message
	 */
	protected void showHint(final String preferencesKey, int coordinatorLayoutId, int messageId) {
		final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_FILE, MODE_PRIVATE);
		boolean isHintPresented = sharedPreferences.getBoolean(preferencesKey, false);
		if (!isHintPresented) {
			View coordinatorLayout = findViewById(coordinatorLayoutId);
			final Snackbar snackbar = Snackbar.make(coordinatorLayout, messageId, Snackbar.LENGTH_INDEFINITE);
			snackbar.setAction(R.string.doNotShowAgain, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putBoolean(preferencesKey, true);
					editor.apply();
				}
			});
			snackbar.show();
		}
	}
}

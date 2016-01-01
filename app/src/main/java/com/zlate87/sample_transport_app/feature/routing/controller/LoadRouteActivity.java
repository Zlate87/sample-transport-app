package com.zlate87.sample_transport_app.feature.routing.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.service.AsyncRoutingService;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.zlate87.sample_transport_app.feature.routing.controller.RoutesPreviewActivity.ROUTE_RESPONSE_INTENT_EXTRA_KEY;

/**
 * Activity class responsible for loading the selected route.
 */
public class LoadRouteActivity extends BaseActivity {

	private static final String TAG = RoutesPreviewActivity.class.getSimpleName();

	/**
	 * Intent extra key for the {@code RouteQueryParameters} that should be used when loading the route.
	 */
	public static final String ROUTE_QUERY_PARAMETERS_INTENT_EXTRA_KEY = "ROUTE_QUERY_PARAMETERS_INTENT_EXTRA_KEY";

	@Inject
	AsyncRoutingService asyncRoutingService;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_load_route_activity;
	}

	private Action1<RouteResponse> routeResponseAction = new Action1<RouteResponse>() {
		@Override
		public void call(RouteResponse routeResponse) {
			Log.v(TAG, "routeResponseAction.call(RouteResponse): called");
			Intent loadRoutesActivityIntent = new Intent(LoadRouteActivity.this, RoutesPreviewActivity.class);
			loadRoutesActivityIntent.putExtra(ROUTE_RESPONSE_INTENT_EXTRA_KEY, routeResponse);
			startActivity(loadRoutesActivityIntent);
		}
	};

	private Action1<Throwable> routeResponseErrorAction = new Action1<Throwable>() {
		@Override
		public void call(Throwable throwable) {
			Log.e(TAG, "routeResponseErrorAction: ", throwable);
			// TODO: 1/1/2016 Show error to the user
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
		loadRoute();
	}

	private void loadRoute() {
		RouteQueryParameters routeQueryParameters = (RouteQueryParameters) getIntent().getExtras()
						.get(ROUTE_QUERY_PARAMETERS_INTENT_EXTRA_KEY);

		asyncRoutingService
						.route(routeQueryParameters)
						.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.doOnError(routeResponseErrorAction)
						.subscribe(routeResponseAction);

		// TODO: 1/1/2016 handle if activity is closed before the route is loaded
	}

}

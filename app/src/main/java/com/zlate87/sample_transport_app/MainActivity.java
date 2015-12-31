package com.zlate87.sample_transport_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.service.AsyncRoutingService;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	@Inject
	AsyncRoutingService asyncRoutingService;

	private Action1<RouteResponse> routeResponseAction = routeResponse -> {
		TextView helloWorldLabel = (TextView) findViewById(R.id.helloWorldLabel);
		String text = String.format("%s routes found", routeResponse.getRoutes().size());
		helloWorldLabel.setText(text);
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
		setContentView(R.layout.activity_main);

		asyncRoutingService
						.route(null)
						.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(routeResponseAction);

	}
}

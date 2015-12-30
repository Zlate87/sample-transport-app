package com.zlate87.sample_transport_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.service.RoutingService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

	@Inject
	RoutingService routingService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
		setContentView(R.layout.activity_main);

		RouteResponse route = routingService.route(null);
		Toast.makeText(this, route.toString(), Toast.LENGTH_LONG).show();
	}
}

package com.zlate87.sample_transport_app.feature.routing.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

import java.util.List;

/**
 * Adapter for the routes.
 */
public class RoutesAdapter extends RecyclerView.Adapter<RouteViewHolder> {

	private List<RouteDetails> routeDetailsList;

	/**
	 * Constructor.
	 * @param routeDetailsList the view model list
	 */
	public RoutesAdapter(List<RouteDetails> routeDetailsList) {
		this.routeDetailsList = routeDetailsList;
	}

	@Override
	public RouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View routesListElement = layoutInflater.inflate(R.layout.routing_routes_list_element, parent, false);
		return new RouteViewHolder(routesListElement);
	}

	@Override
	public void onBindViewHolder(RouteViewHolder holder, int position) {
		holder.reUseView(routeDetailsList.get(position).getRoutePreview());
	}

	@Override
	public int getItemCount() {
		return routeDetailsList.size();
	}
}

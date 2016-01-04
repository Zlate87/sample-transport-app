package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

import java.util.List;

/**
 * Adapter for the routes preview.
 */
public class RoutesPreviewAdapter extends RecyclerView.Adapter<RoutePreviewViewHolder> {

	private List<RouteDetails> routeDetailsList;

	/**
	 * Constructor.
	 * @param routeDetailsList the view model list
	 */
	public RoutesPreviewAdapter(List<RouteDetails> routeDetailsList) {
		this.routeDetailsList = routeDetailsList;
	}

	@Override
	public RoutePreviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View routesListElement = layoutInflater.inflate(R.layout.routing_routes_list_element, parent, false);
		return new RoutePreviewViewHolder(routesListElement);
	}

	@Override
	public void onBindViewHolder(RoutePreviewViewHolder holder, int position) {
		holder.reUseView(routeDetailsList.get(position));
	}

	@Override
	public int getItemCount() {
		return routeDetailsList.size();
	}
}

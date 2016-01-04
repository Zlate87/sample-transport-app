package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.model.Segment;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteSegment;

import java.util.List;

/**
 * Adapter for the route segments.
 */
public class RouteSegmentsAdapter extends RecyclerView.Adapter<RouteSegmentViewHolder> {

	private List<RouteSegment> segments;

	/**
	 * Constructor.
	 * @param segments the view model
	 */
	public RouteSegmentsAdapter(List<RouteSegment> segments) {
		this.segments = segments;
	}

	@Override
	public RouteSegmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View segmentListElement = layoutInflater.inflate(R.layout.routing_rote_segment_list_element, parent, false);
		return new RouteSegmentViewHolder(segmentListElement);
	}

	@Override
	public void onBindViewHolder(RouteSegmentViewHolder holder, int position) {
		holder.reUseView(segments.get(position));
	}

	@Override
	public int getItemCount() {
		return segments.size();
	}
}

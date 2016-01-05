package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteSegment;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.SegmentStop;

import java.util.List;

/**
 * {@code ViewHolder} for a route segment.
 */
public class RouteSegmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


	private TextView segmentFirstStopTime;
	private View segmentChain;
	private TextView segmentFirstStopName;
	private TextView segmentTravelMode;
	private TextView segmentName;
	private TextView segmentDescription;
	private TextView segmentDuration;
	private LinearLayout stopsTimesLinearLayout;
	private LinearLayout stopsNamesLinearLayout;

	private RouteSegment segment;

	private Context context;


	/**
	 * Constructor.
	 *
	 * @param itemView the view
	 */
	public RouteSegmentViewHolder(View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);
		context = itemView.getContext();
		setup(itemView);
	}

	private void setup(View view) {
		segmentFirstStopTime = (TextView) view.findViewById(R.id.segmentFirstStopTime);
		segmentChain = view.findViewById(R.id.segmentChain);
		segmentFirstStopName = (TextView) view.findViewById(R.id.segmentFirstStopName);
		segmentName = (TextView) view.findViewById(R.id.segmentName);
		segmentTravelMode = (TextView) view.findViewById(R.id.segmentTravelMode);
		segmentDescription = (TextView) view.findViewById(R.id.segmentDescription);
		segmentDuration = (TextView) view.findViewById(R.id.segmentDuration);

		stopsTimesLinearLayout = (LinearLayout) view.findViewById(R.id.stopsTimesLinearLayout);
		stopsNamesLinearLayout = (LinearLayout) view.findViewById(R.id.stopsNamesLinearLayout);
	}

	@Override
	public void onClick(View v) {
		segment.setIsExpanded(!segment.isExpanded());
		stopsTimesLinearLayout.setVisibility(segment.isExpanded() ? View.VISIBLE : View.GONE);
		stopsNamesLinearLayout.setVisibility(segment.isExpanded() ? View.VISIBLE : View.GONE);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) segmentChain.getLayoutParams();
		int anchorId = segment.isExpanded() ? R.id.stopsNamesLinearLayout : R.id.segmentInformationLayout;
		params.addRule(RelativeLayout.ALIGN_BOTTOM, anchorId);
	}

	/**
	 * Re use the view.
	 *
	 * @param segment the view model
	 */
	public void reUseView(RouteSegment segment) {
		this.segment = segment;
		renderRouteSegment(segment);
	}

	public void renderRouteSegment(RouteSegment segment) {
		List<SegmentStop> segmentStops = segment.getSegmentStops();
		int color = Color.parseColor(segment.getColor());
		segmentFirstStopTime.setText(segmentStops.get(0).getTime());
		segmentFirstStopName.setText(segmentStops.get(0).getName());
		segmentFirstStopName.setTextColor(color);
		segmentChain.setBackgroundColor(color);
		segmentName.setText(segment.getName());
		segmentTravelMode.setText(segment.getTravelMode());
		segmentDescription.setText(segment.getDescription());
		segmentDuration.setText(segment.getDuration());

		// make sure there are time and name text view for every stop (-1 because the first one is already available)
		int stopViewsToBeAdded = segmentStops.size() - stopsTimesLinearLayout.getChildCount() - 1;
		for (int i = 0; i < stopViewsToBeAdded; i++) {
			TextView stopTimeTextView = new TextView(context);
			stopsTimesLinearLayout.addView(stopTimeTextView);
			TextView stopNameTextView = new TextView(context);
			stopsNamesLinearLayout.addView(stopNameTextView);
		}

		// hide images views that should not be used  (-1 because the first one is already available)
		for (int i = segmentStops.size() - 1; i < stopsTimesLinearLayout.getChildCount(); i++) {
			stopsTimesLinearLayout.getChildAt(i).setVisibility(View.GONE);
			stopsNamesLinearLayout.getChildAt(i).setVisibility(View.GONE);
		}

		// set the stops time and name
		// start from 1, since the 0 is pre filed already; -1 because the first one is not in this layout
		for (int i = 1; i < segmentStops.size(); i++) {
			TextView stopTimeTextView = (TextView) stopsTimesLinearLayout.getChildAt(i - 1);
			stopTimeTextView.setVisibility(View.VISIBLE);
			stopTimeTextView.setText(segmentStops.get(i).getTime());
			TextView stopNameTextView = (TextView) stopsNamesLinearLayout.getChildAt(i - 1);
			stopNameTextView.setVisibility(View.VISIBLE);
			stopNameTextView.setText(segmentStops.get(i).getName());
		}

	}
}

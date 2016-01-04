package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteSegment;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.SegmentStop;

import java.util.List;

/**
 * {@code ViewHolder} for a route segment.
 */
public class RouteSegmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


	private LinearLayout timesLayout;
	private TextView segmentFirstStopTime;
	private View segmentChain;
	private LinearLayout segmentInformationLayout;
	private TextView segmentFirstStopName;
	private TextView segmentTravelMode;
	private TextView segmentName;
	private TextView segmentDescription;
	private TextView segmentDuration;

	private RouteSegment segment;

	private Context context;

	int segmentInformationLayoutNotNamechildren;

	/**
	 * Constructor.
	 *
	 * @param itemView the view
	 */
	public RouteSegmentViewHolder(View itemView) {
		super(itemView);
		context = itemView.getContext();
		setup(itemView);
	}
	private void setup(View view) {
		timesLayout = (LinearLayout) view.findViewById(R.id.timesLayout);
		segmentFirstStopTime = (TextView) view.findViewById(R.id.segmentFirstStopTime);
		segmentChain = view.findViewById(R.id.segmentChain);
		segmentInformationLayout = (LinearLayout) view.findViewById(R.id.segmentInformationLayout);
		segmentFirstStopName = (TextView) view.findViewById(R.id.segmentFirstStopName);
		segmentName = (TextView) view.findViewById(R.id.segmentName);
		segmentTravelMode = (TextView) view.findViewById(R.id.segmentTravelMode);
		segmentDescription = (TextView) view.findViewById(R.id.segmentDescription);
		segmentDuration = (TextView) view.findViewById(R.id.segmentDuration);

		// -1 because of segmentFirstStopName
		segmentInformationLayoutNotNamechildren = segmentInformationLayout.getChildCount() - 1;
	}

	@Override
	public void onClick(View v) {
		// TODO: 1/4/2016 expande colapse, make sure the user is informed about the feature
		// TODO: 1/4/2016 if no stops, notifiy user
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
		segmentFirstStopTime.setText(segmentStops.get(0).getTime());
		segmentFirstStopName.setText(segmentStops.get(0).getName());
		segmentChain.setBackgroundColor(Color.parseColor(segment.getColor()));
		segmentName.setText(segment.getName());
		segmentTravelMode.setText(segment.getTravelMode());
		segmentDescription.setText(segment.getDescription());
		segmentDuration.setText(segment.getDuration());

		// make sure there are time and name text view for every stop
		int stopViewsToBeAdded = segmentStops.size() - timesLayout.getChildCount();
		for (int i = 0; i < stopViewsToBeAdded; i++) {
			TextView stopTimeTextView = new TextView(context);
			timesLayout.addView(stopTimeTextView);
			TextView stopNameTextView = new TextView(context);
			segmentInformationLayout.addView(stopNameTextView);
		}

		// hide images views that should not be used
		for (int i = segmentStops.size(); i < timesLayout.getChildCount(); i++) {
			timesLayout.getChildAt(i).setVisibility(View.GONE);
			segmentInformationLayout.getChildAt(i + segmentInformationLayoutNotNamechildren).setVisibility(View.GONE);
		}

		// set the stops time and name (start from 1, since the 0 is pre filed already)
		for (int i = 1; i < segmentStops.size(); i++) {
			TextView stopTimeTextView = (TextView) timesLayout.getChildAt(i);
			stopTimeTextView.setText(segmentStops.get(i).getTime());
			TextView stopNameTextView = (TextView) segmentInformationLayout.getChildAt(
							i + segmentInformationLayoutNotNamechildren);
			stopNameTextView.setText(segmentStops.get(i).getName());
		}

	}
}

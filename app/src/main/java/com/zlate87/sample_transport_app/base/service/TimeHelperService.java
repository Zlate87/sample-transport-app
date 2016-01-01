package com.zlate87.sample_transport_app.base.service;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Service class responsible for working with time objects.
 */
public class TimeHelperService {

	private static DateTimeFormatter fillDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ");
	private static DateTimeFormatter justTimeFormatter = DateTimeFormat.forPattern("HH:mm");
	private static PeriodFormatter durationFormatter = new PeriodFormatterBuilder()
					.appendHours()
					.appendSuffix("h")
					.appendSeparator(" ")
					.appendMinutes()
					.appendLiteral("min")
					.toFormatter();

	/**
	 * Method that will calculate the duration between two given times in string format
	 *
	 * @param fromDateTime the from time
	 * @param toDateTime   the to time
	 * @return the duration
	 */
	public String calculateDuration(String fromDateTime, String toDateTime) {
		DateTime from = fillDateFormatter.parseDateTime(fromDateTime);
		DateTime to = fillDateFormatter.parseDateTime(toDateTime);
		Interval interval = new Interval(from, to);
		Period period = interval.toPeriod();
		return durationFormatter.print(period);
	}

	/**
	 * Formats the date time and returns just the time part.
	 *
	 * @param dateTimeString the full date time
	 * @return just the time
	 */
	public String justTime(String dateTimeString) {
		DateTime dateTime = fillDateFormatter.parseDateTime(dateTimeString);
		return dateTime.toString(justTimeFormatter);
	}
}

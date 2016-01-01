package com.zlate87.sample_transport_app.base.service;

import com.zlate87.sample_transport_app.UTCRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class for {@code TimeHelperService}.
 */
public class TimeHelperServiceTest {

	@Rule
	public UTCRule utcRule = new UTCRule();

	private TimeHelperService timeHelperService;

	@Before
	public void setUp() {
		timeHelperService = new TimeHelperService();
	}

	@Test
	public void shouldReturnJustTime() {
		// given
		String dateTimeString = "2015-04-17T13:30:00+02:00";

		// when
		String justTime = timeHelperService.justTime(dateTimeString);

		// then
		assertThat(justTime, is("11:30"));
	}

	@Test
	public void shouldCalculateDuration() {
		// given
		String from = "2015-04-17T13:30:00+02:00";
		String to = "2015-04-17T13:51:00+02:00";

		// when
		String duration = timeHelperService.calculateDuration(from, to);

		// then
		assertThat(duration, is("21min"));
	}

}
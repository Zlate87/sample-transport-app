package com.zlate87.sample_transport_app.base.service;

import android.content.Context;
import android.content.res.AssetManager;

import com.zlate87.sample_transport_app.BuildConfig;
import com.zlate87.sample_transport_app.TestHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@code FileReaderService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FileReaderServiceTest {

	private FileReaderService fileReaderService;
	private AssetManager assets;

	@Before
	public void setUp() {
		Context context = mock(Context.class);
		assets = mock(AssetManager.class);
		given(context.getAssets()).willReturn(assets);
		fileReaderService = new FileReaderService(context);
	}

	@Test
	public void shouldReadTextFileFromAssets() throws IOException {
		// given
		String fileName = "test.txt";
		InputStream inputStream = new TestHelper().getInputStreamFromResourcesFile(fileName);
		given(assets.open(fileName)).willReturn(inputStream);

		// when
		String text = fileReaderService.readTextFileFromAssets(fileName);

		// then
		assertThat(text, is("Test Text"));
	}



	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeExceptionIfReadTextFileFromAssetsHasIOException() throws IOException {
		// given
		given(assets.open(anyString())).willThrow(new IOException("Some Exception"));

		// when
		fileReaderService.readTextFileFromAssets("someFile");

		// then exception expected
	}

}
package com.zlate87.sample_transport_app.base.service;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A helper class responsible for reading files.
 */
// TODO: 12/29/2015 add tests
public class FileReaderService {

	public static final String DEFAULT_ENCODING = "UTF-8";
	private static final String TAG = FileReaderService.class.getSimpleName();

	private Context context;

	public FileReaderService(Context context) {
		this.context = context;
	}

	/**
	 * Method that reads a file with a given path from the assets directory.
	 *
	 * @param filePath the file path
	 * @return the text from the file in a {@code String}
	 */

	public String readTextFileFromAssets(String filePath) {
		StringBuilder stringBuilder;
		InputStream inputStream;
		BufferedReader bufferedReader = null;
		try {
			inputStream = context.getAssets().open(filePath);
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_ENCODING));
			String str;
			stringBuilder = new StringBuilder();
			while ((str = bufferedReader.readLine()) != null) {
				stringBuilder.append(str);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					Log.e(TAG, "readTextFileFromAssets: failed to close buffer reader", e);
				}
			}
		}
		return stringBuilder.toString();
	}
}

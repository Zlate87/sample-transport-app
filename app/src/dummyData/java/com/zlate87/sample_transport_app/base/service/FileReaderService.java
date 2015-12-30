package com.zlate87.sample_transport_app.base.service;

import android.content.Context;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * A helper class responsible for reading files.
 */
public class FileReaderService {

	public static final String DEFAULT_ENCODING = "UTF-8";

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
		try {
			InputStream inputStream = context.getAssets().open(filePath);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, DEFAULT_ENCODING);
			return CharStreams.toString(inputStreamReader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

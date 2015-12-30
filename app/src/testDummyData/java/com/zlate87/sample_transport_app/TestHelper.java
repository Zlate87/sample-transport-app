package com.zlate87.sample_transport_app;

import com.google.common.io.Files;

import org.apache.maven.artifact.ant.shaded.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Helper class for working with tests.
 */
public class TestHelper {

	public static final String UTF_8 = "UTF-8";

	/**
	 * Gets the text content in String format from a test resources file.
	 *
	 * @param fileName the file name
	 * @return the string with the content from the file
	 */
	public String getTextContentFromResourcesFile(String fileName) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			List<String> strings = Files.readLines(file, Charset.forName(UTF_8));
			return StringUtils.join(strings.iterator(), "");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the input stream from a test resources file.
	 *
	 * @param fileName the file name
	 * @return the input stream from the file
	 */
	public InputStream getInputStreamFromResourcesFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream(fileName);
	}
}

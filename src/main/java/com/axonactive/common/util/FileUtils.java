/**
 * 
 */
package com.axonactive.common.util;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author tshen
 *
 */
public class FileUtils {
	
	private static final int ONE_MB = 1024 * 1024;
	
	private FileUtils() {}
	
	public static boolean isFileSizeLessThan(InputStream candidateFile, int maximumMBAllowed)
			throws IOException {
		int fileSize = candidateFile.available() / ONE_MB;
		return fileSize < maximumMBAllowed ;
	}

}

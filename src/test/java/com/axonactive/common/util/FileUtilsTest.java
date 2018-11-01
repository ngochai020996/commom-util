/**
 * 
 */
package com.axonactive.common.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * @author tshen
 *
 */
public class FileUtilsTest {
	
	private static final String EXCEL_FILE_ONE_MB = "excel-files/EXCEL_FILE_ONE_MB.xlsx";
	private static final String EXCEL_FILE_TWO_MB = "excel-files/EXCEL_FILE_TWO_MB.xlsx";
	private static final int TWO_MB = 2;
	
	@Test
	public void Test_GivenFileSizeLessThanMaxiumMBAllow_shouldReturnTrue() throws IOException {
		InputStream excelFile = this.getClass().getClassLoader().getResourceAsStream(EXCEL_FILE_ONE_MB);
		boolean actual = FileUtils.isFileSizeLessThan(excelFile, TWO_MB);
		
		assertTrue(actual);
	}
	
	@Test
	public void Test_GivenFileSizeMoreThanMaxiumMBAllow_shouldReturnFalse() throws IOException {
		InputStream excelFile = this.getClass().getClassLoader().getResourceAsStream(EXCEL_FILE_TWO_MB);
		boolean actual = FileUtils.isFileSizeLessThan(excelFile, TWO_MB);
		
		assertFalse(actual);
	}
}

package com.axonactive.common.util;

import java.security.SecureRandom;
import java.util.Random;


public class GuidGenerator {
	private static final  String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int NUMBER_OF_CHARACTER = 12;
	private static final int NUMBER_OF_SEPARATOR = 3;
	private static final char SEPARATOR = '-';
	
	private static final Random rng = new SecureRandom();    

	
	private GuidGenerator() {
		
	}
	
	public static String generateUUID() {
		return randomUUID(NUMBER_OF_CHARACTER, NUMBER_OF_SEPARATOR, SEPARATOR);
	}
	
	public static String randomUUID(int length, int spacing, char spacerChar){
	    StringBuilder sb = new StringBuilder();
	    int spacer = 0;
	    int lengthIn = length;
	    while(lengthIn > 0){
	        if(spacer == spacing){
	            sb.append(spacerChar);
	            spacer = 0;
	        }
	        lengthIn--;
	        spacer++;
	        sb.append(randomChar());
	    }
	    return sb.toString();
	}
	
	
	private static char randomChar(){
	    return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
	}


}

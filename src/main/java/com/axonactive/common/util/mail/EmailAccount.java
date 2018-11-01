/**
 * 
 */
package com.axonactive.common.util.mail;

/**
 * @author nvmuon
 *
 */
public class EmailAccount {

	/**
	 * email account
	 */
	private String username;
	
	/**
	 * the authentication password of the account
	 */
	private String password;
	
	/**
	 * the fullname of the account
	 */
	private String emailAddress;

	
	
	/**
	 * default constructor
	 */
	public EmailAccount() {
		// This is default constructor
	}



	/**
	 * @param email
	 * @param password
	 * @param emailAddress
	 */
	public EmailAccount(String username, String password, String emailAddress) {
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}



	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}



	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}

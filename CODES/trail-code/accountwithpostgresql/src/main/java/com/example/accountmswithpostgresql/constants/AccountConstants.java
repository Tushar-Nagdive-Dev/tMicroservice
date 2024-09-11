package com.example.accountmswithpostgresql.constants;

public final class AccountConstants {
	
	private AccountConstants() {
		throw new UnsupportedOperationException("This is a constant class and cannot be instantiated");
	}
	
	public static final String SAVINGS = "SAVINGS";
	
	public static final String ADDRESS = "Vijay Nagar, INDORE, INDIA";
	
	public static final String STATUS_201 = "201";
	
	public static final String MESSAGE_201 = "Account created successfully";
	
	public static final String STATUS_200 = "200";
	
	public static final String MESSATE_200 = "Request processed successfully";
	
	public static final String STATUS_500 = "500";
	
	public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev team";

}

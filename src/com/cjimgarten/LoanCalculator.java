/*
 * LoanCalculator.java
 * 
 * created: 12-03-2016
 * modified: 12-04-2016
 * 
 * loan calculator desktop application
 */

package com.cjimgarten;

public class LoanCalculator {

	// add some attributes
	private LoanCalcFrame frame;
	
	/**
	 * create an instance
	 */
	public LoanCalculator() {
		this.frame = new LoanCalcFrame("Loan Calculator");
	}
	
	/**
	 * start the application
	 */
	public void startApplication() {
		this.frame.setVisible(true);
	}
}

/*
 * LoanCalculator.java
 * 
 * created: 12-03-2016
 * modified: 12-10-2016
 * 
 * loan calculator desktop application
 */

package com.cjimgarten.controllers.main;

import com.cjimgarten.views.frames.Frame;

public class LoanCalculator {

	// add some attributes
	private Frame frame;
	
	/**
	 * create an instance
	 */
	public LoanCalculator() {
		this.frame = new Frame("Loan Calculator");
	}
	
	/**
	 * start the application
	 */
	public void startApplication() {
		this.frame.setVisible(true);
	}
}

/*
 * PerformCalculation.java
 * 
 * created: 12-04-2016
 * modified: 12-04-2016
 * 
 * performs loan calculations
 */

package com.cjimgarten;

public class PerformCalculation {

	// attributes needed to perform calculations
	private double loanAmount;
	private double interestRate;
	private int loanTerm;
	
	/**
	 * create an instance
	 */
	public PerformCalculation(double loanAmount, double interestRate, int loanTerm) {
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
	}
	
	/**
	 * calculate
	 */
	public void calculate() {
		this.calculatePayments();
	}
	
	/**
	 * calculate monthly payments
	 */
	public void calculatePayments() {
		System.out.println(loanAmount + ", " + interestRate + ", " + loanTerm);
		// TODO: calculate monthly payments
	}
}

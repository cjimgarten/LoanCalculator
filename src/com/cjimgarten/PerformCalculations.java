/*
 * PerformCalculations.java
 * 
 * created: 12-04-2016
 * modified: 12-04-2016
 * 
 * performs loan calculations
 */

package com.cjimgarten;

public class PerformCalculations {

	// attributes needed to perform calculations
	private double loanAmount;
	private double interestRate;
	private int loanTerm;
	
	/**
	 * create an instance
	 */
	public PerformCalculations(double loanAmount, double interestRate, int loanTerm) {
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
	}
	
	/**
	 * perform calculations
	 */
	public void calculate() {	
		// initialize some important variables
		double n = this.loanTerm * 12; // payments per year times number of years
		double i = (this.interestRate * 0.01) / 12.0; // annual interest rate divided by number of payments per year
		double dfPow = Math.pow((1+i), n); // part of the discount factor formula
		double discountFactor = (dfPow - 1) / (i * dfPow); // not sure... just part of the formula
		
		// calculate monthly and total payment
		double monthlyPayment = this.calculateMonthlyPayment(n, i , discountFactor);
		double totalPayment = this.calculateTotalPayment(monthlyPayment, n);

		// print results
		System.out.println("Monthly payment: " + monthlyPayment);
		System.out.println("Total payment: " + totalPayment);
	}
	
	/**
	 * calculate the monthly payment and round to two decimal places
	 */
	public double calculateMonthlyPayment(double n, double i, double discountFactor) {
		double monthlyPayment = this.loanAmount / discountFactor;
		monthlyPayment *= 100.00;
		monthlyPayment = (Math.round(monthlyPayment)) / 100.00;
		return monthlyPayment;
	}
	
	/**
	 * calculate the total payment
	 */
	public double calculateTotalPayment(double monthlyPayment, double n) {
		double totalPayment = monthlyPayment * n;
		return totalPayment;
	}
}

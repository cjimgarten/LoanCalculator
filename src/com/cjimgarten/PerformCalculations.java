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
	
	// attributes needed to store the results of the calculations
	private double monthlyPayment;
	private double totalPayment;
	private double totalInterest;
	private double annualPayment;
	
	/**
	 * create an instance
	 */
	public PerformCalculations(double loanAmount, double interestRate, int loanTerm) {
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.calculate();
	}
	
	/**
	 * perform calculations
	 */
	private void calculate() {
		// initialize some important variables
		double n = this.loanTerm * 12.0; // payments per year times number of years
		double i = (this.interestRate * 0.01) / 12.0; // annual interest rate divided by number of payments per year
		double dfPow = Math.pow((1+i), n); // part of the discount factor formula
		double discountFactor = (dfPow - 1) / (i * dfPow); // not sure... just part of the formula

		// calculate the monthly payment, total payment, total interest and annual payment
		this.monthlyPayment = this.calculateMonthlyPayment(n, i , discountFactor);
		this.totalPayment = this.calculateTotalPayment(n);
		this.totalInterest = this.calculateInterest();
		this.annualPayment = this.calculateAnnualPayment();

		// print results
		System.out.println("Monthly payment: " + this.monthlyPayment);
		System.out.println("Total payment: " + this.totalPayment);
		System.out.println("Total interest: " + this.totalInterest);
		System.out.println("Annual payment: " + this.annualPayment);
	}
	
	/**
	 * calculate the monthly payment and round to two decimal places
	 */
	private double calculateMonthlyPayment(double n, double i, double discountFactor) {
		double monthlyPayment = this.loanAmount / discountFactor;
		monthlyPayment *= 100.00;
		monthlyPayment = (Math.round(monthlyPayment)) / 100.00;
		return monthlyPayment;
	}
	
	/**
	 * calculate the total payment
	 */
	private double calculateTotalPayment(double n) {
		double totalPayment = this.monthlyPayment * n;
		return totalPayment;
	}
	
	/**
	 * calculate the total interest paid
	 */
	private double calculateInterest() {
		double totalInterest = this.totalPayment - this.loanAmount;
		totalInterest *= 100.00;
		totalInterest = (Math.round(totalInterest)) / 100.00;
		return totalInterest;
	}
	
	/**
	 * calculate the annual payment
	 */
	private double calculateAnnualPayment() {
		double annualPayment = this.monthlyPayment * 12.0;
		return annualPayment;
	}
	
	/**
	 * get methods
	 */
	public double getMonthlyPayment() {
		return this.monthlyPayment;
	}
	
	public double getTotalPayment() {
		return this.totalPayment;
	}
	
	public double getTotalInteres() {
		return this.totalInterest;
	}
	
	public double getAnnualPayment() {
		return this.annualPayment;
	}
}

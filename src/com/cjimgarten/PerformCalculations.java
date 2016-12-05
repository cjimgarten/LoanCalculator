/*
 * PerformCalculations.java
 * 
 * created: 12-04-2016
 * modified: 12-05-2016
 * 
 * performs loan calculations
 */

package com.cjimgarten;

public class PerformCalculations {

	// attributes needed to perform calculations
	private double loanAmount;
	private double interestRate;
	private int loanTerm;
	
	// attributes calculated based on the above attributes ^^
	private double n; /* total number of payments */
	private double i; /* monthly interest rate */
	
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
		this.n = this.loanTerm * 12.0; // payments per year times number of years
		this.i = (this.interestRate * 0.01) / 12.0; // annual interest rate divided by number of payments per year
		this.calculate();
	}
	
	/**
	 * perform calculations
	 */
	private void calculate() {
		// calculate the monthly payment, total payment, total interest and annual payment
		double monthlyPayment1 = this.calculateMonthlyPayment1();
		double monthlyPayment2 = this.calculateMonthlyPayment2();
		this.monthlyPayment = monthlyPayment1;
		this.totalPayment = this.calculateTotalPayment();
		this.totalInterest = this.calculateInterest();
		this.annualPayment = this.calculateAnnualPayment();
	}
	
	/**
	 * calculate the monthly payment and round to two decimal places
	 * uses the discount factor
	 */
	private double calculateMonthlyPayment1() {
		double n = this.n;
		double i = this.i;
		double dfPow = Math.pow((1+i), n); // part of the discount factor formula
		double discountFactor = (dfPow - 1) / (i * dfPow); // not sure... just part of the formula
		double monthlyPayment = this.loanAmount / discountFactor;
		return monthlyPayment;
	}
	
	/**
	 * calculate the monthly payment and round to two decimal places
	 * does not use the discount factor
	 */
	private double calculateMonthlyPayment2() {
		double n = this.n;
		double i = this.i;
		double dfPow = Math.pow((1+i), n); // part of the formula
		double monthlyPayment = (i * this.loanAmount *dfPow) / (dfPow - 1); // calculate payment
		return monthlyPayment;
	}
	
	/**
	 * calculate the total payment and round to two decimal places
	 */
	private double calculateTotalPayment() {
		double n = this.n;
		double totalPayment = this.monthlyPayment * n;
		return totalPayment;
	}
	
	/**
	 * calculate the total interest paid and round to two decimal places
	 */
	private double calculateInterest() {
		double totalInterest = this.totalPayment - this.loanAmount;
		return totalInterest;
	}
	
	/**
	 * calculate the annual payment and round to two decimal places
	 */
	private double calculateAnnualPayment() {
		double annualPayment = this.monthlyPayment * 12.0;
		return annualPayment;
	}
	
	/**
	 * calculate monthly amortization schedule
	 */
	public void printAmortizationSchedule() {
		double n = this.n;
		double i = this.i;
		double remainingBalance = this.loanAmount;
		double[] interests = new double[(int)n];
		double[] principals = new double[(int)n];
		double[] balances = new double[(int)n];
		for (int j = 0; j < n; j++) {
			// perform calculations
			double interestPerPayment = remainingBalance * i;
			double principalPerPayment = this.monthlyPayment - interestPerPayment;
			remainingBalance -= principalPerPayment;
			
			// store calculations in appropriate array
			interests[j] = interestPerPayment;
			principals[j] = principalPerPayment;
			balances[j] = remainingBalance;
		}
		this.printAmoritizationSchedule(interests, principals, balances, n);
	}
	
	/**
	 * round and print monthly amortization schedule
	 */
	private void printAmoritizationSchedule(double[] interests, double[] principals, double[] balances, double n) {
		double roundedMonthlyPayment = this.roundToTwoDecimals(this.monthlyPayment);
		for (int j = 0; j < n; j++) {
			interests[j] = this.roundToTwoDecimals(interests[j]);
			principals[j] = this.roundToTwoDecimals(principals[j]);
			balances[j] = this.roundToTwoDecimals(balances[j]);
			System.out.println(j+1 + ".)\t" + roundedMonthlyPayment + "\t" + interests[j] + "\t" + principals[j] + "\t" +  balances[j]);
		}
	}
	
	/**
	 * round to two decimal places
	 */
	public double roundToTwoDecimals(double roundMe) {
		roundMe *= 100;
		roundMe = (Math.round(roundMe)) / 100.00;
		return roundMe;
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
	
	public double getTotalInterest() {
		return this.totalInterest;
	}
	
	public double getAnnualPayment() {
		return this.annualPayment;
	}
}

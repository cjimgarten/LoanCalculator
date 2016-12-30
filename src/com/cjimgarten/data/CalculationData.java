/*
 * CalculationData.java
 * data object stores loan calculations
 */

package com.cjimgarten.data;

import java.util.ArrayList;

public class CalculationData {
	
	// loan data
	private double numberOfPayments; /* total number of payments */
	private double monthlyInterestRate; /* monthly interest rate */
	private double monthlyPayment;
	private double totalPayment;
	private double totalInterest;
	private double annualPayment;
	
	// array list stores amortization data
	private ArrayList<double[]> amortizationData;
	
	/**
	 * create an instance
	 */
	public CalculationData(double n, double i, double monthlyPayment, double totalPayment, double totalInterest, double annualPayment, ArrayList<double[]> data) {
		super();
		this.numberOfPayments = n;
		this.monthlyInterestRate = i;
		this.monthlyPayment = monthlyPayment;
		this.totalPayment = totalPayment;
		this.totalInterest = totalInterest;
		this.annualPayment = annualPayment;
		this.amortizationData = data;
	}
	
	/**
	 * round the values in the data object
	 */
	private ArrayList<double[]> roundAmortizationValues() {
		ArrayList<double[]> data = this.amortizationData;
		int rows = (int)this.numberOfPayments;
		for (int j = 0; j < rows; j++) {
			data.get(0)[j] = this.roundToTwoDecimals(data.get(0)[j]);
			data.get(1)[j] = this.roundToTwoDecimals(data.get(1)[j]);
			data.get(2)[j] = this.roundToTwoDecimals(data.get(2)[j]);
		}
		return data;
	}
	
	/**
	 * round to two decimal places
	 */
	private double roundToTwoDecimals(double roundMe) {
		roundMe *= 100;
		roundMe = (Math.round(roundMe)) / 100.00;
		return roundMe;
	}
	
	/**
	 * get methods
	 */
	public double getNumberOfPayments() {
		double numberOfPayments = this.roundToTwoDecimals(this.numberOfPayments);
		return numberOfPayments;
	}
	
	public double getMonthlyInterestRate() {
		double monthlyInterestRate = this.roundToTwoDecimals(this.monthlyInterestRate);
		return monthlyInterestRate;
	}
	
	public double getMonthlyPayment() {
		double monthlyPayment = this.roundToTwoDecimals(this.monthlyPayment);
		return monthlyPayment;
	}
	
	public double getTotalPayment() {
		double totalPayment = this.roundToTwoDecimals(this.totalPayment);
		return totalPayment;
	}
	
	public double getTotalInterest() {
		double totalInterest = this.roundToTwoDecimals(this.totalInterest);
		return totalInterest;
	}
	
	public double getAnnualPayment() {
		double annualPayment = this.roundToTwoDecimals(this.annualPayment);
		return annualPayment;
	}
	
	public ArrayList<double[]> getAmortizationData() {
		ArrayList<double[]> amortizationData = this.roundAmortizationValues();
		return amortizationData;
	}
}

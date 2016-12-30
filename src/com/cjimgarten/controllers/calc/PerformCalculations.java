/*
 * PerformCalculations.java
 * performs loan calculations
 */

package com.cjimgarten.controllers.calc;

import java.util.ArrayList;
import com.cjimgarten.data.CalculationData;

public class PerformCalculations {

	// attributes needed to perform calculations
	private double loanAmount;
	private double interestRate;
	private int loanTerm;
	
	// object to store calculation data
	private CalculationData loanCalcData;
	
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
		// variables needed to perform other calculations
		double n = this.loanTerm * 12.0; // payments per year times number of years
		double i = (this.interestRate * 0.01) / 12.0; // annual interest rate divided by number of payments per year
		
		// calculate the monthly payment, total payment, total interest and annual payment
		double monthlyPayment1 = this.calculateMonthlyPayment1(n, i);
		double monthlyPayment2 = this.calculateMonthlyPayment2(n, i);
		double monthlyPayment = monthlyPayment1;
		double totalPayment = this.calculateTotalPayment(monthlyPayment, n);
		double totalInterest = this.calculateInterest(totalPayment);
		double annualPayment = this.calculateAnnualPayment(monthlyPayment);
		ArrayList<double[]> amortizationData = this.calculateAmortizationData(n, i, monthlyPayment);
		this.loanCalcData = new CalculationData(
				n,
				i,
				monthlyPayment,
				totalPayment,
				totalInterest,
				annualPayment,
				amortizationData
			);
	}
	
	/**
	 * calculate the monthly payment and round to two decimal places
	 * uses the discount factor
	 */
	private double calculateMonthlyPayment1(double n, double i) {
		double dfPow = Math.pow((1+i), n); // part of the discount factor formula
		double discountFactor = (dfPow - 1) / (i * dfPow); // not sure... just part of the formula
		double monthlyPayment = this.loanAmount / discountFactor;
		return monthlyPayment;
	}
	
	/**
	 * calculate the monthly payment and round to two decimal places
	 * does not use the discount factor
	 */
	private double calculateMonthlyPayment2(double n, double i) {
		double dfPow = Math.pow((1+i), n); // part of the formula
		double monthlyPayment = (i * this.loanAmount *dfPow) / (dfPow - 1); // calculate payment
		return monthlyPayment;
	}
	
	/**
	 * calculate the total payment and round to two decimal places
	 */
	private double calculateTotalPayment(double monthlyPayment, double n) {
		double totalPayment = monthlyPayment * n;
		return totalPayment;
	}
	
	/**
	 * calculate the total interest paid and round to two decimal places
	 */
	private double calculateInterest(double totalPayment) {
		double totalInterest = totalPayment - this.loanAmount;
		return totalInterest;
	}
	
	/**
	 * calculate the annual payment and round to two decimal places
	 */
	private double calculateAnnualPayment(double monthlyPayment) {
		double annualPayment = monthlyPayment * 12.0;
		return annualPayment;
	}
	
	/**
	 * calculate data for amortization schedule
	 */
	private ArrayList<double[]> calculateAmortizationData(double n, double i, double monthlyPayment) {
		double remainingBalance = this.loanAmount;
		double[] interests = new double[(int)n];
		double[] principals = new double[(int)n];
		double[] balances = new double[(int)n];
		for (int j = 0; j < n; j++) {
			// perform calculations
			double interestPerPayment = remainingBalance * i;
			double principalPerPayment = monthlyPayment - interestPerPayment;
			remainingBalance -= principalPerPayment;
			
			// store calculations in appropriate array
			interests[j] = interestPerPayment;
			principals[j] = principalPerPayment;
			balances[j] = remainingBalance;
		}
		ArrayList<double[]> data = new ArrayList<double[]>();
		data.add(interests);
		data.add(principals);
		data.add(balances);
		return data;
	}
	
	/**
	 * get methods
	 */
	public CalculationData getCalculationData() {
		return this.loanCalcData;
	}
}

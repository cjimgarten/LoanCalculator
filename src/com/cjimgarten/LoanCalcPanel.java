/*
 * LoanCalcPanel.java
 * 
 * created: 12-03-2016
 * modified: 12-05-2016
 * 
 * JPanel for a loan calculator application
 */

package com.cjimgarten;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoanCalcPanel extends JPanel implements ActionListener {

	// first row
	private JLabel amountLabel;
	private JTextField amountTextField;
	private JLabel dollarsLabel;
	
	// second row
	private JLabel interestLabel;
	private JTextField interestTextField;
	private JLabel percentLabel;
	
	// third row
	private JLabel termLabel;
	private JTextField termTextField;
	private JLabel yearsLabel;
	
	// final row
	private JButton calcButton;
	private JButton amortizationButton;
	
	// list to display the calculations
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	/**
	 * create an instance
	 */
	public LoanCalcPanel() {
		super();
		this.configurePanel();
	}
	
	/**
	 * configure the panel
	 */
	public void configurePanel() {
		// configure the panel
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		// configure the first row
		this.amountLabel = new JLabel("Loan Amount:");
		this.amountLabel.setBounds(10, 10, 100, 20);
		this.add(this.amountLabel);
		this.amountTextField = new JTextField();
		this.amountTextField.setBounds(120, 10, 100, 20);
		this.add(this.amountTextField);
		this.dollarsLabel = new JLabel("dollars");
		this.dollarsLabel.setBounds(230, 10, 100, 20);
		this.add(this.dollarsLabel);
		
		// configure the second row
		this.interestLabel = new JLabel("Interest Rate:");
		this.interestLabel.setBounds(10, 40, 100, 20);
		this.add(this.interestLabel);
		this.interestTextField = new JTextField();
		this.interestTextField.setBounds(120, 40, 100, 20);
		this.add(this.interestTextField);
		this.percentLabel = new JLabel("% per year");
		this.percentLabel.setBounds(230, 40, 100, 20);
		this.add(this.percentLabel);
		
		// configure the third row
		this.termLabel = new JLabel("Loan Term:");
		this.termLabel.setBounds(10, 70, 100, 20);
		this.add(this.termLabel);
		this.termTextField = new JTextField();
		this.termTextField.setBounds(120, 70, 100, 20);
		this.add(termTextField);
		this.yearsLabel = new JLabel("years");
		this.yearsLabel.setBounds(230, 70, 100, 20);
		this.add(this.yearsLabel);
		
		// configure a button to perform the calculation
		this.calcButton = new JButton("Calculate");
		this.calcButton.setBounds(10, 100, 100, 25);
		this.calcButton.addActionListener(this);
		this.add(this.calcButton);
		
		// and a button to perform amortization
		this.amortizationButton = new JButton("Amortization");
		this.amortizationButton.setBounds(120, 100, 125, 25);
		this.amortizationButton.addActionListener(this);
		this.add(this.amortizationButton);
		
		// configure a list model to hold calculations
		this.listModel = new DefaultListModel<String>();
		
		// configure a list to display the calculations
		this.list = new JList<String>(this.listModel);
		this.list.setBounds(10, 135, 330, 75);
		this.list.setBorder(new LineBorder(new Color(100, 100, 100), 2));
		this.add(this.list);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get data from text fields
		String loanAmountStr = this.amountTextField.getText();
		String interestRateStr = this.interestTextField.getText();
		String loanTermStr = this.termTextField.getText();
		
		// convert data to appropriate data type
		double loanAmountDouble = 0.00;
		double interestRateDouble = 0.00;
		int loanTermInt = 0;
		try {
			loanAmountDouble = Double.parseDouble(loanAmountStr);
			interestRateDouble = Double.parseDouble(interestRateStr);
			loanTermInt = Integer.parseInt(loanTermStr);
		} catch (NumberFormatException ex) {
			return;
		}
		
		// perform the calculations
		PerformCalculations pc = new PerformCalculations(
				loanAmountDouble,
				interestRateDouble,
				loanTermInt
			);
		
		// clear the list model if it is not empty
		if (!this.listModel.isEmpty()) {
			this.listModel.clear();
		}
		
		// check which button was pressed
		Object src = e.getSource();
		if (src.equals(this.calcButton)) {
			// add calculations to the list model and display to screen
			double monthlyPayment = pc.getMonthlyPayment();
			monthlyPayment = pc.roundToTwoDecimals(monthlyPayment);
			String mpStr = "Monthly payment: $" + monthlyPayment;
			this.listModel.addElement(mpStr);
			double totalPayment = pc.getTotalPayment();
			totalPayment = pc.roundToTwoDecimals(totalPayment);
			String tpStr = "Total payment: $" + totalPayment;
			this.listModel.addElement(tpStr);
			double totalInterest = pc.getTotalInterest();
			totalInterest = pc.roundToTwoDecimals(totalInterest);
			String tiStr = "Total interest: $" + totalInterest;
			this.listModel.addElement(tiStr);
			double annualPayment = pc.getAnnualPayment();
			annualPayment = pc.roundToTwoDecimals(annualPayment);
			String apStr = "Annual payment: $" + annualPayment;
			this.listModel.addElement(apStr);
		} else {
			System.out.println("Monthly Amortization Schedule:");
			System.out.println("No.\tAmt.\tIntr.\tPrin.\tBal.");
			pc.printAmortizationSchedule();
		}
	}
}

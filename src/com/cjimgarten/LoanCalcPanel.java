/*
 * LoanCalcPanel.java
 * 
 * created: 12-03-2016
 * modified: 12-03-2016
 * 
 * JPanel for a loan calculator application
 */

package com.cjimgarten;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get data from text fields
		String loanAmountStr = this.amountTextField.getText();
		String interestRateStr = this.interestTextField.getText();
		String loanTermStr = this.termTextField.getText();
		
		// convert data to appropriate data type
		double loanAmountDouble = Double.parseDouble(loanAmountStr);
		double interestRateDouble = Double.parseDouble(interestRateStr);
		int loanTermInt = Integer.parseInt(loanTermStr);
		
		// perform the calculations
		PerformCalculation pc = new PerformCalculation(
				loanAmountDouble,
				interestRateDouble,
				loanTermInt
			);
		pc.calculate();		
	}
}

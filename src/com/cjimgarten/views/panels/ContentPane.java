/*
 * ContentPane.java
 * 
 * created: 12-03-2016
 * modified: 12-10-2016
 * 
 * content pane for a loan calculator application
 */

package com.cjimgarten.views.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cjimgarten.data.CalculationData;
import com.cjimgarten.controllers.calc.PerformCalculations;

public class ContentPane extends JPanel implements ActionListener {
	
	/* top left panel for the content pane */
	private JPanel tlp;
	
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
	
	// to perform calculations
	private PerformCalculations pc;
	
	/* top right and bottom panels for the content pane */
	private TopRightPanel trp;
	private BottomPanel bp;
	
	/**
	 * create an instance
	 */
	public ContentPane() {
		super();
		this.configurePanel();
	}
	
	/**
	 * configure the content pane
	 */
	private void configurePanel() {
		// line border object to pass to the separate panels
		LineBorder lb = new LineBorder(new Color(100, 100, 100, 100), 1);
		
		// configure the content pane
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		/* configure the top left panel */
		this.tlp = new JPanel();
		int width = 350;
		int height = 150;
		this.tlp.setBorder(lb);
		this.tlp.setLayout(null);
		this.tlp.setBounds(10, 10, width, height);
		
		// configure the first row of the top left panel
		this.amountLabel = new JLabel("Loan Amount:");
		this.amountLabel.setBounds(10, 10, 100, 20);
		this.tlp.add(this.amountLabel);
		this.amountTextField = new JTextField();
		this.amountTextField.setBounds(120, 10, 100, 20);
		this.tlp.add(this.amountTextField);
		this.dollarsLabel = new JLabel("dollars");
		this.dollarsLabel.setBounds(230, 10, 100, 20);
		this.tlp.add(this.dollarsLabel);
		
		// configure the second row of the top left panel
		this.interestLabel = new JLabel("Interest Rate:");
		this.interestLabel.setBounds(10, 40, 100, 20);
		this.tlp.add(this.interestLabel);
		this.interestTextField = new JTextField();
		this.interestTextField.setBounds(120, 40, 100, 20);
		this.tlp.add(this.interestTextField);
		this.percentLabel = new JLabel("% per year");
		this.percentLabel.setBounds(230, 40, 100, 20);
		this.tlp.add(this.percentLabel);
		
		// configure the third row of the top left panel
		this.termLabel = new JLabel("Loan Term:");
		this.termLabel.setBounds(10, 70, 100, 20);
		this.tlp.add(this.termLabel);
		this.termTextField = new JTextField();
		this.termTextField.setBounds(120, 70, 100, 20);
		this.tlp.add(termTextField);
		this.yearsLabel = new JLabel("years");
		this.yearsLabel.setBounds(230, 70, 100, 20);
		this.tlp.add(this.yearsLabel);
		
		// configure a button to perform the calculations
		this.calcButton = new JButton("Calculate");
		this.calcButton.setBounds(10, 100, 100, 25);
		this.calcButton.addActionListener(this);
		this.tlp.add(this.calcButton);
		
		// add the top left panel to the content pane
		this.add(this.tlp);
		
		// initialize the object that performs calculations
		this.pc = null;
		
		/* configure the top right and bottom panels */
		
		// configure the top right panel
		this.trp = new TopRightPanel(lb);
		this.add(this.trp);
		
		// configure the bottom panel
		this.bp = new BottomPanel(lb);
		this.add(this.bp);
	}
	
	/**
	 * event handling method
	 */
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
		this.pc = new PerformCalculations(
				loanAmountDouble,
				interestRateDouble,
				loanTermInt
			);
		
		// get data calculated by pc object
		CalculationData data = this.pc.getCalculationData();
		
		// get and display list model data
		double monthlyPayment = data.getMonthlyPayment();
		double totalPayment = data.getTotalPayment();
		double totalInterest = data.getTotalInterest();
		double annualPayment = data.getAnnualPayment();
		this.trp.displayListModel(monthlyPayment, totalPayment, totalInterest, annualPayment);
		
		// get and display amortization table data
		ArrayList<double[]> amortizationData = data.getAmortizationData();
		this.bp.createAndDisplayTable(amortizationData, monthlyPayment);
	}
}

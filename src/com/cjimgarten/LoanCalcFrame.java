/*
 * LoanCalcFrame.java
 * 
 * created: 12-03-2016
 * modified: 12-04-2016
 * 
 * JFrame for a loan calculator application
 */

package com.cjimgarten;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoanCalcFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * create an instance
	 */
	public LoanCalcFrame(String title) {
		super(title);
		this.configureFrame();
	}
	
	/**
	 * configure the frame
	 */
	public void configureFrame() {
		// configure the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 350, 250);
		
		// configure the content pane
		this.contentPane = new LoanCalcPanel();
		this.setContentPane(this.contentPane);
	}
}

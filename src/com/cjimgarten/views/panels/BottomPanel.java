/*
 * BottomPanel.java
 * bottom panel of the content pane
 */

package com.cjimgarten.views.panels;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class BottomPanel extends JPanel {

	// attributes
	private JTable table; // table to display amortization schedule
	private JScrollPane scrollPane; // scroll pane to hold the table
	
	/**
	 * create an instance
	 */
	public BottomPanel(LineBorder lb) {
		super();
		this.configurePanel(lb);
	}
	
	/**
	 * configure the panel
	 */
	private void configurePanel(LineBorder lb) {
		// variables to set dimensions
		int width = 730;
		int height = 270;
		
		// configure the panel
		this.setBorder(lb);
		this.setLayout(null);
		this.setBounds(10, 170, width, height);
		
		// configure a scroll pane to display the amortization schedule
		this.scrollPane = new JScrollPane(this.table);
		this.scrollPane.setBounds(1, 1, width-(lb.getThickness()*2), height-(lb.getThickness()*2));
		this.add(this.scrollPane);
		
		// initialize an empty table
		this.initializeEmptyTable();
	}
	
	/**
	 * create and display an empty table
	 */
	private void initializeEmptyTable() {
		// configure and display an empty table
		String[] columnNames = {"Payment No.", "Amount", "Interest", "Principal", "Balance"};
		int columns = columnNames.length;
		int rows = 25;
		Object[][] tableData = new Object[rows][columns];
		String payment = "";
		String interest = "";
		String principal = "";
		String balance = "";
		for (int i = 0; i < rows; i++) {
			Object[] row = {new Integer(i+1), payment, interest, principal, balance};
			tableData[i] = row;
		}
		this.table = new JTable(tableData, columnNames);
		this.scrollPane.setViewportView(this.table);
	}
	
	/**
	 * create and display a table
	 */
	public void createAndDisplayTable(ArrayList<double[]> data, double payment) {
		// configure the table to display the amortization schedule
		String[] columnNames = {"Payment No.", "Amount", "Interest", "Principal", "Balance"};
		int columns = columnNames.length;
		int rows = data.get(0).length;
		Object[][] tableData = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			String interest = "$" + data.get(0)[i];
			String principal = "$" + data.get(1)[i];
			String balance = "$" + data.get(2)[i];
			Object[] row = {new Integer(i+1), "$" + payment, interest, principal, balance};
			tableData[i] = row;
		}
		this.table = new JTable(tableData, columnNames);
		this.scrollPane.setViewportView(this.table);
	}
	
	/**
	 * get methods
	 */	
	public JScrollPane getScrollPane() {
		return this.scrollPane;
	}
}

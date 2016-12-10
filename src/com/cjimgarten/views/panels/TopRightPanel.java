/*
 * TopRightPanel.java
 * 
 * created: 12-06-2016
 * modified: 12-10-2016
 * 
 * top right panel of the content pane
 */

package com.cjimgarten.views.panels;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TopRightPanel extends JPanel {

	// list to display the calculations
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	/**
	 * create an instance
	 */
	public TopRightPanel(LineBorder lb) {
		super();
		this.configurePanel(lb);
	}
	
	/**
	 * configure the panel
	 */
	private void configurePanel(LineBorder lb) {
		// variables to set dimensions
		int width = 350;
		int height = 150;
		
		// configure the panel
		this.setBorder(lb);
		this.setLayout(null);
		this.setBounds(390, 10, width, height);
		
		// configure a list model to hold the calculations
		this.listModel = new DefaultListModel<String>();
		
		// configure a list to display the calculations
		this.list = new JList<String>(this.listModel);
		this.list.setBounds(1, 1, width-(lb.getThickness()*2), height-(lb.getThickness()*2));
		this.add(this.list);
	}
	
	/**
	 * display the list model data
	 */
	public void displayListModel(double monthlyPayment, double totalPayment, double totalInterest, double annualPayment) {
		// clear the list model if it is not empty
		if (!this.listModel.isEmpty()) {
			this.listModel.clear();
		}
		
		// add calculations to the list model and display to screen
		String mpStr = "Monthly payment: $" + monthlyPayment;
		this.listModel.addElement(mpStr);
		String tpStr = "Total payment: $" + totalPayment;
		this.listModel.addElement(tpStr);
		String tiStr = "Total interest: $" + totalInterest;
		this.listModel.addElement(tiStr);
		String apStr = "Annual payment: $" + annualPayment;
		this.listModel.addElement(apStr);
	}
	
	/**
	 * get methods
	 */
	public DefaultListModel<String> getListModel() {
		return this.listModel;
	}
	
	public JList<String> getList() {
		return this.list;
	}
	
//	/**
//	 * add elements to the list model
//	 */
//	public void addToListModel(String element) {
//		this.listModel.addElement(element);
//	}
//	
//	/**
//	 * test if list model is empty
//	 */
//	public boolean listModelEmpty() {
//		if (this.listModel.isEmpty()) {
//			return true;
//		}
//		return false;
//	}
//	
//	/**
//	 * clear elements from the list model
//	 */
//	public void clearListModel() {
//		this.listModel.clear();
//	}
}

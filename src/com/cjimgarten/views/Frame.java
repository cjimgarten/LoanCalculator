/*
 * Frame.java
 * 
 * created: 12-03-2016
 * modified: 12-06-2016
 * 
 * frame for a loan calculator application
 */

package com.cjimgarten.views;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private ContentPane contentPane;

	/**
	 * create an instance
	 */
	public Frame(String title) {
		super(title);
		this.configureFrame();
	}
	
	/**
	 * configure the frame
	 */
	private void configureFrame() {
		// variables to set frame dimensions
		int width = 750;
		int height = 450;
		
		// configure the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, width, height);
		
		// configure the content pane
		this.contentPane = new ContentPane();
		this.setContentPane(this.contentPane);
	}
}

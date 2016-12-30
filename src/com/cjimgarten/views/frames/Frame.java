/*
 * Frame.java
 * frame for a loan calculator application
 */

package com.cjimgarten.views.frames;

import javax.swing.JFrame;
import com.cjimgarten.views.panels.ContentPane;

public class Frame extends JFrame {

	// attributes
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

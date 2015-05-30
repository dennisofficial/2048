package org.croniks.j2048.core;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("2048");
		f.setContentPane(new JavaPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
	
}

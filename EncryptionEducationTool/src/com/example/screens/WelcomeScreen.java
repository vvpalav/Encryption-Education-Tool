package com.example.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.algorithms.Algorithm;
import com.example.algorithms.MD5Algorithm;
import com.example.algorithms.RC4Algorithm;
import com.example.algorithms.PGPAlgorithm;

public class WelcomeScreen extends JFrame {

	private static final long serialVersionUID = -8315658054698527320L;
	String[] algoList = new String[] { "Message-Digest Algorithm (MD5)", "Rivest Cipher (RC4)", "Preety Good Policy (PGP)" };
	public static Color color = new Color(44, 144, 151);

	public WelcomeScreen() {
		setSize(350, 175);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenSize.getWidth()/2.5), (int) (screenSize.getHeight()/3));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Encryption Education Tool");
		setLayout(new GridLayout(2, 1, 0, 0));
		add(createHeading());
		add(createAlgorithmsList());
		setVisible(true);
	}

	public JPanel createAlgorithmsList() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 0, 1));
		JComboBox<String> j = new JComboBox<String>(algoList);
		panel.add(j);
		panel.add(createContinueButton(j));
		return panel;
	}

	public JButton createContinueButton(final JComboBox<String> list) {
		JButton b = new JButton("<html><body><b><font size=5 face=\"Arial\">Continue</b></body></html>");
		b.setForeground(color);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Algorithm a = null;
				switch ((String) list.getSelectedItem()) {
				case "Message-Digest Algorithm (MD5)":
					a = new MD5Algorithm();
					break;
				case "Rivest Cipher (RC4)":
					a = new RC4Algorithm();
					break;
				case "Preety Good Policy (PGP)":
					a = new PGPAlgorithm();
					break;
				}
				new AlgorithmHomePage(a);
			}
		});
		return b;
	}

	public JPanel createHeading() {
		JPanel p = new JPanel(new GridLayout(1, 1));
		JLabel label = new JLabel("<html><body><font size=6 face=\"Arial\">Learn Encryption</body></html>", 
				SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		p.setBackground(color);
		p.add(label);
		return p;
	}
}

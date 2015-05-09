package com.example.screens;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.algorithms.Algorithm;
import com.example.algorithms.AlgorithmDataHolder;

public class MD5ExecutionPage extends JFrame {

	private static final long serialVersionUID = 9078957146304458540L;
	private List<String> output;
	private Algorithm algo;
	private JTextArea area;
	private JPanel mainPanel;
	private JPanel buttonsPanel;
	private JPanel outputPanel;
	private JButton next;
	private JButton prev;
	private int activeIndex = -1;
	private JTextArea plainText;
	private JTextArea cipherText;
	private JLabel label2;
	private JLabel label;
	
	public MD5ExecutionPage(Algorithm algo) {
		this.algo = algo;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((d.getSize().width) / 1.6);
		int y = (int) ((d.getSize().height) / 1.3);
		setSize(x, y);
		setLocation(250, 80);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createInputPanels();
		setPanelsInsideMainFrame(mainPanel, buttonsPanel);
		setVisible(true);
	}
	
	public void setPanelsInsideMainFrame(JPanel panel1, JPanel panel2){
		getContentPane().removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Input Text and Label Panel
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.9;
		add(panel1, c);
		
		//Next and Previous Buttons Panel
		c.gridy = 1;
		c.weighty = 0.1;
		add(panel2, c);
		validate();
		repaint();
	}
	
	public void createInputPanels(){
		createEnterTextPanel();
		createButtonsPanel();
		createOutputPanel();
	}
	
	public void createEnterTextPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Input label
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.01;
		JLabel label = new JLabel("<html><body><b><font size=5 face=\"Arial\">Enter Plain Text</b></body></html>");
		label.setForeground(WelcomeScreen.color);
		mainPanel.add(label, c);
		
		//Input text area
		c.insets = new Insets(10, 10, 10, 10);
		c.gridy = 1;
		c.weighty = 1;
		area = new JTextArea();
		area.setLineWrap(true);
		area.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scrollPane = new JScrollPane(area);
		mainPanel.add(scrollPane, c);
	}
	
	public void createButtonsPanel(){
		//Next and Previous Buttons
		buttonsPanel = new JPanel(new GridLayout(1, 2));
		next = new JButton("<html><body><b><font size=5 face=\"Arial\">Next</b></body></html>");
		next.setForeground(WelcomeScreen.color);
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Next: Active Index before: " + activeIndex);
				if(activeIndex <= -1){
					AlgorithmDataHolder input = new AlgorithmDataHolder();
					System.out.println(area.getText());
					input.setInput(area.getText());
					try {
						algo.executeAlgorithm(input);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(MD5ExecutionPage.this, 
								e1.getClass() + "\n" + e1.getMessage());
						e1.printStackTrace();
					}
					output = input.getOutput();
					prev.setEnabled(true);
				}
				
				if(activeIndex < output.size()){
					setNextPane();
				}
				
				if (activeIndex >= output.size()-2){
					next.setEnabled(false);
				}
				//System.out.println("Next: Active Index After: " + activeIndex);
			}
		});
		buttonsPanel.add(next);
		
		prev = new JButton("<html><body><b><font size=5 face=\"Arial\">Previous</b></body></html>");
		prev.setForeground(WelcomeScreen.color);
		prev.setEnabled(false);
		prev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Prev: Active Index Before: " + activeIndex);
				if(activeIndex >= 0){
					retrievePreviousPanel();
				}
				if(activeIndex <= -1){
					prev.setEnabled(false);
				} 
				if (activeIndex < output.size()-1 || activeIndex == -1){
					next.setEnabled(true);
				}
				//System.out.println("Prev: Active Index After: " + activeIndex);
			}
		});
		buttonsPanel.add(prev);
	}
	
	public void createOutputPanel() {
		plainText = new JTextArea();
		plainText.setMargin(new Insets(10, 10, 10, 10));
		cipherText = new JTextArea();
		cipherText.setMargin(new Insets(10, 10, 10, 10));
		outputPanel = new JPanel();
		outputPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Input label
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.01;
		label = new JLabel("<html><body><b><font size=5 face=\"Arial\">Plain Text</b></body></html>");
		label.setForeground(WelcomeScreen.color);
		outputPanel.add(label, c);

		// Input text
		c.gridy = 1;
		c.weighty = 0.49;
		plainText.setEditable(false);
		outputPanel.add(new JScrollPane(plainText), c);

		// Output label
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 2;
		c.weighty = 0.01;
		label2 = new JLabel("<html><body><b><font size=5 face=\"Arial\">Cipher Text</b></body></html>");
		label2.setForeground(WelcomeScreen.color);
		outputPanel.add(label2, c);

		// Input text
		c.gridy = 3;
		c.weighty = 0.49;
		cipherText.setEditable(false);
		outputPanel.add(new JScrollPane(cipherText), c);
	}
	
	public void setNextPane(){
		activeIndex++;
		plainText.setText(output.get(activeIndex));
		cipherText.setText(output.get(activeIndex+1));
		label.setText("<html><body><b><font size=5 face=\"Arial\">Round " + (activeIndex) + "</b></body></html>");
		label2.setText("<html><body><b><font size=5 face=\"Arial\">Round " + (activeIndex+1) + "</b></body></html>");
		setPanelsInsideMainFrame(outputPanel, buttonsPanel);
	}
	
	public void retrievePreviousPanel() {
		activeIndex--;
		if (activeIndex <= -1) {
			setPanelsInsideMainFrame(mainPanel, buttonsPanel);
		} else {
			label.setText("<html><body><b><font size=5 face=\"Arial\">Round " + (activeIndex) + "</b></body></html>");
			label2.setText("<html><body><b><font size=5 face=\"Arial\">Round " + (activeIndex+1) + "</b></body></html>");
			plainText.setText(output.get(activeIndex));
			cipherText.setText(output.get(activeIndex+1));
			setPanelsInsideMainFrame(outputPanel, buttonsPanel);
		}
	}
}

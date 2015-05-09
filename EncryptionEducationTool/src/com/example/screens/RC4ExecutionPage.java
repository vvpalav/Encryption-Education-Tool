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

public class RC4ExecutionPage extends JFrame {

	private static final long serialVersionUID = -7149625379730357239L;
	private List<String> output;
	private Algorithm algo;
	private JTextArea inputArea;
	private JTextArea inputKey;
	private JPanel mainPanel;
	private JPanel buttonsPanel;
	private JPanel outputPanel;
	private JButton encrypt;
	private JButton decrypt;
	private JButton prev;
	private JTextArea plainText;
	private JTextArea cipherText;
	
	public RC4ExecutionPage(Algorithm algo) {
		this.algo = algo;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((d.getSize().width) / 1.6);
		int y = (int) ((d.getSize().height) / 1.3);
		setSize(x, y);
		setLocation(250, 80);
		createInputPanels();
		setPanelsInsideMainFrame(mainPanel, buttonsPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		//Label for Plain Text
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.01;
		JLabel label = new JLabel("<html><body><b><font size=5 face=\"Arial\">Enter Plain or Cipher Text</b></body></html>");
		label.setForeground(WelcomeScreen.color);
		mainPanel.add(label, c);
		
		//Input text area for Plain Text
		c.gridy = 1;
		c.weighty = 0.49;
		inputArea = new JTextArea();
		inputArea.setLineWrap(true);
		inputArea.setMargin(new Insets(10, 10, 10, 10));
		mainPanel.add(new JScrollPane(inputArea), c);
		
		// Label for Key
		c.gridy = 2;
		c.weighty = 0.01;
		JLabel label3 = new JLabel(
				"<html><body><b><font size=5 face=\"Arial\">Enter Key</b></body></html>");
		label3.setForeground(WelcomeScreen.color);
		mainPanel.add(label3, c);
				
		// Input text area for Key
		c.gridy = 3;
		c.weighty = 0.49;
		inputKey = new JTextArea();
		inputKey.setLineWrap(true);
		inputKey.setMargin(new Insets(10, 10, 10, 10));
		mainPanel.add(new JScrollPane(inputKey), c);
	}
	
	public void createButtonsPanel(){

		//Encrypt and Decrypt Buttons
		buttonsPanel = new JPanel(new GridLayout(1, 2));
		encrypt = new JButton("<html><body><b><font size=5 face=\"Arial\">Encrypt Plain Text</b></body></html>");
		encrypt.setForeground(WelcomeScreen.color);
		encrypt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputArea.getText().length() > 0
						&& inputKey.getText().length() > 0){
					AlgorithmDataHolder algoOutput = new AlgorithmDataHolder();
					algoOutput.setInput(inputArea.getText());
					algoOutput.setKey(inputKey.getText());
					algoOutput.setSpecialStr(Algorithm.encrypt);
					try {
						algo.executeAlgorithm(algoOutput);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(RC4ExecutionPage.this, 
								e1.getClass() + "\n" + e1.getMessage());
						e1.printStackTrace();
					}
					output = algoOutput.getOutput();
					setNextPane();
				} else {
					JOptionPane.showMessageDialog(RC4ExecutionPage.this, 
							"Plain/Cipher Text or Key is missing");
				}
			}
		});
		buttonsPanel.add(encrypt);
		
		decrypt = new JButton("<html><body><b><font size=5 face=\"Arial\">Decrypt Cipher Text</b></body></html>");
		decrypt.setForeground(WelcomeScreen.color);
		decrypt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inputArea.getText().length() > 0
						&& inputKey.getText().length() > 0){
					AlgorithmDataHolder algoOutput = new AlgorithmDataHolder();
					algoOutput.setInput(inputArea.getText());
					algoOutput.setKey(inputKey.getText());
					algoOutput.setSpecialStr(Algorithm.decrypt);
					try {
						algo.executeAlgorithm(algoOutput);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(RC4ExecutionPage.this, 
								e1.getClass() + "\n" + e1.getMessage());
						e1.printStackTrace();
					}
					output = algoOutput.getOutput();
					setNextPane();
				} else {
					JOptionPane.showMessageDialog(RC4ExecutionPage.this, 
							"Plain/Cipher Text or Key is missing");
				}
			}
		});
		buttonsPanel.add(decrypt);
		
		prev = new JButton("<html><body><b><font size=5 face=\"Arial\">Previous</b></body></html>");
		prev.setForeground(WelcomeScreen.color);
		prev.setEnabled(false);
		prev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				retrievePreviousPanel();
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
		JLabel label = new JLabel("<html><body><b><font size=5 face=\"Arial\">Input Text</b></body></html>");
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
		JLabel label2 = new JLabel("<html><body><b><font size=5 face=\"Arial\">Output Text</b></body></html>");
		label2.setForeground(WelcomeScreen.color);
		outputPanel.add(label2, c);

		// Input text
		c.gridy = 3;
		c.weighty = 0.49;
		cipherText.setEditable(false);
		outputPanel.add(new JScrollPane(cipherText), c);
	}
	
	public void setNextPane(){
		prev.setEnabled(true);
		encrypt.setEnabled(false);
		decrypt.setEnabled(false);
		plainText.setText(output.get(0));
		cipherText.setText(output.get(1));
		setPanelsInsideMainFrame(outputPanel, buttonsPanel);
	}
	
	public void retrievePreviousPanel() {
		prev.setEnabled(false);
		encrypt.setEnabled(true);
		decrypt.setEnabled(true);
		setPanelsInsideMainFrame(mainPanel, buttonsPanel);
	}
}

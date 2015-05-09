package com.example.screens;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.example.algorithms.Algorithm;
import com.example.algorithms.AlgorithmDataHolder;

public class PGPExecutionPage extends JFrame {

	private static final long serialVersionUID = -282789900290734616L;
	private Algorithm algo;
	private JTextArea inputParaPhrase;
	private JPanel mainPanel;
	private JPanel buttonsPanel;
	private JButton encrypt;
	private JButton decrypt;
	private JFileChooser chooser = new JFileChooser();
	private JTextArea inputFile;
	private JTextArea outputFile;
	private JLabel filePath;

	public PGPExecutionPage(Algorithm algo) {
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

	public void setPanelsInsideMainFrame(JPanel panel1, JPanel panel2) {
		getContentPane().removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Input Text and Label Panel
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.9;
		add(panel1, c);

		// Next and Previous Buttons Panel
		c.gridy = 1;
		c.weighty = 0.1;
		add(panel2, c);
		validate();
		repaint();
	}

	public void createInputPanels() {
		createEnterTextPanel();
		createButtonsPanel();
	}

	public void createEnterTextPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Label for Plain Text
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		JLabel label = new JLabel(
				"<html><body><b><font size=5 face=\"Arial\">Enter "
						+ "Input File Path</b></body></html>");
		label.setForeground(WelcomeScreen.color);
		mainPanel.add(label, c);

		// Input text area for Input File
		c.gridy = 1;
		inputFile = new JTextArea();
		inputFile.setLineWrap(true);
		inputFile.setMargin(new Insets(20, 10, 10, 10));
		mainPanel.add(inputFile, c);

		// Label for Key
		c.gridy = 2;
		JLabel label2 = new JLabel(
				"<html><body><b><font size=5 face=\"Arial\">Enter Output "
						+ "File Path</b></body></html>");
		label2.setForeground(WelcomeScreen.color);
		mainPanel.add(label2, c);

		// Input text area for Output File
		c.gridy = 3;
		outputFile = new JTextArea();
		outputFile.setLineWrap(true);
		outputFile.setMargin(new Insets(20, 10, 10, 10));
		mainPanel.add(outputFile, c);

		// Label for Key
		c.gridy = 4;
		JLabel label3 = new JLabel(
				"<html><body><b><font size=5 face=\"Arial\">Enter "
						+ "Paraphrase</b></body></html>");
		label3.setForeground(WelcomeScreen.color);
		mainPanel.add(label3, c);

		// Input text area for Paraphrase
		c.gridy = 5;
		inputParaPhrase = new JTextArea();
		inputParaPhrase.setLineWrap(true);
		inputParaPhrase.setMargin(new Insets(20, 10, 10, 10));
		mainPanel.add(inputParaPhrase, c);

		JButton fileChoose = new JButton(
				"<html><body><b><font size=5 face=\"Arial\">"
						+ "Choose PGP Public/Private Key File</b></body></html>");
		fileChoose.setForeground(WelcomeScreen.color);
		fileChoose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setFileFilter(new FileNameExtensionFilter(
						"PGP Key File", "pgp"));
				int returnVal = chooser.showOpenDialog(PGPExecutionPage.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					filePath.setText("<html><body><b><font size=5 face=\"Arial\">"
							+ "PGP Key File Selected: "
							+ chooser.getSelectedFile().getAbsolutePath()
							+ "</b></body></html>");
				}
			}
		});
		c.gridy = 6;
		mainPanel.add(fileChoose, c);

		c.gridy = 7;
		filePath = new JLabel(
				"<html><body><b><font size=5 face=\"Arial\">PGP Key File "
						+ "Selected: No File Selected</b></body></html>");
		filePath.setForeground(WelcomeScreen.color);
		mainPanel.add(filePath, c);
	}

	public void createButtonsPanel() {
		// Encrypt and Decrypt Buttons
		buttonsPanel = new JPanel(new GridLayout(1, 2));
		encrypt = new JButton(
				"<html><body><b><font size=5 face=\"Arial\">Encrypt "
						+ "Plain Text</b></body></html>");
		encrypt.setForeground(WelcomeScreen.color);
		encrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputFile.getText().length() > 0
						&& outputFile.getText().length() > 0
						&& inputParaPhrase.getText().length() > 0
						&& chooser.getSelectedFile() != null
						&& chooser.getSelectedFile().getName().length() > 0) {
					AlgorithmDataHolder algoOutput = new AlgorithmDataHolder();
					algoOutput.setPGPInputFile(inputFile.getText());
					algoOutput.setPGPOutputFile(outputFile.getText());
					algoOutput.setSpecialStr(Algorithm.encrypt);
					algoOutput.setPGPKeyFile(chooser.getSelectedFile()
							.getAbsolutePath());
					algoOutput.setPGPParaPhrase(inputParaPhrase.getText());
					try {
						algo.executeAlgorithm(algoOutput);
						if (algoOutput.isPGPSuccessFlag()) {
							JOptionPane.showMessageDialog(
									PGPExecutionPage.this,
									"Algorithm execution returned success\n"
											+ "Check the output file at "
											+ algoOutput.getPGPOutputFile());
						} else {
							JOptionPane.showMessageDialog(
									PGPExecutionPage.this,
									"Algorithm execution returned failure");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(PGPExecutionPage.this,
								e1.getClass() + "\n" + e1.getMessage());
						e1.printStackTrace();
					}
				} else {
					JOptionPane
							.showMessageDialog(PGPExecutionPage.this,
									"Input File or Output File or Paraphrase or Key File is Missing");
				}
			}
		});
		buttonsPanel.add(encrypt);

		decrypt = new JButton(
				"<html><body><b><font size=5 face=\"Arial\">Decrypt "
						+ "Cipher Text</b></body></html>");
		decrypt.setForeground(WelcomeScreen.color);
		decrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputFile.getText().length() > 0
						&& outputFile.getText().length() > 0
						&& inputParaPhrase.getText().length() > 0
						&& chooser.getSelectedFile() != null
						&& chooser.getSelectedFile().getName().length() > 0) {
					AlgorithmDataHolder algoOutput = new AlgorithmDataHolder();
					algoOutput.setPGPInputFile(inputFile.getText());
					algoOutput.setPGPOutputFile(outputFile.getText());
					algoOutput.setSpecialStr(Algorithm.decrypt);
					algoOutput.setPGPKeyFile(chooser.getSelectedFile()
							.getAbsolutePath());
					algoOutput.setPGPParaPhrase(inputParaPhrase.getText());
					try {
						algo.executeAlgorithm(algoOutput);
						if (algoOutput.isPGPSuccessFlag()) {
							JOptionPane.showMessageDialog(
									PGPExecutionPage.this,
									"Algorithm execution returned success\n"
											+ "Check the output file at "
											+ algoOutput.getPGPOutputFile());
						} else {
							JOptionPane.showMessageDialog(
									PGPExecutionPage.this,
									"Algorithm execution returned failure");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(PGPExecutionPage.this,
								e1.getClass() + "\n" + e1.getMessage());
					}
				} else {
					JOptionPane
							.showMessageDialog(PGPExecutionPage.this,
									"Input File or Output File or Paraphrase or Key File is Missing");
				}
			}
		});
		buttonsPanel.add(decrypt);
	}
}

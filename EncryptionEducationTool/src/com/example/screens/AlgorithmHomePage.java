package com.example.screens;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.example.algorithms.Algorithm;

public class AlgorithmHomePage extends JFrame {

	private static final long serialVersionUID = 7911265908273747418L;
	private Algorithm algo;

	public AlgorithmHomePage(Algorithm algorithm) {
		this.algo = algorithm;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((d.getSize().width) / 1.6);
		int y = (int) ((d.getSize().height) / 1.3);
		setSize(x, y);
		setLocation(250, 80);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(algo.getAlgoName());
		setVisible(true);
		setLayout(new GridLayout(1, 1));
		add(algorithm.getAlgoPane());
	}
}

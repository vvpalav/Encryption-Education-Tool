package com.example.main;

import java.awt.EventQueue;

import com.example.screens.WelcomeScreen;

public class EncryptionEducationMain {

	public EncryptionEducationMain() {
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				new WelcomeScreen();
			}
		});
	}
}

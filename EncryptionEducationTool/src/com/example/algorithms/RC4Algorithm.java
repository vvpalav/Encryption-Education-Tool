package com.example.algorithms;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import com.example.AlgorithmInformation.RC4_InfoText;

public class RC4Algorithm extends Algorithm {

	private byte[] S;
	private byte[] T;
	private int keylen;

	public RC4Algorithm() {
		super("Rivest Cipher Algorithm - RC4", RC4_InfoText.file,
				RC4_InfoText.source);
	}
	
	public void createKeyStream(final byte[] key){
		S = new byte[256];
		T = new byte[256];
		
		if (key.length < 1 || key.length > 256) {
			throw new IllegalArgumentException(
					"key must be between 1 and 256 bytes");
		} else {
			keylen = key.length;
			for (int i = 0; i < 256; i++) {
				S[i] = (byte) i;
				T[i] = key[i % keylen];
			}
			int j = 0;
			byte tmp;
			for (int i = 0; i < 256; i++) {
				j = (j + S[i] + T[i]) & 0xFF;
				tmp = S[j];
				S[j] = S[i];
				S[i] = tmp;
			}
		}
	}
	
	@Override
	public void executeAlgorithm(AlgorithmDataHolder input) {
		createKeyStream(input.getKey().getBytes());
		List<String> out = new LinkedList<String>();
		out.add(input.getInput());
		byte[] str = null;
		if(input.getSpecialStr().equals(Algorithm.encrypt)){
			str = encrypt(input.getInput().getBytes());
			out.add(toHexString(str));
		} else if (input.getSpecialStr().equals(Algorithm.decrypt)){
			str = encrypt(hexStringToByteArray(input.getInput()));
			try {
				out.add(new String(str, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}	
		input.setOutput(out);
	}

	public byte[] encrypt(final byte[] plaintext) {
		final byte[] ciphertext = new byte[plaintext.length];
		int i = 0, j = 0, k, t;
		byte tmp;
		for (int counter = 0; counter < plaintext.length; counter++) {
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			tmp = S[j];
			S[j] = S[i];
			S[i] = tmp;
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			ciphertext[counter] = (byte) (plaintext[counter] ^ k);
		}
		return ciphertext;
	}

	public String toHexString(byte[] array){
    	StringBuilder str = new StringBuilder();
    	for(byte c : array){
    		str.append(String.format("%02X", c));
    	}
    	return str.toString();	
    }
	
	public byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}

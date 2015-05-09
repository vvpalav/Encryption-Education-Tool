package com.example.algorithms;

import com.example.AlgorithmInformation.PGP_InfoText;
import com.example.algorithms.PGP.PGPFileProcessor;

public class PGPAlgorithm extends Algorithm {

	public PGPAlgorithm() {
		super("Pretty Good Policy (PGP) - RFC 4880", PGP_InfoText.file,
				PGP_InfoText.source);
	}

	@Override
	public void executeAlgorithm(AlgorithmDataHolder input) throws Exception {
		try {
			boolean flag = false;
			PGPFileProcessor p = new PGPFileProcessor();
			p.setPassphrase(input.getPGPParaPhrase());
			p.setInputFileName(input.getPGPInputFile());
			p.setOutputFileName(input.getPGPOutputFile());
			if (input.getSpecialStr().equals(Algorithm.encrypt)) {
				p.setPublicKeyFileName(input.getPGPKeyFile());
				flag = p.encrypt();
			} else {
				p.setSecretKeyFileName(input.getPGPKeyFile());
				flag = p.decrypt();
			}
			input.setPGPSuccessFlag(flag);
		} catch (Exception e) {
			input.setPGPSuccessFlag(false);
			throw e; 
		}
	}
}

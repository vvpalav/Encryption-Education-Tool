package com.example.algorithms;

import java.util.List;

public class AlgorithmDataHolder {

	private List<String> output;
	private String input;
	private String key;
	private String specialStr;
	private String PGPParaPhrase;
	private String PGPKeyFile;
	private String PGPOutputFile;
	private String PGPInputFile;
	private boolean PGPSuccessFlag;
	
	public AlgorithmDataHolder() {}
	
	public void setInput(String input){
		this.input = input.trim();
	}
	
	public void setSpecialStr(String str){
		this.specialStr = str.trim();
	}
	
	public String getSpecialStr(){
		return this.specialStr;
	}
	
	public void setKey(String key){
		this.key = key.trim();
	}
	
	public void setOutput(List<String> output){
		this.output = output;
	}
	
	public List<String> getOutput(){
		return output;
	}
	
	public String getInput(){
		return input;
	}
	
	public String getKey(){
		return key;
	}

	public String getPGPParaPhrase() {
		return PGPParaPhrase;
	}

	public void setPGPParaPhrase(String pGPParaPhrase) {
		PGPParaPhrase = pGPParaPhrase.trim();
	}

	public String getPGPKeyFile() {
		return PGPKeyFile;
	}

	public void setPGPKeyFile(String pGPKeyFile) {
		PGPKeyFile = pGPKeyFile.trim();
	}

	public String getPGPOutputFile() {
		return PGPOutputFile;
	}

	public void setPGPOutputFile(String pGPOutputFile) {
		PGPOutputFile = pGPOutputFile.trim();
	}

	public String getPGPInputFile() {
		return PGPInputFile;
	}

	public void setPGPInputFile(String pGPInputFile) {
		PGPInputFile = pGPInputFile.trim();
	}

	public boolean isPGPSuccessFlag() {
		return PGPSuccessFlag;
	}

	public void setPGPSuccessFlag(boolean pGPSuccessFlag) {
		PGPSuccessFlag = pGPSuccessFlag;
	}
}

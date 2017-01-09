package com.example.quezzter;

public class Dataholder {

	String question;
	String optionA;
	String optionB;
	String optionC;
	String optionD;
	String correctAnswer;
	boolean answeredVal;
	
	public boolean isAnsweredVal() {
		return answeredVal;
	}
	public void setAnsweredVal(boolean answeredVal) {
		this.answeredVal = answeredVal;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}

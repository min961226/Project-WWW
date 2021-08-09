package com.qs.www.mypage.model.dto;

import java.io.Serializable;

public class CheckQuestionDTO implements Serializable {
	
	private String questionCode;
	private String questionBody;
	
	public CheckQuestionDTO () {}

	public CheckQuestionDTO(String questionCode, String questionBody) {
		super();
		this.questionCode = questionCode;
		this.questionBody = questionBody;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	@Override
	public String toString() {
		return "CheckQuestionDTO [questionCode=" + questionCode + ", questionBody=" + questionBody + "]";
	}
}

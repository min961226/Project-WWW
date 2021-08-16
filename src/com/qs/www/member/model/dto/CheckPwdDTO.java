package com.qs.www.member.model.dto;

import java.io.Serializable;

import com.qs.www.member.model.dto.CheckQuestionDTO;

public class CheckPwdDTO implements Serializable {
	
	private String memberId;
	private String name;
	private String email;
	private CheckQuestionDTO question;
	private String questionAnswer;
	
	public CheckPwdDTO() {}
	
	public CheckPwdDTO(String memberId, String name, String email, CheckQuestionDTO question, String questionAnswer) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.question = question;
		this.questionAnswer = questionAnswer;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CheckQuestionDTO getQuestion() {
		return question;
	}

	public void setQuestion(CheckQuestionDTO question) {
		this.question = question;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	@Override
	public String toString() {
		return "CheckPwdDTO [memberId=" + memberId + ", name=" + name + ", email=" + email + ", question=" + question
				+ ", questionAnswer=" + questionAnswer + "]";
	}
}

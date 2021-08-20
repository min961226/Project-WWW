package com.qs.www.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class MemberInfoDTO implements Serializable {
	
	private int memberNo;
	private String memberId;
	private String password;
	private String name;
	private DepartmentDTO department;
	private JobDTO job;
	private String email;
	private Date enrollDate;
	private String entYn;
	private Date entDate;
	private RoleDTO role;
	private String appWorkType;
	private int workCode;
	private int remainingHoliday;
	private String domitoryYn;
	private String gender;
	private Date birthday;
	private String rrn;
	private String phone;
	private String address;
	private CheckQuestionDTO checkQuestion;
	private String questionAnswer;
	
	public MemberInfoDTO() {}

	public MemberInfoDTO(int memberNo, String memberId, String password, String name, DepartmentDTO department,
			JobDTO job, String email, Date enrollDate, String entYn, Date entDate, RoleDTO role, String appWorkType,
			int workCode, int remainingHoliday, String domitoryYn, String gender, Date birthday, String rrn,
			String phone, String address, CheckQuestionDTO checkQuestion, String questionAnswer) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.department = department;
		this.job = job;
		this.email = email;
		this.enrollDate = enrollDate;
		this.entYn = entYn;
		this.entDate = entDate;
		this.role = role;
		this.appWorkType = appWorkType;
		this.workCode = workCode;
		this.remainingHoliday = remainingHoliday;
		this.domitoryYn = domitoryYn;
		this.gender = gender;
		this.birthday = birthday;
		this.rrn = rrn;
		this.phone = phone;
		this.address = address;
		this.checkQuestion = checkQuestion;
		this.questionAnswer = questionAnswer;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getEntYn() {
		return entYn;
	}

	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public String getAppWorkType() {
		return appWorkType;
	}

	public void setAppWorkType(String appWorkType) {
		this.appWorkType = appWorkType;
	}

	public int getWorkCode() {
		return workCode;
	}

	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}

	public int getRemainingHoliday() {
		return remainingHoliday;
	}

	public void setRemainingHoliday(int remainingHoliday) {
		this.remainingHoliday = remainingHoliday;
	}

	public String getDomitoryYn() {
		return domitoryYn;
	}

	public void setDomitoryYn(String domitoryYn) {
		this.domitoryYn = domitoryYn;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CheckQuestionDTO getCheckQuestion() {
		return checkQuestion;
	}

	public void setCheckQuestion(CheckQuestionDTO checkQuestion) {
		this.checkQuestion = checkQuestion;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	@Override
	public String toString() {
		return "MemberInfoDTO [memberNo=" + memberNo + ", memberId=" + memberId + ", password=" + password + ", name="
				+ name + ", department=" + department + ", job=" + job + ", email=" + email + ", enrollDate="
				+ enrollDate + ", entYn=" + entYn + ", entDate=" + entDate + ", role=" + role + ", appWorkType="
				+ appWorkType + ", workCode=" + workCode + ", remainingHoliday=" + remainingHoliday + ", domitoryYn="
				+ domitoryYn + ", gender=" + gender + ", birthday=" + birthday + ", rrn=" + rrn + ", phone=" + phone
				+ ", address=" + address + ", checkQuestion=" + checkQuestion + ", questionAnswer=" + questionAnswer
				+ "]";
	}
}

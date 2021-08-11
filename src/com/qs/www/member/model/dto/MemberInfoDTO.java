package com.qs.www.member.model.dto;

import java.io.Serializable;
import java.sql.Date;


public class MemberInfoDTO implements Serializable {
	
	private int memberNo;
	private String memberId;
	private String password;
	private String name;
	private Date enrollDate;
	private Date entDate;
	private String entYn;
	private String email;
	private Date birthday;
	private String address;
	private String gender;
	private String phone;
	private String questionCode;
	private CheckQuestionDTO checkQuestion;
	private String questionAnswer;
	private String domitoryYn;
	private String roleCode;
	private RoleDTO role;
	private String deptCode;
	private DepartmentDTO department;
	private String jobCode;
	private JobDTO job;
	private String appWorkType;
	private int workCode;
	private int remainingHoliday;
	
	public MemberInfoDTO() {}

	public MemberInfoDTO(int memberNo, String memberId, String password, String name, Date enrollDate, Date entDate,
			String entYn, String email, Date birthday, String address, String gender, String phone, String questionCode,
			CheckQuestionDTO checkQuestion, String questionAnswer, String domitoryYn, String roleCode, RoleDTO role,
			String deptCode, DepartmentDTO department, String jobCode, JobDTO job, String appWorkType, int workCode,
			int remainingHoliday) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.enrollDate = enrollDate;
		this.entDate = entDate;
		this.entYn = entYn;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.questionCode = questionCode;
		this.checkQuestion = checkQuestion;
		this.questionAnswer = questionAnswer;
		this.domitoryYn = domitoryYn;
		this.roleCode = roleCode;
		this.role = role;
		this.deptCode = deptCode;
		this.department = department;
		this.jobCode = jobCode;
		this.job = job;
		this.appWorkType = appWorkType;
		this.workCode = workCode;
		this.remainingHoliday = remainingHoliday;
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

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public String getEntYn() {
		return entYn;
	}

	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
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

	public String getDomitoryYn() {
		return domitoryYn;
	}

	public void setDomitoryYn(String domitoryYn) {
		this.domitoryYn = domitoryYn;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
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

	@Override
	public String toString() {
		return "MemberInfoDTO [memberNo=" + memberNo + ", memberId=" + memberId + ", password=" + password + ", name="
				+ name + ", enrollDate=" + enrollDate + ", entDate=" + entDate + ", entYn=" + entYn + ", email=" + email
				+ ", birthday=" + birthday + ", address=" + address + ", gender=" + gender + ", phone=" + phone
				+ ", questionCode=" + questionCode + ", checkQuestion=" + checkQuestion + ", questionAnswer="
				+ questionAnswer + ", domitoryYn=" + domitoryYn + ", roleCode=" + roleCode + ", role=" + role
				+ ", deptCode=" + deptCode + ", department=" + department + ", jobCode=" + jobCode + ", job=" + job
				+ ", appWorkType=" + appWorkType + ", workCode=" + workCode + ", remainingHoliday=" + remainingHoliday
				+ "]";
	}
}

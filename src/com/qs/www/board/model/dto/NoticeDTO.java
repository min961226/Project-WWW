package com.qs.www.board.model.dto;

import java.util.Date;

public class NoticeDTO {
	
	private int rowNum;
	private int no;
	private String title;
	private int member;
	private String type;
	private int count;
	private String body;
	private String delete;
	private String created;
	private java.sql.Date createdDate;
	private String modified;
	private String name;
	
	

	public NoticeDTO() {}



	public NoticeDTO(int rowNum, int no, String title, int member, String type, int count, String body, String delete,
			String created, java.sql.Date createdDate, String modified, String name) {
		super();
		this.rowNum = rowNum;
		this.no = no;
		this.title = title;
		this.member = member;
		this.type = type;
		this.count = count;
		this.body = body;
		this.delete = delete;
		this.created = created;
		this.createdDate = createdDate;
		this.modified = modified;
		this.name = name;
	}



	public int getRowNum() {
		return rowNum;
	}



	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getMember() {
		return member;
	}



	public void setMember(int member) {
		this.member = member;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public String getDelete() {
		return delete;
	}



	public void setDelete(String delete) {
		this.delete = delete;
	}



	public String getCreated() {
		return created;
	}



	public void setCreated(String created) {
		this.created = created;
	}



	public java.sql.Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(java.sql.Date createdDate) {
		this.createdDate = createdDate;
	}



	public String getModified() {
		return modified;
	}



	public void setModified(String modified) {
		this.modified = modified;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "NoticeDTO [rowNum=" + rowNum + ", no=" + no + ", title=" + title + ", member=" + member + ", type="
				+ type + ", count=" + count + ", body=" + body + ", delete=" + delete + ", created=" + created
				+ ", createdDate=" + createdDate + ", modified=" + modified + ", name=" + name + "]";
	}





	
}
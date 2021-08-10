package com.qs.www.board.model.dto;

import java.util.Date;

public class FreeBoardDTO {
	
	private int no;
	private String title;
	private int member;
	private String type;
	private int count;
	private String body;
	private String delete;
	private Date created;
	private Date modified;
	

	public FreeBoardDTO() {}


	public FreeBoardDTO(int no, String title, int member, String type, int count, String body, String delete, Date created,
			Date modified) {
		super();
		this.no = no;
		this.title = title;
		this.member = member;
		this.type = type;
		this.count = count;
		this.body = body;
		this.delete = delete;
		this.created = created;
		this.modified = modified;
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


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}


	@Override
	public String toString() {
		return "NoticeDTO [no=" + no + ", title=" + title + ", member=" + member + ", type=" + type + ", count=" + count
				+ ", body=" + body + ", delete=" + delete + ", created=" + created + ", modified=" + modified + "]";
	}
	
	

}

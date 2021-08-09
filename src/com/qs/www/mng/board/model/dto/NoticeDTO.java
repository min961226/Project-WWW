package com.qs.www.mng.board.model.dto;

import java.util.Date;

import com.qs.www.member.model.dto.MemberDTO;

public class NoticeDTO implements java.io.Serializable {
	
	private int no;
	private String title;
	private String body;
	private int writeMemberNo;
	private MemberDTO writer;
	private int count;
	private Date createdDate;
	private String status;
	
	public NoticeDTO() {}

	public NoticeDTO(int no, String title, String body, int writeMemberNo, MemberDTO writer, int count,
			Date createdDate, String status) {
		super();
		this.no = no;
		this.title = title;
		this.body = body;
		this.writeMemberNo = writeMemberNo;
		this.writer = writer;
		this.count = count;
		this.createdDate = createdDate;
		this.status = status;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getWriteMemberNo() {
		return writeMemberNo;
	}

	public void setWriteMemberNo(int writeMemberNo) {
		this.writeMemberNo = writeMemberNo;
	}

	public MemberDTO getWriter() {
		return writer;
	}

	public void setWriter(MemberDTO writer) {
		this.writer = writer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NoticeDTO [no=" + no + ", title=" + title + ", body=" + body + ", writeMemberNo=" + writeMemberNo
				+ ", writer=" + writer + ", count=" + count + ", createdDate=" + createdDate + ", status=" + status
				+ "]";
	}
	
	
	
}

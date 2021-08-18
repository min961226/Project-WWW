package com.qs.www.mng.welfare.model.dto;

public class WelfareYnDTO {

	
	private int documentNo;
	private String useYn;
	private char useYnChar;
	
	public WelfareYnDTO() {}

	public WelfareYnDTO(int documentNo, String useYn, char useYnChar) {
		super();
		this.documentNo = documentNo;
		this.useYn = useYn;
		this.useYnChar = useYnChar;
	}

	public int getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public char getUseYnChar() {
		return useYnChar;
	}

	public void setUseYnChar(char useYnChar) {
		this.useYnChar = useYnChar;
	}

	@Override
	public String toString() {
		return "WelfareYnDTO [documentNo=" + documentNo + ", useYn=" + useYn + ", useYnChar=" + useYnChar + "]";
	}
	
	

}

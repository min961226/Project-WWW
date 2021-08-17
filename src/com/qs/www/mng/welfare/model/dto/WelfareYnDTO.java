package com.qs.www.mng.welfare.model.dto;

public class WelfareYnDTO {

	
	private int documentNo;
	private String useYn;
	
	public WelfareYnDTO() {}

	public WelfareYnDTO(int documentNo, String useYn) {
		super();
		this.documentNo = documentNo;
		this.useYn = useYn;
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

	@Override
	public String toString() {
		return "WelfareYnDTO [documentNo=" + documentNo + ", useYn=" + useYn + "]";
	}
	
	
}

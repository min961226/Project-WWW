package com.qs.www.mypage.model.service;

import com.qs.www.mypage.model.dao.MypageDAO;

public class MypageService {
	
	private final MypageDAO mypageDAO;
	
	public MypageService() {
		mypageDAO = new MypageDAO();
	}
}

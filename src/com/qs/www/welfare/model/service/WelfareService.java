package com.qs.www.welfare.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.welfare.model.dao.WelfareDAO;
import static com.qs.www.common.mybatis.Template.getSqlSession;

public class WelfareService {

	private final WelfareDAO welfareDAO;

	public WelfareService() {
		welfareDAO = new WelfareDAO();
	}
	
	public List<String> checkWelfareList() {
		
		SqlSession session = getSqlSession();
		
		List<String> welfareList = new ArrayList<>();
		
		welfareList = new ArrayList<>()
		
		
		return null;
	}

}

package com.qs.www.main.model.service;

import static com.qs.www.common.mybatis.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dao.MainDAO;
import com.qs.www.main.model.dto.MainDTO;
import com.qs.www.main.model.dto.MainInfoDTO;

public class MainService {
	
	private final MainDAO mainDAO;
	
	public MainService() {
		mainDAO = new MainDAO();
	}

	public MainInfoDTO selectMain(MainDTO mainDTO) {
		
		SqlSession sqlSession = getSqlSession();
		
		List<MainInfoDTO> mainInfo = mainDAO.selectMain(sqlSession, mainDTO);
		
		System.out.println(mainInfo);
		
		sqlSession.close();
		
		return null;
	}
}

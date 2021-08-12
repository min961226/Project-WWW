package com.qs.www.main.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.MainDTO;
import com.qs.www.main.model.dto.MainInfoDTO;

public class MainDAO {

	public List<MainInfoDTO> selectMain(SqlSession sqlSession, MainDTO mainDTO) {
		return sqlSession.selectList("selectMain", mainDTO);
	}
}

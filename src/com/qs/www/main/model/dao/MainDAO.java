package com.qs.www.main.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.qs.www.main.model.dto.CommutingLogDTO;
import com.qs.www.main.model.dto.WorkInfoDTO;
import com.qs.www.main.model.dto.WorkingLogDTO;

public class MainDAO {

	public List<CommutingLogDTO> selectCommutingLog(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectList("MainDAO.selectCommutingLog", workInfo);
	}

	public List<WorkingLogDTO> selectWorkingLog(SqlSession sqlSession, WorkInfoDTO workInfo) {
		return sqlSession.selectList("MainDAO.selectWorkingLog", workInfo);
	}
}
